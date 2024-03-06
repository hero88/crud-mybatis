package com.allxone.coinmarket.service.impl;

import com.allxone.coinmarket.dto.response.*;
import com.allxone.coinmarket.mapper.*;
import com.allxone.coinmarket.model.*;
import com.allxone.coinmarket.service.CoinService;
import com.allxone.coinmarket.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
//        List<PayrollEmployee> top2Payroll = top2Employees(payrollDTOS);
//        companyDTO.setListTop2(top2Payroll);

        List<Payroll> payrolls = payrollMapper.getTopTwoPreviousMonthPayrolls();
        List<PayrollEmployee> top2s = getMapPayroll_PayrollEmployee(payrolls);
        companyDTO.setListTop2(top2s);
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
            dto.setTax_rate(taxRate);
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

    // tìm và set up các thông tin liên quan đến nhân viên ở tháng hiện tại
    public List<PayrollEmployee> newSalaryThisMonth(List<PayrollEmployee> listPayroll){

        listPayroll.forEach(y ->{
            Employees employee = employeesMapper.selectByPrimaryKey(y.getEmployee_id());
            String insuranceType = employee.getInsuranceIds();
            int[] insurance = null;
            if(!insuranceType.equals("null")){
                insurance = getSo(insuranceType);
                for (int id : insurance) {
                    InsuranceType insuranceType1 = insuranceTypeMapper.selectByPrimaryKey(id);
                    BigDecimal insuranceRate = insuranceType1.getInsuranceRate();
                    if (id == 1) {
                        y.setUnemployment_insurance(y.getNet_salary().multiply(insuranceRate).divide(new BigDecimal(100)));
                    } else if (id == 2) {
                        y.setSocial_insurance(y.getNet_salary().multiply(insuranceRate).divide(new BigDecimal(100)));
                    } else {
                        y.setHealth_insurance(y.getNet_salary().multiply(insuranceRate).divide(new BigDecimal(100)));
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
            listPayrolls.sort(Comparator.comparing(payroll -> {
                // Chuyển đổi Date thành LocalDateTime
                return payroll.getPeriodEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            }));
            y.setLast_name(employee.getLastName());
            y.setEmail(employee.getEmail());
            y.setFirst_name(employee.getFirstName());
            y.setDepartment_name(departments.getName());
            y.setListHistoryPayroll(listPayrolls);
            y.setEstimate_tax_contribution(((y.getNet_salary().multiply(y.getTax_rate())).divide(new BigDecimal(100))).add(y.getUnemployment_insurance().add(y.getHealth_insurance().add(y.getSocial_insurance()))));
            y.setPosition(employee.getPosition());
        });
        return listPayroll;
    }

    //  chuyển từ list Payroll sang  PayrollEmployee để trả về api
    public List<PayrollEmployee> getMapPayroll_PayrollEmployee(List<Payroll> listPayroll){
        List<PayrollEmployee> payrollEmployees = new ArrayList<>();
        // dùng for để duyệt mảng
        listPayroll.forEach(Payroll -> {
            // tạo mới PayrollEmployee để đấy vào  list mới
            PayrollEmployee payrollEmployee = new PayrollEmployee();
            Employees employees = employeesMapper.selectByPrimaryKey(Payroll.getEmployeeId());

            // truy xuất  thuế
            TaxInformationExample taxInformationSQL = new TaxInformationExample();
            taxInformationSQL.createCriteria().andEmployeeIdEqualTo(Payroll.getEmployeeId());
            List<TaxInformation> taxInformations = taxInformationMapper.selectByExample(taxInformationSQL);
            BigDecimal taxRate = new BigDecimal(0);
            // kiểm tra danh sách thuế trả về nếu có bản ghi thì set data nếu không thì mặc định là 0
            if(taxInformations.size() > 0) {
                taxRate = taxInformations.get(0).getTaxRate();
            }
            // lấy mà để tính phí
            String insuranceType = employees.getInsuranceIds();
            int[] insurance = null;
            if(!insuranceType.equals("null")){
                insurance = getSo(insuranceType);
                for (int id : insurance) {
                    InsuranceType insuranceType1 = insuranceTypeMapper.selectByPrimaryKey(id);
                    BigDecimal insuranceRate = insuranceType1.getInsuranceRate();
                    if (id == 1) {
                        payrollEmployee.setUnemployment_insurance(Payroll.getNetSalary().multiply(insuranceRate).divide(new BigDecimal(100)));
                    } else if (id == 2) {
                        payrollEmployee.setSocial_insurance(Payroll.getNetSalary().multiply(insuranceRate).divide(new BigDecimal(100)));
                    } else {
                        payrollEmployee.setHealth_insurance(Payroll.getNetSalary().multiply(insuranceRate).divide(new BigDecimal(100)));
                    }
                    if (payrollEmployee.getSocial_insurance() == null) {
                        payrollEmployee.setSocial_insurance(BigDecimal.ZERO);
                    }
                    if (payrollEmployee.getUnemployment_insurance() == null) {
                        payrollEmployee.setUnemployment_insurance(BigDecimal.ZERO);
                    }
                    if (payrollEmployee.getHealth_insurance() == null) {
                        payrollEmployee.setHealth_insurance(BigDecimal.ZERO);
                    }
                }
            }
            else {
                payrollEmployee.setUnemployment_insurance(new BigDecimal(0));
                payrollEmployee.setSocial_insurance(new BigDecimal(0));
                payrollEmployee.setHealth_insurance(new BigDecimal(0));
            }
            Departments departments = departmentsMapper.selectByPrimaryKey(employees.getDepartmentId());

            // lấy lương 3 tháng gẫn nhất để vẽ biểu đồ
            List<Payroll> listPayrolls = payrollMapper.getTopThreeLatestSalaries(employees.getId());
            listPayrolls.sort(Comparator.comparing(payroll -> {
                // Chuyển đổi Date thành LocalDateTime
                return payroll.getPeriodEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            }));
            payrollEmployee.setEmployee_id(Payroll.getEmployeeId());
            payrollEmployee.setNet_salary(Payroll.getNetSalary());
            payrollEmployee.setFirst_name(employees.getFirstName());
            payrollEmployee.setLast_name(employees.getLastName());
            payrollEmployee.setDepartment_name(departments.getName());
            payrollEmployee.setEmail(employees.getEmail());
            payrollEmployee.setTax_rate(taxRate.multiply(Payroll.getNetSalary()).divide(new BigDecimal(100)));
            payrollEmployee.setPosition(employees.getPosition());
            payrollEmployee.setListHistoryPayroll(listPayrolls);
            payrollEmployee.setEstimate_tax_contribution(((Payroll.getNetSalary().multiply(taxRate)).divide(new BigDecimal(100))).add(payrollEmployee.getUnemployment_insurance().add(payrollEmployee.getHealth_insurance().add(payrollEmployee.getSocial_insurance()))));
            payrollEmployees.add(payrollEmployee);
        });
        return payrollEmployees;
    }

    public int[] getSo(String chuoi) {
        chuoi = chuoi.replaceAll("\\s+", ""); // Xóa tất cả khoảng trắng trong chuỗi
        chuoi = chuoi.replaceAll("\\[", "");  // Xóa dấu "["
        chuoi = chuoi.replaceAll("\\]", "");  // Xóa dấu "]"

        String[] mangChuoi = chuoi.split(",");
        int[] mangSoNguyen = new int[mangChuoi.length];
        for (int i = 0; i < mangChuoi.length; i++) {
            try {
                mangSoNguyen[i] = Integer.parseInt(mangChuoi[i]);
            } catch (NumberFormatException e) {
                mangSoNguyen[i] = 0;
            }
        }
        return mangSoNguyen;
    }
}
