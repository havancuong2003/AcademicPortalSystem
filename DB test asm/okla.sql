select distinct g.id as gid, g.courseId,mc.gradeCategory,mc.gradeItem,mc.[weight] from   mark_course mc join mark_student ms on ms.mark_course_id=mc.id

join student_group sg on sg.id = ms.student_group_id
join [Group] g on g.id=sg.groupid
join Lecture l on g.lectureid=l.id
join course c on c.id=g.courseId
join student s on s.id = sg.Studentid

where l.userName = 'sonnt' and sg.groupid=12



select  * from mark_course mc


where mc.courseId =16
