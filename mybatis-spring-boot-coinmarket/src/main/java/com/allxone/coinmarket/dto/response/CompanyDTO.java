package com.allxone.coinmarket.dto.response;

import com.allxone.coinmarket.dto.EmployeesDto;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CompanyDTO {
    private Long totalEmployees;
    private Long totalHoldings;
    private BigDecimal totalPayroll;
    private BigDecimal totalHour;
    private List<PayrollEmployee> listTop2;
}
