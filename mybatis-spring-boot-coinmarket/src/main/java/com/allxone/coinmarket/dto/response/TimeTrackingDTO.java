package com.allxone.coinmarket.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Timer;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeTrackingDTO {
    private Long id;

    private Long employeeId;

    private Date dateTrack;

    private String clockIn;

    private String clockOut;

    private BigDecimal totalHours;


}
