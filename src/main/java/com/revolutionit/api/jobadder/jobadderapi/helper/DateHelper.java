package com.revolutionit.api.jobadder.jobadderapi.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHelper {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public boolean isCurrentProject(String startDate, String endDate){
        LocalDate today = LocalDate.now();

        LocalDate sd = LocalDate.parse(startDate, formatter);
        LocalDate ed = LocalDate.parse(endDate, formatter);
        return ( today.compareTo(sd) >= 0 && today.compareTo(ed) <= 0 );
    }

    public String calculateDate(String dateString, int number, String unit) {
        LocalDate date = LocalDate.parse(dateString, formatter);
        LocalDate newDate;
        switch(unit.toLowerCase()){
            case "year":
                newDate = date.plusYears(number);
                break;
            case "month":
                newDate = date.plusMonths(number);
                break;
            case "day":
                newDate = date.plusDays(number);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + unit.toLowerCase());
        }
        return formatter.format(newDate);
    }
}
