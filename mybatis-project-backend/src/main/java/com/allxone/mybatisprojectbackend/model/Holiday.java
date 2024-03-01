package com.allxone.mybatisprojectbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Holiday {
    private Integer id;
    private String holidayName;
    private String holidayDescription;
    private Short durationDays;
}
