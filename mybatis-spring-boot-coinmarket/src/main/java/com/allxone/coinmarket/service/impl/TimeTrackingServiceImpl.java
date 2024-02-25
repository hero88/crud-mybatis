package com.allxone.coinmarket.service.impl;

import com.allxone.coinmarket.mapper.TimeTrackingMapper;
import com.allxone.coinmarket.model.TimeTracking;
import com.allxone.coinmarket.service.TimeTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.*;
import java.util.Date;
import java.util.List;

@Service
public class TimeTrackingServiceImpl implements TimeTrackingService {

    @Autowired
    private TimeTrackingMapper timeTrackingMapper;

    @Override
    public BigDecimal sumTotalHoursWorking(Long id, int month) {
        BigDecimal workingTime = BigDecimal.ZERO;
        List<TimeTracking> timeTrackings = timeTrackingMapper.getEmployeeIdWorkingTime(id, month);
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
                    Date clockIn = timeTracking.getClockIn();
                    Date clockOut = timeTracking.getClockOut();

                    ZonedDateTime clockInZonedDateTime = clockIn.toInstant().atZone(ZoneId.systemDefault());
                    ZonedDateTime clockOutZonedDateTime = clockOut.toInstant().atZone(ZoneId.systemDefault());

                    LocalTime clockInTime = clockInZonedDateTime.toLocalTime();
                    LocalTime clockOutTime = clockOutZonedDateTime.toLocalTime();

                    LocalTime startTime = LocalTime.parse("20:00");
                    LocalTime endTime = LocalTime.parse("06:00");

                    LocalDateTime clockInDateTime = LocalDateTime.of(clockInZonedDateTime.toLocalDate(), clockInTime);
                    LocalDateTime clockOutDateTime = LocalDateTime.of(clockOutZonedDateTime.toLocalDate(), clockOutTime);

                    if ((clockInDateTime.isAfter(startTime.atDate(clockInZonedDateTime.toLocalDate())) || clockInDateTime.equals(startTime.atDate(clockInZonedDateTime.toLocalDate())))
                            && (clockOutDateTime.isBefore(endTime.atDate(clockOutZonedDateTime.toLocalDate())) || clockOutDateTime.equals(endTime.atDate(clockOutZonedDateTime.toLocalDate())))) {
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
}
