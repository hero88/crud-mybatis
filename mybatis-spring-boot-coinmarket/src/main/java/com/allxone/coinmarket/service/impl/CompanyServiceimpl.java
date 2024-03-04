package com.allxone.coinmarket.service.impl;

import com.allxone.coinmarket.dto.response.*;
import com.allxone.coinmarket.mapper.*;
import com.allxone.coinmarket.model.*;
import com.allxone.coinmarket.service.CoinService;
import com.allxone.coinmarket.service.CompanyService;
import com.allxone.coinmarket.service.InsuranceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceimpl implements CompanyService {
    private final EmployeesMapper employeesMapper;
    private final PayrollMapper payrollMapper;
    private final CoinService coinService;
    private final TimeTrackingMapper timeTrackingMapper;
    private final TimeTrackingServiceImpl timeTrackingService;
    private final TaxInformationMapper taxInformationMapper;
    private final DepartmentsMapper departmentsMapper;
    private final InsuranceTypeMapper insuranceTypeMapper;

    @Override
    public CompanyDTO getCompany() throws IOException {
        CompanyDTO companyDTO = new CompanyDTO();
        // Lấy ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        // Lấy ngày đầu tiên của tháng hiện tại
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfCurrentMonth = calendar.getTime();
        // Lấy ngày cuối cùng của tháng hiện tại
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDayOfCurrentMonth = calendar.getTime();
        Long countEmployees = employeesMapper.countByExample(new EmployeesExample());
        companyDTO.setTotalEmployees(countEmployees);

        TimeTrackingExample timeTrackingExample = new TimeTrackingExample();
        timeTrackingExample.createCriteria()
                .andDateTrackBetween(firstDayOfCurrentMonth, lastDayOfCurrentMonth);
        BigDecimal totalHour = timeTrackingMapper.sumTotalHoursMonth(timeTrackingExample);
        companyDTO.setTotalHour(totalHour != null ? totalHour : BigDecimal.ZERO);

        List<CoinsUserReponse> list = null;
        try {
            list = coinService.getAllCoinsUser();
            Long totalHolding = list.stream().mapToLong(response -> response.getAmount().longValue()).sum();
            companyDTO.setTotalHoldings(totalHolding);
        } catch (Exception ex) {
            ex.printStackTrace();
            companyDTO.setTotalHoldings(0L);
        }
        List<WorkingTimeDTO> workingTimeDTOS = timeTrackingMapper.findAllWorkingTimeEmployeeByFilters(firstDayOfCurrentMonth, lastDayOfCurrentMonth);
        List<PayrollEmployee> payrollDTOS = getTotalPayrollEmployeesMonth(workingTimeDTOS);

        BigDecimal totalSalary = BigDecimal.valueOf(payrollDTOS.stream().mapToLong(data -> data.getNet_salary().longValue()).sum());
        companyDTO.setTotalPayroll(totalSalary);

        List<PayrollEmployee> top2Payroll = top2Employees(payrollDTOS);
        companyDTO.setListTop2(top2Payroll);
        return companyDTO;
    }
    // Tính tống lương của nhân viên tháng hiện tại
    public List<PayrollEmployee> getTotalPayrollEmployeesMonth(List<WorkingTimeDTO> listWorkings){
        TaxInformationExample taxInformationSQL = new TaxInformationExample();
        // Sử dụng Map để nhóm các bản ghi theo id nhân viên
        List<PayrollEmployee> listPayroll = new ArrayList<>();
        // Tạo danh sach word của từ nhân viên mỗi nhân viên sẽ có 1 danh sach word để tính tổng giờ
        Map<Long, List<WorkingTimeDTO>> groupWorking = new HashMap<>();
        for(WorkingTimeDTO  word : listWorkings){
            if(!groupWorking.containsKey(word.getEmployee_id())){
                groupWorking.put(word.getEmployee_id(),new ArrayList<>());
            }
            groupWorking.get(word.getEmployee_id()).add(word);
        }
        List<List<WorkingTimeDTO>> groupedLists = new ArrayList<>(groupWorking.values());
        // Lọc ra các bản danh sách để tính lương
        for(List<WorkingTimeDTO> working : groupedLists){
            PayrollEmployee dto = new PayrollEmployee();
            int month = getCurrentMonth() - 1;
            BigDecimal totalHours = sumTotalHoursWorking(working);
            // tính thuế
            taxInformationSQL.createCriteria().andEmployeeIdEqualTo(working.get(0).getEmployee_id());
            BigDecimal taxRate = taxInformationMapper.selectByExample(taxInformationSQL).get(0).getTaxRate();
            // tìm lương của nhân viên của tháng gần nhất
            Payroll payroll = payrollMapper.getEmployeeSalaryByMonth(month,working.get(0).getEmployee_id());
            BigDecimal netSalary;
            // so sánh điều kiện nếu nhân viên tháng trước chưa có lương thì mặc định lương theo giờ là 15.00
            if (payroll == null) {
                netSalary = new BigDecimal("15.00")
                        .multiply(totalHours)
                        .multiply(new BigDecimal(100).subtract(taxRate))
                        .divide(new BigDecimal(100));
            } else {
                netSalary = payroll.getSalary()
                        .multiply(totalHours)
                        .multiply(new BigDecimal(100).subtract(taxRate))
                        .divide(new BigDecimal(100));
            }
            dto.setEmployee_id(working.get(0).getEmployee_id());
            dto.setNet_salary(netSalary);
            dto.setTotal_tax(totalHours.multiply(new BigDecimal(100).subtract(taxRate)).divide(new BigDecimal(100)));
            listPayroll.add(dto);
        }
        return listPayroll;
    }
    // tính tổng số giờ của nhân viên
    public BigDecimal sumTotalHoursWorking(List<WorkingTimeDTO> timeTrackings) {
        BigDecimal workingTime = BigDecimal.ZERO;
        for (WorkingTimeDTO timeTracking : timeTrackings) {
            int dayOfWeek = timeTrackingService.dayOfWeek(timeTracking.getDate_track());
            switch (dayOfWeek) {
                case 1:
                    workingTime = workingTime.add(timeTracking.getTotal_hours().multiply(new BigDecimal(2)));
                    break;
                case 7:
                    workingTime = workingTime.add(timeTracking.getTotal_hours().multiply(new BigDecimal(1.5)));
                    break;
                default:
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

                    String clockInString = timeTracking.getClock_in();
                    String clockOutString = timeTracking.getClock_out();

                    LocalTime clockInTime = LocalTime.parse(clockInString, timeFormatter);
                    LocalTime clockOutTime = LocalTime.parse(clockOutString, timeFormatter);

                    LocalTime startTime = LocalTime.parse("20:00:00", timeFormatter);
                    LocalTime endTime = LocalTime.parse("06:00:00", timeFormatter);

                    if ((clockInTime.isAfter(startTime) || clockInTime.equals(startTime))
                            && (clockOutTime.isBefore(endTime) || clockOutTime.equals(endTime))) {
                        workingTime = workingTime.add(timeTracking.getTotal_hours().multiply(new BigDecimal(1.5)));
                    } else {
                        workingTime = workingTime.add(timeTracking.getTotal_hours());
                    }
                    break;
            }
        }
        return workingTime;
    }
    // lấy tháng hiện tại
    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0 nên cần cộng thêm 1
    }

    // tìm và set up các thông tin liên quan đến top2 nhân viên có lương cao nhất tháng
    public List<PayrollEmployee> top2Employees(List<PayrollEmployee> listPayroll){
        // Tìm top2 nhân viên có luong cao nhất
        List<PayrollEmployee> top2 = listPayroll.stream()
                .sorted(Comparator.comparing(PayrollEmployee::getNet_salary).reversed())
                .limit(2)
                .collect(Collectors.toList());
        // set up các trường cần thiết cho top 2
        top2.forEach(y ->{
            Employees employee = employeesMapper.selectByPrimaryKey(y.getEmployee_id());
            String insuranceType = employee.getInsuranceIds();
            int[] insurance = null;
            if(!insuranceType.equals("null")){
                insurance = getSo(insuranceType);
                for (int id : insurance) {
                    InsuranceType insuranceType1 = insuranceTypeMapper.selectByPrimaryKey(id);
                    BigDecimal insuranceRate = insuranceType1.getInsuranceRate();
                    if (id == 1) {
                        y.setUnemployment_insurance(insuranceRate);
                    } else if (id == 2) {
                        y.setSocial_insurance(insuranceRate);
                    } else {
                        y.setHealth_insurance(insuranceRate);
                    }

                    if (y.getSocial_insurance() == null) {
                        y.setSocial_insurance(BigDecimal.ZERO);
                    }
                    if (y.getUnemployment_insurance() == null) {
                        y.setUnemployment_insurance(BigDecimal.ZERO);
                    }
                    if (y.getHealth_insurance() == null) {
                        y.setHealth_insurance(BigDecimal.ZERO);
                    }
                }
            }
            else {
                y.setUnemployment_insurance(new BigDecimal(0));
                y.setSocial_insurance(new BigDecimal(0));
                y.setHealth_insurance(new BigDecimal(0));
            }
            Departments departments = departmentsMapper.selectByPrimaryKey(employee.getDepartmentId());
            List<Payroll> listPayrolls = payrollMapper.getTopThreeLatestSalaries(employee.getId());
            y.setLast_name(employee.getLastName());
            y.setEmail(employee.getEmail());
            y.setFirst_name(employee.getFirstName());
            y.setDepartment_name(departments.getName());
            y.setListHistoryPayroll(listPayrolls);
            y.setPosition(employee.getPosition());
        });
        return top2;
    }

    public int[] getSo(String chuoi){
//        / Loại bỏ dấu "[" và "]" từ chuỗi
        chuoi = chuoi.replace("[", "").replace("]", "");
        // Phân tách chuỗi thành các phần tử, sử dụng dấu phẩy làm dấu phân cách
        String[] mangChuoi = chuoi.split(",");
        // Chuyển đổi mảng các chuỗi thành mảng các số nguyên
        int[] mangSoNguyen = new int[mangChuoi.length];
        for (int i = 0; i < mangChuoi.length; i++) {
            mangSoNguyen[i] = Integer.parseInt(mangChuoi[i]);
        }
        return mangSoNguyen;
    }
}
