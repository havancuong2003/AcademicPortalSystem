/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author -MSI-
 */
public class Group implements IEntity {

    private int id;
    private String name;
    private Course course;
    private Term term;
    private Room room;
    private TimeSlot timeslot;
    private Teacher teacher;
    private Date timeStart;
    private Date timeEnd;
    private Date firstDay;
    private Date secondDay;

    public Group() {
    }

    public Group(int id, String name, Course course, Term term, Room room, TimeSlot timeslot, Teacher teacher, Date timeStart, Date timeEnd, Date firstDay, Date secondDay) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.term = term;
        this.room = room;
        this.timeslot = timeslot;
        this.teacher = teacher;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.firstDay = firstDay;
        this.secondDay = secondDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public TimeSlot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(TimeSlot timeslot) {
        this.timeslot = timeslot;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Date getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(Date firstDay) {
        this.firstDay = firstDay;
    }

    public Date getSecondDay() {
        return secondDay;
    }

    public void setSecondDay(Date secondDay) {
        this.secondDay = secondDay;
    }

}
