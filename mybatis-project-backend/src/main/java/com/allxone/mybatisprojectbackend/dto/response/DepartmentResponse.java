package com.allxone.mybatisprojectbackend.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentResponse {
    private Integer id;
    private String name;
}
