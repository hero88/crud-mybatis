package com.allxone.coinmarket.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.allxone.coinmarket.dto.response.WorkingTimeDTO;
import com.allxone.coinmarket.model.TimeTracking;

public interface TimeTrackingService {
    public TimeTracking save(TimeTracking tracking);

    public TimeTracking delete(Long id);

    public TimeTracking getInformation(Long id);

    public List<TimeTracking> getListByDate(Date date);

    public List<WorkingTimeDTO> getAllWorkingTimeEmployee(Date date,Integer limit);

    List<WorkingTimeDTO> getAllWorkingTimeEmployeeAllTime(Integer limit);

    List<WorkingTimeDTO> getAllWorkingTimeEmployeeByFilter(List<Integer> id, Date from, Date to,Integer limit);
    
    BigDecimal sumTotalHoursWorking(Long id,int month);
}
