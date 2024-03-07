package com.allxone.coinmarket.dto.response;

import com.allxone.coinmarket.model.Payroll;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Data
public class PayrollEmployee {

    private Long employee_id;

    private String department_name;

    private String first_name;

    private String last_name;

    private String email;

    private BigDecimal net_salary;

    private BigDecimal gross_salary;

    private BigDecimal totalHours;

    private BigDecimal tax_rate;

    private BigDecimal estimate_tax_contribution;

    private BigDecimal unemployment_insurance;

    private BigDecimal social_insurance;

    private BigDecimal health_insurance;

    private String position;

    private List<Payroll> listHistoryPayroll;
}
