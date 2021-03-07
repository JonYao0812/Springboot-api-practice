package com.practice.api.jobadder.jobadderapi.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHelper {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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

    public boolean isCurrent(String startDateString, String endDateString){
        LocalDate startDate = LocalDate.parse(startDateString, formatter);
        LocalDate endDate = LocalDate.parse(endDateString, formatter);
        LocalDate today = LocalDate.now();
        return ( startDate.compareTo(today) >= 0 && endDate.compareTo(today) <= 0);

    }
}
