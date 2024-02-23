package com.allxone.coinmarket.service.impl;

import com.allxone.coinmarket.dto.response.TimeTrackingDTO;
import com.allxone.coinmarket.dto.response.WorkingTimeDTO;
import com.allxone.coinmarket.mapper.TimeTrackingMapper;
import com.allxone.coinmarket.model.TimeTracking;
import com.allxone.coinmarket.model.TimeTrackingExample;
import com.allxone.coinmarket.service.TimeTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class TimeTrackingServiceImpl implements TimeTrackingService {

    @Autowired
    TimeTrackingMapper mapper;

    @Override
    public TimeTracking save(TimeTracking tracking) {
        TimeTrackingExample example = new TimeTrackingExample();
        if(tracking.getId() == null){
            return create(tracking,example);
        }
        return update(tracking,example);
    }
    @Override
    public TimeTracking delete(Long id) {
        TimeTracking timeTracking = mapper.selectByPrimaryKey(id);
        Integer check = mapper.deleteByPrimaryKey(id);
        if(check !=null){
            return timeTracking;
        }
        return null;
    }

    @Override
    public TimeTracking getInformation(Long id) {
        TimeTracking timeTracking = mapper.selectByPrimaryKey(id);
        if(timeTracking!=null){
            return timeTracking;
        }
        return null;
    }

    @Override
    public List<TimeTracking> getListByDate(Date date) {
        TimeTrackingExample example = new TimeTrackingExample();
        example.createCriteria().andDateTrackEqualTo(date);
        List<TimeTracking> list = mapper.selectByExample(example);
        if(!list.isEmpty()){
            return list;
        }return null;
    }

    public TimeTracking create(TimeTracking tracking, TimeTrackingExample example){
        example.createCriteria().andEmployeeIdEqualTo(tracking.getEmployeeId()).andDateTrackEqualTo(tracking.getDateTrack());
        TimeTracking checkExist = mapper.selectByExample(example).get(0);
        if(checkExist==null){
            tracking.setDateTrack(new Date());
            tracking.setCreatedAt(new Date());
            tracking.setUpdatedAt(new Date());
            tracking.setTotalHours(BigDecimal.valueOf((convertToDate(tracking.getClockOut()).getTime() - convertToDate(tracking.getClockIn()).getTime())/3600000));
            Integer check = mapper.insert(tracking);
            if(check!=null){
                return tracking;
            }
        }
        return null;
    }

    public TimeTracking update(@NotNull TimeTracking tracking, TimeTrackingExample example){
        example.createCriteria().andIdEqualTo(tracking.getId());
        tracking.setTotalHours(BigDecimal.valueOf((convertToDate(tracking.getClockOut()).getTime() - convertToDate(tracking.getClockIn()).getTime())/3600000));
        tracking.setUpdatedAt(mapper.selectByExample(example).get(0).getCreatedAt());
        tracking.setUpdatedAt(new Date());
        tracking.setDateTrack(mapper.selectByExample(example).get(0).getDateTrack());
        tracking.setEmployeeId(mapper.selectByExample(example).get(0).getEmployeeId());
        mapper.updateByExample(tracking,example);
        return mapper.selectByExample(example).get(0);
    }

    @Override
    public List<WorkingTimeDTO> getAllWorkingTimeEmployee(Date date,Integer limit) {
        List<WorkingTimeDTO> list = mapper.findAllWorkingTimeEmployee(date, limit);
        if(list.isEmpty()){
            return null;
        }
        return list;
    }
    public Date  convertToDate(String str){
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date date = format.parse(str);
            return date;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

}
