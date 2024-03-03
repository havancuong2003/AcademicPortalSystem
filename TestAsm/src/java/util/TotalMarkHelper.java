/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import dal.MarkDBContext;
import java.text.DecimalFormat;
import java.util.ArrayList;
import model.Mark;

/**
 *
 * @author -MSI-
 */
public class TotalMarkHelper {
    // Hàm lấy ra kì học hiện tại thông qua ngày hiện tại

    public String getCurrentSemester() {

        return "sp24";
    }

    public boolean hasZeroValueForJPD(ArrayList<Mark> grades, String gradeCategory) {
        for (Mark grade : grades) {
            if (hasNullValue(grades, getCurrentSemester())) {
                if (grade.getGradeCategory().equals(gradeCategory)
                        && grade.getValue().equals("0")
                        && grade.getGroup().getCourse().getCode().contains("JPD")) {
                    return true;
                }
            }

        }
        return false;
    }

    public int calculateTotalClasses(ArrayList<Mark> grades) {
        int totalClasses = 0;
        for (Mark grade : grades) {
            if (grade.getValue() != null) {
                totalClasses++;
            }
        }
        return 24;
    }

    // Hàm tính tổng số buổi nghỉ
    public int calculateTotalAbsences(ArrayList<Mark> grades) {
        int totalAbsences = 0;
        for (Mark grade : grades) {
            if (grade.getValue() == null) {
                totalAbsences++;
            }
        }
        return 4;
    }

    // Hàm kiểm tra xem có mục nào chứa value null hay không
    public boolean hasNullValue(ArrayList<Mark> grades, String currentSemester) {
        for (Mark grade : grades) {
            if (grade.getGroup().getTerm().getId().equals(currentSemester) && grade.getValue() == null) {
                return true;
            }
        }
        return false;
    }

    // Hàm tính điểm theo quy tắc
    public double calculateGrade(String value, float weight) {
        if (value == null) {
            return 0;
        }
        return Double.parseDouble(value) * weight / 100.0;
    }

    // Hàm tính tổng của một loại điểm
    public double calculateCategoryTotal(ArrayList<Mark> grades, String category) {
        double total = 0;
        for (Mark grade : grades) {
            if (grade.getGradeCategory().equals(category)) {
                total += calculateGrade(grade.getValue(), grade.getWeight());
            }
        }
        return total;
    }

    public double calculateAverageValue(ArrayList<Mark> grades, String gradeCategory) {
        double totalValue = 0;
        int count = 0;

        for (Mark grade : grades) {
            if (grade.getGradeCategory().equals(gradeCategory)) {
                totalValue += Double.parseDouble(grade.getValue());
                count++;
            }
        }

        // Tránh chia cho 0
        if (count == 0) {
            return 0;
        }

        return totalValue / count;
    }

// Hàm tính total tổng
    public double calculateTotal(ArrayList<Mark> grades) {
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

    // Hàm kiểm tra xem một loại điểm pass hay NOT PASS
    public String checkPass(double grade) {
        if (grade == 0) {
            return "NOT PASS";
        } else if (grade < 5) {
            return "NOT PASS";
        } else {
            return "PASSED";
        }
    }

    public String getStatusMark(String username, int gid) {
        MarkDBContext mdbc = new MarkDBContext();
        ArrayList<Mark> markForTotal = mdbc.getMarkForTotal(username, gid);
        String getTotal = "";
        String currentSemester = getCurrentSemester();
        // Kiểm tra các điểm nếu có điểm nào là 0 thì in ra "NOT PASS"
        boolean allPassed = true;
        for (Mark grade : markForTotal) {
            if (calculateCategoryTotal(markForTotal, grade.getGradeCategory()) == 0) {
                allPassed = false;
                break;
            }
        }
        // Nếu tất cả điểm đều khác 0 hoặc không có giá trị null, tính total và kiểm tra kết quả
        if (hasNullValue(markForTotal, currentSemester)) {
            getTotal = ("Current studying ; 0.0; ");
        } else {
            double total = calculateTotal(markForTotal);
            DecimalFormat df = new DecimalFormat("#.##");
            if (calculateTotalAbsences(markForTotal) * 1.0 / calculateTotalClasses(markForTotal) * 1.0 > 0.2) {
                getTotal = ("NOT PASS;" + df.format(total) + ";absent");
            } else {
// all pass tuc nghia la tat ca cac dau diem deu khac 0
                if (allPassed) {
                    if (hasZeroValueForJPD(markForTotal, "Final Exam Resit")) {
                        getTotal = ("NOT PASS;" + df.format(total) + ";FE =0");
                    } else if (calculateAverageValue(markForTotal, "Final Exam Resit") < 4) {
                        getTotal = ("NOT PASS;" + df.format(total) + ";FE < 4");
                    } else {

                        getTotal = (checkPass(total) + ";" + df.format(total) + "; ");
                    }
                } // neu co 1 dau diem nao do khac  0 thi sao
                else {
                    if (hasZeroValueForJPD(markForTotal, "Final Exam") && calculateCategoryTotal(markForTotal, "Final Exam Resit") == 0) {
                        getTotal = ("NOT PASS;" + df.format(total) + ";FE =0 ");
                    } else if (hasZeroValueForJPD(markForTotal, "Final Exam Resit") && calculateCategoryTotal(markForTotal, "Final Exam Resit") != 0) {
                        getTotal = ("NOT PASS;" + df.format(total) + ";FE =0 ");
                    } else {
                        // neu final exam khac  0 va final exam resit  = 0 thi se tinh diem dua tren final exam
                        if (calculateCategoryTotal(markForTotal, "Final Exam") != 0 && calculateCategoryTotal(markForTotal, "Final Exam Resit") == 0) {
                            if (calculateAverageValue(markForTotal, "Final Exam") < 4) {
                                getTotal = ("NOT PASS;" + df.format(total) + ";FE < 4");
                            } else {
                                getTotal = (checkPass(total) + ";" + df.format(total) + "; ");
                            }
                        } // hoac la neu final exam  = 0 va final exam resit khac 0 thi se tinh diem dua tren final exam resit
                        else if (calculateCategoryTotal(markForTotal, "Final Exam Resit") != 0) {
                            // neu final exam resit  ma nho hon 4 thi faile
                            if (calculateAverageValue(markForTotal, "Final Exam Resit") < 4) {
                                getTotal = ("NOT PASS;" + df.format(total) + ";FE < 4");
                            } else {
                                getTotal = (checkPass(total) + ";" + df.format(total) + "; ");
                            }
                        } else {
                            getTotal = ("NOT PASS;" + df.format(total) + ";category = 0");
                        }
                    }

                }
            }

        }
        return getTotal;
    }

}
