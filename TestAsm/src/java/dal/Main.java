/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Mark;

/**
 *
 * @author -MSI-
 */
public class Main {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();

        // Tìm ngày đầu tiên của tuần (Thứ 2)
        LocalDate firstDayOfWeek = today;
        while (firstDayOfWeek.getDayOfWeek() != DayOfWeek.MONDAY) {
            firstDayOfWeek = firstDayOfWeek.minusDays(1);
        }

        // Tìm ngày cuối cùng của tuần (Chủ nhật)
        LocalDate lastDayOfWeek = today;
        while (lastDayOfWeek.getDayOfWeek() != DayOfWeek.SUNDAY) {
            lastDayOfWeek = lastDayOfWeek.plusDays(1);
        }

        // Định dạng ngày thành chuỗi "yyyy-MM-dd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String firstDayOfWeekStr = firstDayOfWeek.format(formatter);
        String lastDayOfWeekStr = lastDayOfWeek.format(formatter);

        // In ngày đầu tiên và ngày cuối cùng của tuần
        System.out.println("Ngày đầu tiên của tuần (Thứ 2): " + firstDayOfWeekStr);
        System.out.println("Ngày cuối cùng của tuần (Chủ nhật): " + lastDayOfWeekStr);
    }
}
