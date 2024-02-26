package com.allxone.coinmarket.dto.response;

import java.util.List;

import javax.validation.constraints.NegativeOrZero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResult<T> {
	private List<T> data;
	private int totalPages;

}
