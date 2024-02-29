package com.allxone.coinmarket.service.impl;

import com.allxone.coinmarket.dto.response.CoinsUserReponse;
import com.allxone.coinmarket.dto.response.CompanyDTO;
import com.allxone.coinmarket.mapper.EmployeesMapper;
import com.allxone.coinmarket.mapper.PayrollMapper;
import com.allxone.coinmarket.mapper.TimeTrackingMapper;
import com.allxone.coinmarket.model.EmployeesExample;
import com.allxone.coinmarket.model.PayrollExample;
import com.allxone.coinmarket.model.TimeTrackingExample;
import com.allxone.coinmarket.service.CoinService;
import com.allxone.coinmarket.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceimpl implements CompanyService {
    private final EmployeesMapper employeesMapper;
    private final PayrollMapper payrollMapper;
    private final CoinService coinService;
    private final TimeTrackingMapper timeTrackingMapper;

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

        PayrollExample payrollExample = new PayrollExample();
        payrollExample.createCriteria()
                .andPeriodEndBetween(firstDayOfCurrentMonth, lastDayOfCurrentMonth);
        BigDecimal totalPayroll = payrollMapper.sumSalaryByMonth(payrollExample);
        companyDTO.setTotalPayroll(totalPayroll != null ? totalPayroll : BigDecimal.ZERO);

        TimeTrackingExample timeTrackingExample = new TimeTrackingExample();
        timeTrackingExample.createCriteria()
                .andDateTrackBetween(firstDayOfCurrentMonth, lastDayOfCurrentMonth);
        BigDecimal totalHour = timeTrackingMapper.sumTotalHoursMonth(timeTrackingExample);
        companyDTO.setTotalHour(totalHour != null ? totalHour : BigDecimal.ZERO);

        try {
            List<CoinsUserReponse> list = coinService.getAllCoinsUser();
            Long totalHolding = list.stream().mapToLong(response -> response.getAmount().longValue()).sum();
            companyDTO.setTotalHoldings(totalHolding);
        } catch (Exception ex) {
            ex.printStackTrace();
            companyDTO.setTotalHoldings(0L);
        }
        return companyDTO;
    }
}
