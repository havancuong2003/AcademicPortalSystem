﻿
create table [role] (
id int primary key,
func nvarchar(50)
)

create table account(
userName nvarchar(50) primary key,
[password] nvarchar(50),
rolid int,
Foreign key (rolid) references [role](id)
)


CREATE TABLE Student (
    id  nvarchar(50) primary key,
    [name] NVARCHAR(255),
	dob date,
	email nvarchar(50),
	imgUrl varchar(MAX),
	userName nvarchar(50),
Foreign key (userName) references account(username)
);
ALTER TABLE Student
ADD userName nvarchar(50);

ALTER TABLE Student
ADD CONSTRAINT FK_Student_Account FOREIGN KEY (userName) REFERENCES account(userName);


ALTER TABLE Lecture
ADD userName nvarchar(50);

ALTER TABLE Lecture
ADD CONSTRAINT FK_Teacher_Account FOREIGN KEY (userName) REFERENCES account(userName);



CREATE TABLE Lecture (
    id  nvarchar(50) primary key,
    [name] NVARCHAR(255),
	dob date,
	email nvarchar(50),
	userName nvarchar(50),
Foreign key (userName) references account(username)
);

CREATE TABLE Course (
    id INT identity(1,1) PRIMARY KEY,
    code NVARCHAR(255),
	 [description] NVARCHAR(255)
);


Create table Term (
id varchar(50) primary key,
[description] nvarchar(255),
timeStart date,
timeEnd date
);
CREATE TABLE TimeSlot (
     id INT identity(1,1) PRIMARY KEY,
    [description] NVARCHAR(255)
);

CREATE TABLE Room (
     id INT identity(1,1) PRIMARY KEY,
    [name] NVARCHAR(255)
);
CREATE TABLE [Group] (
    id INT identity(1,1) PRIMARY KEY,
    [name] NVARCHAR(255),
    courseId INT,
	termID varchar(50) ,
	roomID int,
	timeSlotID int,
	lectureid nvarchar(50),
    FOREIGN KEY (courseId) REFERENCES Course(id),
	FOREIGN KEY (termId) REFERENCES Term(id),
	FOREIGN KEY (roomid) REFERENCES Room(id),
	FOREIGN KEY (timeSlotID) REFERENCES TimeSlot(id),
	FOREIGN KEY (lectureid) REFERENCES lecture(id),
	timeStart date,
	timeEnd date,
	firstday date,
	secondday date
);


create table student_group(
	id int identity(1,1) primary key,
	Studentid nvarchar(50),
	groupid int,
    FOREIGN KEY (Studentid) REFERENCES Student(id),
	FOREIGN KEY (groupid) REFERENCES [group](id)
);


CREATE TABLE [Session] (
    id INT identity(1,1) PRIMARY KEY,
    [status] bit,
    group_id INT,
    [date] DATE,
    FOREIGN KEY (group_id) REFERENCES [Group](id),
);

CREATE TABLE Attendance (
    id INT identity(1,1) PRIMARY KEY,
    [status] NVARCHAR(255),
    [description] NVARCHAR(255),
    student_id nvarchar(50),
    session_id INT,
    FOREIGN KEY (student_id) REFERENCES Student(id),
    FOREIGN KEY (session_id) REFERENCES [Session](id)
);

create table mark(
id int identity(1,1) primary key,
student_group_id int,
FOREIGN KEY (student_group_id) REFERENCES student_group(id),
grade_category nvarchar(255),
grade_item nvarchar(255),
[weight] float,
[value] float,
comment text
);


