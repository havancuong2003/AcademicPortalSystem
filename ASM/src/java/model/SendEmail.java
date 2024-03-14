/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author -MSI-
 */
public class SendEmail implements IEntity{
    private Student student;
    private Course course;
    private int absentCount;
    private int totalClasses;
    private boolean emailCheck;

    public SendEmail() {
    }

    public SendEmail(Student student, Course course, int absentCount, int totalClasses, boolean emailCheck) {
        this.student = student;
        this.course = course;
        this.absentCount = absentCount;
        this.totalClasses = totalClasses;
        this.emailCheck = emailCheck;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getAbsentCount() {
        return absentCount;
    }

    public void setAbsentCount(int absentCount) {
        this.absentCount = absentCount;
    }

    public int getTotalClasses() {
        return totalClasses;
    }

    public void setTotalClasses(int totalClasses) {
        this.totalClasses = totalClasses;
    }

    public boolean isEmailCheck() {
        return emailCheck;
    }

    public void setEmailCheck(boolean emailCheck) {
        this.emailCheck = emailCheck;
    }
    
    
    
    
}
