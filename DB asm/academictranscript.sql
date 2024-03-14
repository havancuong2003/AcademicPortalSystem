select distinct t.id,t.description as term,c.code,c.description as [Course Name],sc.total,sc.status from statusMarkCourse sc
join student_group sg on sc.student_group_id=sg.id
join student s on s.id=sg.Studentid
join [group] g on g.id = sg.groupid
join term t on t.id = g.termID
join course c on c.id=g.courseId
where s.id ='he3'
order by t.id



