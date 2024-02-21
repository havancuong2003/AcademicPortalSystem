CREATE TABLE Student (
    id INT PRIMARY KEY,
    name NVARCHAR(255)
);

CREATE TABLE Teacher (
    id INT PRIMARY KEY,
    name NVARCHAR(255)
);

CREATE TABLE Course (
    id INT PRIMARY KEY,
    name NVARCHAR(255)
);

CREATE TABLE Department (
    id INT PRIMARY KEY,
    name NVARCHAR(255)
);

CREATE TABLE [Group] (
    id INT PRIMARY KEY,
    name NVARCHAR(255),
    course_id INT,
    FOREIGN KEY (course_id) REFERENCES Course(id)
);

CREATE TABLE TimeSlot (
    id INT PRIMARY KEY,
    description NVARCHAR(255)
);

CREATE TABLE Room (
    id INT PRIMARY KEY,
    name NVARCHAR(255)
);

CREATE TABLE Term (
    id INT PRIMARY KEY,
    name NVARCHAR(255)
);

CREATE TABLE Session (
    id INT PRIMARY KEY,
    status NVARCHAR(255),
    group_id INT,
    room_id INT,
    timeSlot_id INT,
    lecture_id INT,
    date DATE,
    FOREIGN KEY (group_id) REFERENCES [Group](id),
    FOREIGN KEY (room_id) REFERENCES Room(id),
    FOREIGN KEY (timeSlot_id) REFERENCES TimeSlot(id),
    FOREIGN KEY (lecture_id) REFERENCES Teacher(id)
);

CREATE TABLE Attendance (
    id INT identity(1,1) PRIMARY KEY,
    status NVARCHAR(255),
    description NVARCHAR(255),
    student_id INT,
    session_id INT,
    FOREIGN KEY (student_id) REFERENCES Student(id),
    FOREIGN KEY (session_id) REFERENCES Session(id)
);

create table Student_course(
̣studentid int,
courseid int,
FOREIGN KEY (studentid) REFERENCES Student(id),
FOREIGN KEY (courseid) REFERENCES course(id)
);

