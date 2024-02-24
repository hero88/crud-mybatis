package com.allxone.coinmarket.service;

import com.allxone.coinmarket.dto.response.CompanyDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface CompanyService {
    CompanyDTO getCompany() throws IOException;
}
