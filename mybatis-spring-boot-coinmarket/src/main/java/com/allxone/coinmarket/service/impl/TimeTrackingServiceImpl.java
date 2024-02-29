package com.allxone.coinmarket.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allxone.coinmarket.dto.response.WorkingTimeDTO;
import com.allxone.coinmarket.mapper.TimeTrackingMapper;
import com.allxone.coinmarket.model.TimeTracking;
import com.allxone.coinmarket.model.TimeTrackingExample;
import com.allxone.coinmarket.service.TimeTrackingService;
@Service
public class TimeTrackingServiceImpl implements TimeTrackingService {

    @Autowired
    TimeTrackingMapper mapper;

    @Override
    public BigDecimal sumTotalHoursWorking(Long id, int month) {
        BigDecimal workingTime = BigDecimal.ZERO;
        List<TimeTracking> timeTrackings = mapper.getEmployeeIdWorkingTime(id, month);
        for (TimeTracking timeTracking : timeTrackings) {
            int dayOfWeek = dayOfWeek(timeTracking.getDateTrack());

            switch (dayOfWeek) {
                case 1:
                    workingTime = workingTime.add(timeTracking.getTotalHours().multiply(new BigDecimal(2)));
                    break;
                case 7:
                    workingTime = workingTime.add(timeTracking.getTotalHours().multiply(new BigDecimal(1.5)));
                    break;
                default:
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

                    String clockInString = timeTracking.getClockIn();
                    String clockOutString = timeTracking.getClockOut();

                    LocalTime clockInTime = LocalTime.parse(clockInString, timeFormatter);
                    LocalTime clockOutTime = LocalTime.parse(clockOutString, timeFormatter);

                    LocalTime startTime = LocalTime.parse("20:00:00", timeFormatter);
                    LocalTime endTime = LocalTime.parse("06:00:00", timeFormatter);

                    if ((clockInTime.isAfter(startTime) || clockInTime.equals(startTime))
                            && (clockOutTime.isBefore(endTime) || clockOutTime.equals(endTime))) {
                        workingTime = workingTime.add(timeTracking.getTotalHours().multiply(new BigDecimal(1.5)));
                    } else {
                        workingTime = workingTime.add(timeTracking.getTotalHours());
                    }
                    break;
            }
        }
        return workingTime;
    }

    public static int dayOfWeek(Date day) {
        LocalDateTime dateTime = day.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDate date = dateTime.toLocalDate();
        int dayOfWeek = date.getDayOfWeek().getValue();
        int adjustedDayOfWeek = (dayOfWeek == 7) ? 1 : dayOfWeek + 1;
        return adjustedDayOfWeek;
    }
    

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
        Integer checkExist = mapper.selectByExample(example).size();
        if(checkExist==0){
            tracking.setDateTrack(tracking.getDateTrack());
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
    @Override
    public List<WorkingTimeDTO> getAllWorkingTimeEmployeeAllTime(Integer limit) {
        List<WorkingTimeDTO> list = mapper.findAllWorkingTimeEmployeeAllTime(limit);
        if(list.isEmpty()){
            return null;
        }
        return list;
    }

    @Override
    public List<WorkingTimeDTO> getAllWorkingTimeEmployeeByFilter(List<Integer> id, Date from, Date to,Integer limit) {
        System.out.println(mapper.findAllWorkingTimeEmployeeByFilter(id, from, to, limit).toString());
        return mapper.findAllWorkingTimeEmployeeByFilter(id, from, to, limit);
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
