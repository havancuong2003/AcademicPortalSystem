/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

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

    public  int getWeekOfYear(LocalDate date) {
        DayOfWeek firstDayOfYear = date.withDayOfYear(1).getDayOfWeek(); // Ngày đầu tiên của năm
        int daysToAdd = DayOfWeek.MONDAY.getValue() - firstDayOfYear.getValue(); // Số ngày cần thêm để đưa về Thứ Hai
        
        if (daysToAdd < 0) {
            daysToAdd += 7; // Đảm bảo số ngày cần thêm là dương
        }

        int daysSinceFirstMonday = date.getDayOfYear() + daysToAdd - 1; // Số ngày tính từ Thứ Hai đầu tiên của năm
        int weekNumber = daysSinceFirstMonday / 7 + 1; // Tính số tuần bằng cách chia số ngày cho 7
        
        return weekNumber;
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
