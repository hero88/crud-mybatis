package com.allxone.coinmarket.utilities;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

@Service
public class TimeUtils {
	
	public List<Long> getListTimeStampEnd12MonthsAgo(){
		List<Long> list= new ArrayList<>();
		for (int i=12; i>0;i--) {
			Instant time = LocalDate.now()
	                .minusMonths(i)
	                .withDayOfMonth(1)
	                .atStartOfDay(ZoneId.of("UTC"))
	                .toInstant();
	        long timestampTwelveMonthsAgo = time.getEpochSecond();
	        list.add(timestampTwelveMonthsAgo);
		}
		
        Instant nowInstant = Instant.now();
        long timestampNow = nowInstant.getEpochSecond();
        list.add(timestampNow);
        return list;
	}
	
	public List<String> getEnglishMonthNamesLast12Months() {
        List<String> monthNames = new ArrayList<>();
        for (int i = 12; i > 0; i--) {
            LocalDate monthStart = LocalDate.now().minusMonths(i).withDayOfMonth(1);
            String monthName = monthStart.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            monthNames.add(monthName);
        }

        return monthNames;
    }
}
