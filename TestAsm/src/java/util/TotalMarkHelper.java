/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import dal.MarkDBContext;
import java.util.ArrayList;
import model.Mark;

/**
 *
 * @author -MSI-
 */
public class TotalMarkHelper {
    // Hàm lấy ra kì học hiện tại thông qua ngày hiện tại

    public String getCurrentSemester() {

        return "4";
    }

    public boolean allNull(ArrayList<Mark> grades, String semester) {
        for (Mark grade : grades) {
            if (!grade.getGroup().getTerm().getId().equals(semester)) {
                return false;
            }
            if (grade.getValue() != null) {
                return false;
            }
        }
        return true;
    }
// check diem FE cua mon JPD xem co ton tai diem 0 hay khong

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
// tra ve tong so buoi hoc cua 1 mon

    public int calculateTotalClasses(String username, int gid) {
        MarkDBContext mdbc = new MarkDBContext();
        return mdbc.countTotalClass(username, gid);
    }

    // Hàm tính tổng số buổi nghỉ
    public int calculateTotalAbsences(String username, int gid) {
        MarkDBContext mdbc = new MarkDBContext();
        return mdbc.countAbsent(username, gid);
    }
// de check xem cac dau diem da full chua, neu chua thi de la current studying
    //  neu chua full nhung ton tai diem FE hoac FE Resit ( bo thi FE de thi FE resit)
    // day la da full . nen return false
    // chua full tuc la fe va fe resit deu khong ton tai va cac diem thanh phan chua full

    // tuc la diem fe  = null va fe resit = null
    public boolean hasNullValue(ArrayList<Mark> grades, String currentSemester) {
        boolean isFinalExamNull = true;
        boolean isFinalExamResitNull = true;

        for (Mark grade : grades) {
            // Kiểm tra điểm Final Exam và Final Exam Resit

            if (grade.getGradeCategory().equals("Final Exam")) {
                if (grade.getValue() != null) {
                    isFinalExamNull = false; // Nếu Final Exam không null, gán false
                }
            }
            if (grade.getGradeCategory().equals("Final Exam Resit")) {
                if (grade.getValue() != null) {
                    isFinalExamResitNull = false; // Nếu Final Exam Resit không null, gán false
                }
            }

        }
        // Trả về true nếu cả hai điểm đều là null
        return isFinalExamNull && isFinalExamResitNull && currentSemester.equals("4");
    }

    // Hàm tính điểm theo quy tắc
    public Double calculateGrade(String value, float weight) {
        if (value == null) {
            return null;
        }
        return Double.parseDouble(value) * weight / 100.0;
    }

    // Hàm tính tổng của một loại điểm
    public double checkGradeEqual4OrNot(ArrayList<Mark> grades, String category) {
        double total = 0;
        for (Mark grade : grades) {
            if (grade.getGradeCategory().equals(category)) {
                String gradeValue = grade.getValue();
                if (gradeValue != null) {
                    total = Double.parseDouble(gradeValue);
                }
            }
        }
        return total;
    }

    // Hàm tính tổng của một loại điểm
    public Double calculateCategoryForFE(ArrayList<Mark> grades, String category) {
        Double total = null; // Khởi tạo total là null

        for (Mark grade : grades) {
            if (grade.getGradeCategory().equals(category)) {
                Double gradeValue = calculateGrade(grade.getValue(), grade.getWeight());
                if (gradeValue != null) {
                    // Nếu total là null, gán total bằng gradeValue; nếu không, cộng thêm vào total
                    total = (total == null) ? gradeValue : total + gradeValue;
                }
            }
        }

        return total;
    }

