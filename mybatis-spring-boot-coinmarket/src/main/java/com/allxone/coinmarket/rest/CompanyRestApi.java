package com.allxone.coinmarket.rest;

import com.allxone.coinmarket.dto.response.ApiResponse;
import com.allxone.coinmarket.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/company")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CompanyRestApi {
    private final CompanyService companyService;
    @GetMapping
    public ResponseEntity<?> getCompany() throws IOException {
        return ResponseEntity.ok(ApiResponse.builder().data(companyService.getCompany()).success(true).message("Get data susses").build());
    }
}
