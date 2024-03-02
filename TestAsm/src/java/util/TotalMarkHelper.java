/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;


import java.util.ArrayList;
import model.Mark;

/**
 *
 * @author -MSI-
 */
public class TotalMarkHelper {
        // Hàm lấy ra kì học hiện tại thông qua ngày hiện tại

    public static String getCurrentSemester() {
        
        return "fa23";
    }

    public static int calculateTotalClasses(ArrayList<Mark> grades) {
        int totalClasses = 0;
        for (Mark grade : grades) {
            if (grade.getValue() != null) {
                totalClasses++;
            }
        }
        return 24;
    }

    // Hàm tính tổng số buổi nghỉ
    public static int calculateTotalAbsences(ArrayList<Mark> grades) {
        int totalAbsences = 0;
        for (Mark grade : grades) {
            if (grade.getValue() == null) {
                totalAbsences++;
            }
        }
        return 4;
    }

    // Hàm kiểm tra xem có mục nào chứa value null hay không
    public static boolean hasNullValue(ArrayList<Mark> grades, String currentSemester) {
        for (Mark grade : grades) {
            if (grade.getGroup().getTerm().getId().equals(currentSemester) && grade.getValue() == null) {
                return true;
            }
        }
        return false;
    }

    // Hàm tính điểm theo quy tắc
    public static double calculateGrade(String value, float weight) {
        if (value == null) {
            return 0;
        }
        return Double.parseDouble(value) * weight / 100.0;
    }

    // Hàm tính tổng của một loại điểm
    public static double calculateCategoryTotal(ArrayList<Mark> grades, String category) {
        double total = 0;
        for (Mark grade : grades) {
            if (grade.getGradeCategory().equals(category)) {
                total += calculateGrade(grade.getValue(), grade.getWeight());
            }
        }
        return total;
    }

// Hàm tính total tổng
    public static double calculateTotal(ArrayList<Mark> grades) {
        double total = 0;
        ArrayList<String> categories = new ArrayList<>();
        boolean hasFEGrade = false;
        boolean hasFEResitGrade = false;
        double FEGrade = 0;
        double FEResitGrade = 0;

        for (Mark grade : grades) {
            String category = grade.getGradeCategory();
            if (!categories.contains(category)) {
                if (grade.getGradeCategory().equals("Final Exam")) {
                    hasFEGrade = true;
                    FEGrade = calculateCategoryTotal(grades, category);
                } else if (grade.getGradeCategory().equals("Final Exam Resit")) {
                    hasFEResitGrade = true;
                    FEResitGrade = calculateCategoryTotal(grades, category);
                } else {
                    total += calculateCategoryTotal(grades, category);
                }
                categories.add(category);
            }
        }

        if (hasFEGrade && !hasFEResitGrade) {
            total += FEGrade;
        } else if (hasFEGrade && hasFEResitGrade) {
            total += FEResitGrade;
        } else if (!hasFEGrade && hasFEResitGrade) {
            total += FEResitGrade;
        }

        return total;
    }

    // Hàm kiểm tra xem một loại điểm pass hay not pass
    public static String checkPass(double grade) {
        if (grade == 0) {
            return "not pass";
        } else if (grade < 5) {
            return "not pass";
        } else {
            return "pass";
        }
    }

}
