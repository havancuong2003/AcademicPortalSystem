select a.id as aid,a.status as astatus,a.description as adescription,session_id,s.id as ssid,s.status as ss_status,
s.group_id as ss_groupid,s.room_id as ss_roomid,s.timeSlot_id as ss_timeslotid,s.lecture_id as ss_lectureid,date,st.id as stid,st.name as studentName,
t.id as teacher_id,t.name as tearcherName,r.id as roomID,r.name as roomName,g.id as groupID,g.name as groupName,g.course_id as group_courseID, c.id as courseID,c.name as courseName
from Attendance a 
join Session s on a.session_id=s.id 
join Student st on st.id=a.student_id 
join Teacher t on t.id=s.lecture_id 
join Room r on r.id=s.room_id 
join [Group] g on g.id=s.group_id 
join course c on c.id=g.course_id

select s.id, st.name,c.name as coursename,g.id as gid,r.id as rid,r.name as roomname,a.status,a.description,s.timeSlot_id,t.id as teacherid,t.name as TeacherName,s.date from Attendance a join Session s on a.session_id=s.id join Student st on st.id=a.student_id join Teacher t on t.id=s.lecture_id join Room r on r.id=s.room_id join [Group] g on g.id=s.group_id join course c on c.id=g.course_id
select * from Attendance

select * from Course