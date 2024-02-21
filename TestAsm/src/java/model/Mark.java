/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author -MSI-
 */
public class Mark implements IEntity {

    private int id;
    private Student student;
    private Course course;
    private String gradeCategory;
    private String gradeItem;
    private float weight;
    private float value;
    private String comment;

    public Mark() {
    }

    public Mark(int id, Student student, Course course, String gradeCategory, String gradeItem, float weight, float value, String comment) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.gradeCategory = gradeCategory;
        this.gradeItem = gradeItem;
        this.weight = weight;
        this.value = value;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getGradeCategory() {
        return gradeCategory;
    }

    public void setGradeCategory(String gradeCategory) {
        this.gradeCategory = gradeCategory;
    }

    public String getGradeItem() {
        return gradeItem;
    }

    public void setGradeItem(String gradeItem) {
        this.gradeItem = gradeItem;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
