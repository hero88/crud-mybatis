package com.allxone.mybatisprojectbackend.service.Impl;

import com.allxone.mybatisprojectbackend.convert.TimeTrackingConvert;
import com.allxone.mybatisprojectbackend.dto.request.TimeTrackingRequest;
import com.allxone.mybatisprojectbackend.dto.response.TimeTrackingResponse;
import com.allxone.mybatisprojectbackend.mapper.InsuranceTypeMapper;
import com.allxone.mybatisprojectbackend.mapper.PayrollMapper;
import com.allxone.mybatisprojectbackend.mapper.TaxInformationMapper;
import com.allxone.mybatisprojectbackend.mapper.TimeTrackingMapper;
import com.allxone.mybatisprojectbackend.model.InsuranceType;
import com.allxone.mybatisprojectbackend.model.Payroll;
import com.allxone.mybatisprojectbackend.model.TaxInformation;
import com.allxone.mybatisprojectbackend.model.TimeTracking;
import com.allxone.mybatisprojectbackend.service.TimeTrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimeTrackingServiceImpl implements TimeTrackingService {

    private final TimeTrackingMapper timeTrackingMapper;
    private final PayrollMapper payrollMapper;
    private final TaxInformationMapper taxInformationMapper;
    private final InsuranceTypeMapper insuranceTypeMapper;

    @Override
    public List<TimeTrackingResponse> getAllTimeTracking() {
        return timeTrackingMapper.getAllTimeTracking()
                .stream()
                .map(TimeTrackingConvert::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TimeTrackingResponse updateTimeTracking(TimeTrackingRequest timeTrackingRequest) {
        TimeTracking timeTracking = TimeTrackingConvert.toTimeTracking(timeTrackingRequest);
        timeTrackingMapper.updateTimeTracking(timeTracking);
        return getTimeTrackingById(timeTracking.getId());
    }

    @Override
    public void deleteTimeTrackingById(Long id) {
        timeTrackingMapper.deleteTimeTrackingById(id);
    }

    @Override
    public TimeTrackingResponse saveTimeTracking(TimeTrackingRequest timeTrackingRequest) {
        TimeTracking timeTracking = TimeTrackingConvert.toTimeTracking(timeTrackingRequest);
        try {
            timeTrackingMapper.saveTimeTracking(timeTracking);
            //check sa-suDay
            int dayOfWeekValue = timeTracking.getDateTrack().getDayOfWeek().getValue();
            int clockIn = timeTracking.getClockIn().getHour();
            int clockOut = timeTracking.getClockOut().getHour();

            Payroll payrollEmployee =
                    payrollMapper.getPayrollByEmployeeId(timeTracking.getEmployeeId());
            Double insuranceRate =
                    insuranceTypeMapper.getInsuranceTypeByEmployeeId(timeTracking.getEmployeeId())
                            .stream()
                            .mapToDouble(InsuranceType::getInsuranceRate)
                            .reduce(0.0,Double::sum);
            TaxInformation taxInformation =
                    taxInformationMapper.getTaxInformationByEmployeeId(timeTracking.getEmployeeId());

            if (timeTracking.getDateTrack().getMonth() != payrollEmployee.getPeriodEnd().getMonth()) {
                payrollEmployee.setPeriodStart(timeTracking.getDateTrack());
                payrollEmployee.setSalary(0.0);
            }
            if (dayOfWeekValue == 7) {
                payrollEmployee.setNetSalary(getNetSalary(payrollEmployee,
                        timeTracking.getTotalHours(),
                        1.5,
                        taxInformation.getTaxRate(),
                        insuranceRate));
            } else if (dayOfWeekValue == 8) {
                payrollEmployee.setNetSalary(getNetSalary(payrollEmployee,
                        timeTracking.getTotalHours(),
                        2.0,
                        taxInformation.getTaxRate(),
                        insuranceRate));
            } else if (clockIn >= 20) {
                if (clockOut < 6) {
                    payrollEmployee.setNetSalary(getNetSalary(payrollEmployee,
                            getTotalNumberOfWorkingHours8pmTo6am(
                                    timeTracking.getClockIn(),
                                    timeTracking.getClockOut()
                            ),
                            1.0,
                            taxInformation.getTaxRate(),
                            insuranceRate));
                } else {
                    double firstPartHours = getTotalNumberOfWorkingHours8pmTo6am(
                            timeTracking.getClockIn(),
                            LocalTime.of(6, 0)
                    );
                    double secondPartHours = getTotalNumberOfWorkingHours8pmTo6am(
                            LocalTime.of(6, 0),
                            timeTracking.getClockOut()
                    );
                    double firstPartSalary = getNetSalary(
                            payrollEmployee,
                            firstPartHours,
                            1.5,
                            taxInformation.getTaxRate(),
                            insuranceRate
                    );
                    double secondPartSalary = getNetSalary(
                            payrollEmployee,
                            secondPartHours,
                            1.0,
                            taxInformation.getTaxRate(),
                            insuranceRate
                    );
                    payrollEmployee.setNetSalary(firstPartSalary + secondPartSalary);
                }

            } else {
                payrollEmployee.setNetSalary(getNetSalary(payrollEmployee,
                        timeTracking.getTotalHours(),
                        1.0,
                        taxInformation.getTaxRate(),
                        insuranceRate));
            }
            payrollEmployee.setPeriodEnd(timeTracking.getDateTrack());
            payrollMapper.updatePayroll(payrollEmployee);
        } catch (Exception e) {
            System.out.println(e);
        }
        return getTimeTrackingById(timeTracking.getId());
    }

    @Override
    public TimeTrackingResponse getTimeTrackingById(Long id) {
        TimeTracking timeTracking = timeTrackingMapper.getTimeTrackingById(id);
        return TimeTrackingConvert.toDto(timeTracking);
    }

    public Double getNetSalary(Payroll payroll,
                               Double hour,
                               Double bonus,
                               Double taxRate,
                               Double insuranceRate) {
        return payroll.getNetSalary()
                + (payroll.getSalary() * hour * (100 - taxRate - insuranceRate)) / 100
                + bonus
                - payroll.getDeductions();
    }

    public Double getTotalNumberOfWorkingHours8pmTo6am(LocalTime startTime, LocalTime endTime) {
        long minutes = startTime.until(endTime, ChronoUnit.MINUTES);
        return minutes / 60.0;
    }

}

