/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author -MSI-
 */
public class ChangeClass implements IEntity{
    private int id;
    private Student fromStudent;
    private Group fromGroup;
    private Student toStudent;
    private Group toGroup;
    private String status;

    public ChangeClass() {
    }

    public ChangeClass(int id, Student fromStudent, Group fromGroup, Student toStudent, Group toGroup, String status) {
        this.id = id;
        this.fromStudent = fromStudent;
        this.fromGroup = fromGroup;
        this.toStudent = toStudent;
        this.toGroup = toGroup;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getFromStudent() {
        return fromStudent;
    }

    public void setFromStudent(Student fromStudent) {
        this.fromStudent = fromStudent;
    }

    public Group getFromGroup() {
        return fromGroup;
    }

    public void setFromGroup(Group fromGroup) {
        this.fromGroup = fromGroup;
    }

    public Student getToStudent() {
        return toStudent;
    }

    public void setToStudent(Student toStudent) {
        this.toStudent = toStudent;
    }

    public Group getToGroup() {
        return toGroup;
    }

    public void setToGroup(Group toGroup) {
        this.toGroup = toGroup;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
