package com.allxone.coinmarket.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PayrollDTO {

    private Long id;

    private String first_name;

    private String last_name;

    private String email;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "vi-VN", timezone = "Asia/Ho_Chi_Minh")
    private Date hire_date;

    private BigDecimal salary;

    private BigDecimal net_salary;

}
