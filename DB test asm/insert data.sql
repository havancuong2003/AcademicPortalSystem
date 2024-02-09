

insert into student values
(1,'Ha Van Cuong'),
(2,'Nguyen Thi Dieu Linh'),
(3,'Dinh Gian Han'),
(4,'Do Thuy Linh'),
(5,'Ha Van Nam'),
(6,'Ha Thi Thu'),
(7,'Trinh Thi Trang')


insert into teacher values
(1,'AnhNN'),
(2,'Sonnt'),
(3,'Cuonghv'),
(4,'linhdt'),
(5,'thuht'),
(6,'namnh')


insert into course values
(1,'MAS'),
(2,'PRJ'),
(3,'WED'),
(4,'JPD'),
(5,'CSD')

insert into  timeslot values
(1,'7h30-10h'),
(2,'10h-12h30'),
(3,'12h40-3h10'),
(4,'3h10-5h40')


insert into [group] values
(1,'se1817',2),
(2,'se1817',4),
(3,'se1817',5),
(4,'se1817',1),
(5,'se1817',3),
(6,'se1801',2),
(7,'se1802',2)

insert into room values
(1,'be101'),
(2,'be102'),
(3,'al201'),
(4,'al202'),
(5,'de201')
select * from session

insert into [session] values
(1,'true',1,1,1,2,'2023-12-15'),
(2,'true',2,1,2,1,'2023-12-15'),
(3,'false',3,1,3,6,'2023-12-15'),
(4,'false',4,1,4,5,'2023-12-15'),
(5,'true',5,2,1,3,'2023-12-16'),
(6,'false',1,1,2,2,'2023-12-17'),
(7,'true',2,1,1,1,'2023-12-17'),
(8,'false',3,1,4,6,'2023-12-17'),
(9,'true',4,1,3,5,'2023-12-17'),
(10,'true',5,2,2,3,'2023-12-18')


insert into Attendance values 
(1,'true','good',1,1),
(2,'false','nghi hoc',2,1),
(3,'true','good',3,1),
(4,'true','good',4,1),
(5,'true','good',5,1),
(6,'false','ngu trong lop',6,1),
(7,'true','good',7,1),
(8,'fale','tu y ra khoi lop',1,2),
(9,'true','good',2,2),
(10,'true','good',3,2)

select * from Attendance a join Session s on a.session_id=s.id join Student st on st.id=a.student_id
select st.name,a.status,a.description,s.timeSlot_id,t.name as TeacherName,s.date from Attendance a join Session s on a.session_id=s.id join Student st on st.id=a.student_id join Teacher t on t.id=s.lecture_id
select * from Session





