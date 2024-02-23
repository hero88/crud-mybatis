package com.allxone.coinmarket.service;

import com.allxone.coinmarket.dto.response.WorkingTimeDTO;
import com.allxone.coinmarket.model.TimeTracking;

import java.util.Date;
import java.util.List;

public interface TimeTrackingService {
    public TimeTracking save(TimeTracking tracking);

    public TimeTracking delete(Long id);

    public TimeTracking getInformation(Long id);

    public List<TimeTracking> getListByDate(Date date);

    public List<WorkingTimeDTO> getAllWorkingTimeEmployee(Date date,Integer limit);
}