    // ham tinh tung dau diem ( grade Category )
    public Double calculateAverageValue(ArrayList<Mark> grades, String gradeCategory) {
        double totalValue = 0;
        int count = 0;

        for (Mark grade : grades) {
            if (grade.getGradeCategory().equals(gradeCategory)) {
                if (grade.getValue() != null) {

                    totalValue += Double.parseDouble(grade.getValue());
                    count++;
                } else {
                    return null;
                }
            }
        }

        // Tránh chia cho 0
        if (count == 0) {
            return null;
        }

        return totalValue / count;
    }

// tinh total cua tung loai category va check xem co cai nao la 0 hay khong. neu co return true
    // neu khong return false
    public boolean isCategoryTotalZero(ArrayList<Mark> grades) {
        boolean normalMark = false;
        for (Mark grade : grades) {
            if (!grade.getGradeCategory().equals("Final Exam") && !grade.getGradeCategory().equals("Final Exam Resit")) {
                Double averageValue = calculateAverageValue(grades, grade.getGradeCategory());
                if (averageValue != null && averageValue == 0) {
                    normalMark = true;
                }
            }
        }
        Double finalExamResitAverage = calculateAverageValue(grades, "Final Exam Resit");
        if (finalExamResitAverage != null && finalExamResitAverage != 0) {
            return true && normalMark;
        }
        Double finalExamAverage = calculateAverageValue(grades, "Final Exam");
        return finalExamAverage == null || finalExamAverage == 0;
    }

    public double calculateTotal(ArrayList<Mark> grades) {
        double total = 0;
        boolean hasFEResitGrade = false;

        double FEmark = 0;
        for (Mark grade : grades) {

            Double calculateMark = calculateGrade(grade.getValue(), grade.getWeight());
            if (calculateMark != null) {
                total += calculateMark;
            }
            if (grade.getGradeCategory().equals("Final Exam") && grade.getValue() != null) {
                FEmark = calculateMark;
            }
            if (grade.getGradeCategory().equals("Final Exam Resit") && grade.getValue() != null) {
                hasFEResitGrade = true;
            }
        }

        // Nếu có Final Exam Resit, trừ Final Exam nếu tồn tại
        if (hasFEResitGrade) {
            total -= FEmark;
        }

        return total;
    }

    // Hàm kiểm tra xem một loại điểm pass hay NOT PASS
    public String checkPass(double grade) {
        if (grade < 5) {
            return "NOT PASS";
        } else {
            return "PASSED";
        }
    }

    public String getStatusMark(String username, int gid) {
        MarkDBContext mdbc = new MarkDBContext();
        ArrayList<Mark> markForTotal = mdbc.getMarkForTotal("cuonghv", gid);
        String getTotal = "";
        String currentSemester = getCurrentSemester();
        if (allNull(markForTotal, "4")) {
            getTotal = "current studying;0.0; ";
        } else if (hasNullValue(markForTotal, currentSemester)) {
            getTotal = "NOT PASS;0.0; ";
        } else {
            if (calculateCategoryForFE(markForTotal, "Final Exam Resit") == null) {
                if (calculateTotalAbsences(username, gid) * 1.0 / calculateTotalClasses(username, gid) > 0.2) {
                    getTotal = "NOT PASS ;" + calculateTotal(markForTotal) + ";absent > 20%";
                } else if (checkGradeEqual4OrNot(markForTotal, "Final Exam") < 4) {
                    getTotal = "NOT PASS ;" + calculateTotal(markForTotal) + ";FE < 4";
                } else if (calculateTotal(markForTotal) < 5) {
                    getTotal = "NOT PASS ;" + calculateTotal(markForTotal) + ";Total < 5";
                } else if (isCategoryTotalZero(markForTotal)) {
                    getTotal = "NOT PASS ;" + calculateTotal(markForTotal) + ";Category = 0";
                } else {
                    getTotal = "PASSED ;" + calculateTotal(markForTotal) + "; ";
                }
            } else if (calculateCategoryForFE(markForTotal, "Final Exam Resit") != null) {
                if (calculateTotalAbsences(username, gid) * 1.0 / calculateTotalClasses(username, gid) > 0.2) {
                    getTotal = "NOT PASS ;" + calculateTotal(markForTotal) + ";absent > 20%";
                } else if (checkGradeEqual4OrNot(markForTotal, "Final Exam Resit") < 4) {
                    getTotal = "NOT PASS ;" + calculateTotal(markForTotal) + ";FE < 4";
                } else if (calculateTotal(markForTotal) < 5) {
                    getTotal = "NOT PASS ;" + calculateTotal(markForTotal) + ";Total < 5";
                } else if (isCategoryTotalZero(markForTotal)) {
                    getTotal = "NOT PASS ;" + calculateTotal(markForTotal) + ";Category = 0";
                } else {
                    getTotal = "PASSED ;" + calculateTotal(markForTotal) + "; ";
                }
            }
        }
        // Nếu chưa full điểm và thuộc kì hiện tại thì in ra "current studying"
        return getTotal;
    }

}
