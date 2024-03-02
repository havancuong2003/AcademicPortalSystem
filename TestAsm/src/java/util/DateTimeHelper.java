/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

public class DateTimeHelper {

    public LocalDate getFirstDayOfWeek(LocalDate date) {
        LocalDate firstDayOfWeek = date;
        while (firstDayOfWeek.getDayOfWeek() != DayOfWeek.MONDAY) {
            firstDayOfWeek = firstDayOfWeek.minusDays(1);
        }
        return firstDayOfWeek;
    }

    public LocalDate getLastDayOfWeek(LocalDate date) {
        LocalDate lastDayOfWeek = date;
        while (lastDayOfWeek.getDayOfWeek() != DayOfWeek.SUNDAY) {
            lastDayOfWeek = lastDayOfWeek.plusDays(1);
        }
        return lastDayOfWeek;
    }

    public int getWeekOfYear(LocalDate date) {

        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return date.get(weekFields.weekOfWeekBasedYear());
    }

    public ArrayList<java.sql.Date> getWeekDaysAsSqlDates(LocalDate date) {
        LocalDate firstDayOfWeek = getFirstDayOfWeek(date);
        LocalDate lastDayOfWeek = getLastDayOfWeek(date);
        
        ArrayList<java.sql.Date> weekDays = new ArrayList<>();
        while (!firstDayOfWeek.isAfter(lastDayOfWeek)) {
            weekDays.add(java.sql.Date.valueOf(firstDayOfWeek));
            firstDayOfWeek = firstDayOfWeek.plusDays(1);
        }
        return weekDays;
    }

}
