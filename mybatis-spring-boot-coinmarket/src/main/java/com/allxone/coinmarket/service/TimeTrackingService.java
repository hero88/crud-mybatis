package com.allxone.coinmarket.service;

import java.math.BigDecimal;

public interface TimeTrackingService {
    BigDecimal sumTotalHoursWorking(Long id,int month);
}
