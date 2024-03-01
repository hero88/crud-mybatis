package com.allxone.coinmarket.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NegativeOrZero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeesDto {
	private Long id;

	private Long userId;

	private String firstName;

	private String lastName;

	private Date birthday;

	private String gender;

	private String contactNumber;

	private String email;

	private Integer departmentId;

	private String position;

	private Date hireDate;

	private Date terminationDate;

	private Date createdAt;

	private Date updatedAt;
	
    private BigDecimal taxRate;

    private Boolean taxExemption;
    
    private Integer taxInformationId;
    
    private Integer insuranceId;
    

}
