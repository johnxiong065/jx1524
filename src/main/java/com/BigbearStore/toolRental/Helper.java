package com.BigbearStore.toolRental;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class Helper {
    public static boolean isHoliday(LocalDate date) {
        return  isIndependenceDay(date) || isLaborDay(date) ;
    }

    private static boolean isLaborDay(LocalDate date) {
        LocalDate laborDay = LocalDate.of(date.getYear(), Month.SEPTEMBER, 1);
        while (laborDay.getDayOfWeek() != DayOfWeek.MONDAY) {
            laborDay = laborDay.plusDays(1);
        }
        return date.equals(laborDay);
    }

    private static boolean isIndependenceDay(LocalDate date) {
        LocalDate independenceDay = LocalDate.of(date.getYear(), Month.JULY, 4);
        if (independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            independenceDay = independenceDay.minusDays(1);
        } else if (independenceDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            independenceDay = independenceDay.plusDays(1);
        }
        return date.equals(independenceDay);
    }
}
