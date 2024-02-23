package com.allxone.coinmarket.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkingTimeDTO {
    private Long id;

    private Long employee_id;

    private String first_name;

    private String last_name;

    private Date date_track;

    private String clock_in;

    private String clock_out;

    private BigDecimal total_hours;
}
