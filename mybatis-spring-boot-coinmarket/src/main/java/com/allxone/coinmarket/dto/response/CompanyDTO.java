package com.allxone.coinmarket.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CompanyDTO {
    private Long totalEmployees;
    private Long totalHoldings;
    private BigDecimal totalPayroll;
}
