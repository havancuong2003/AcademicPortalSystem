--- trigger nay dung de len thoi khoa bieu
CREATE TRIGGER trg_CreateSessionsForGroup
ON [group]
AFTER INSERT
AS
BEGIN
    -- Biến cần thiết
    DECLARE @GroupID INT
    DECLARE @StartDate DATE
    DECLARE @EndDate DATE
    DECLARE @CurrentDate DATE
    DECLARE @FirstDayOfWeek DATE
    DECLARE @SecondDayOfWeek DATE
	DECLARE @LECTUREID NVARCHAR(50)

    -- Con trỏ để lặp qua các bản ghi được chèn vào bảng group
    DECLARE group_cursor CURSOR FOR
    SELECT lectureid,id, timeStart, timeEnd, firstday, secondday
    FROM  inserted

    -- Mở con trỏ để bắt đầu lặp qua các bản ghi
    OPEN group_cursor

    -- Lấy bản ghi đầu tiên từ bảng tạm thời và gán cho các biến tương ứng
    FETCH NEXT FROM group_cursor INTO @LECTUREID, @GroupID, @StartDate, @EndDate, @FirstDayOfWeek, @SecondDayOfWeek

    -- Bắt đầu vòng lặp
    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Tạo session cho mỗi tuần
        SET @CurrentDate = @StartDate
        WHILE @CurrentDate <= @EndDate
        BEGIN
            -- Kiểm tra xem ngày hiện tại có phải là firstDate hoặc secondDate của tuần không
            IF DATEPART(WEEKDAY, @CurrentDate) = DATEPART(WEEKDAY, @FirstDayOfWeek) OR DATEPART(WEEKDAY, @CurrentDate) = DATEPART(WEEKDAY, @SecondDayOfWeek)
            BEGIN
                INSERT INTO [session] ([status],group_id, [date],[lectureid])
                VALUES (0,@GroupID, @CurrentDate,@LECTUREID)
            END
            SET @CurrentDate = DATEADD(DAY, 1, @CurrentDate)
        END

        -- Lấy bản ghi tiếp theo từ bảng tạm thời
        FETCH NEXT FROM group_cursor INTO @LECTUREID,@GroupID, @StartDate, @EndDate, @FirstDayOfWeek, @SecondDayOfWeek
    END

    -- Đóng con trỏ và giải phóng tài nguyên
    CLOSE group_cursor
    DEALLOCATE group_cursor
END;

-------- trigger nay dung de add ban ghi cho viec danh diem danh.



CREATE TRIGGER trg_CreateAttendanceForSession
ON [student_group]
AFTER INSERT
AS
BEGIN
  
    DECLARE @SessionID INT
    DECLARE @GroupID INT
    DECLARE @StudentID nvarchar(50);
 
    DECLARE group_cursor CURSOR FOR
   select distinct sg.groupid from [group] g join inserted sg on g.id=sg.groupid;
  
    OPEN group_cursor;   
    FETCH NEXT FROM group_cursor INTO @GroupID;
    WHILE @@FETCH_STATUS = 0
    BEGIN
      
        DECLARE session_cursor CURSOR FOR
            SELECT sg.studentid,s.id FROM [session] s join student_group sg
			on s.group_id= sg.groupid
			WHERE s.group_id = @GroupID;

       
        OPEN session_cursor;

        FETCH NEXT FROM session_cursor INTO @StudentID, @SessionID;
        WHILE @@FETCH_STATUS = 0
        BEGIN
         INSERT INTO Attendance ([status],[description],student_Id, session_id) VALUES (null,null,@StudentID, @SessionID);

         FETCH NEXT FROM session_cursor INTO @StudentID,@SessionID;
        END;

        CLOSE session_cursor;
        DEALLOCATE session_cursor;

        -- Lấy bản ghi tiếp theo từ bảng tạm thời
        FETCH NEXT FROM group_cursor INTO  @GroupID;
    END;

    CLOSE group_cursor;
    DEALLOCATE group_cursor;
END;



----- trigger nay de set trang thai diem danh cua giang vien

CREATE TRIGGER UpdateAttendanceOnSessionStatusChange
ON [Session]
AFTER UPDATE
AS
BEGIN
    IF UPDATE([status])
    BEGIN
        UPDATE Attendance
        SET [status] = NULL,
            [description] = NULL
        WHERE session_id IN (SELECT id FROM inserted WHERE status = 'false');
    END
END;








--- trigger nay  insert diem vao data
CREATE TRIGGER trg_InsertStudentGroup
ON student_group
AFTER INSERT
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @UserName NVARCHAR(50)
    DECLARE @GroupId INT
    DECLARE @TermId NVARCHAR(50)
    DECLARE @CourseId INT

    -- Khai báo con trỏ để duyệt qua các dòng được chèn
    DECLARE @InsertedCursor CURSOR
    SET @InsertedCursor = CURSOR FOR
        SELECT s.userName, sg.groupid
        FROM inserted i
        JOIN student_group sg ON i.id = sg.id
        JOIN student s ON sg.Studentid = s.id

    -- Mở con trỏ
    OPEN @InsertedCursor
    FETCH NEXT FROM @InsertedCursor INTO @UserName, @GroupId

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Lấy TermID và CourseID từ bảng [group]
        SELECT @TermId = g.termID, @CourseId = g.courseId
        FROM [group] g
        WHERE g.id = @GroupId

        -- Chèn dữ liệu vào bảng mark_student
        INSERT INTO mark_student (student_group_id, mark_course_id, [value], comment)
        SELECT sg.id AS student_group_id, mc.id AS mark_course_id, NULL AS [value], NULL AS comment
        FROM student_group sg
        INNER JOIN [group] g ON sg.groupid = g.id
        INNER JOIN student s ON sg.Studentid = s.id
        INNER JOIN mark_course mc ON mc.courseId = g.courseId
        WHERE s.userName LIKE @UserName AND g.termID = @TermId AND g.courseId = @CourseId

        FETCH NEXT FROM @InsertedCursor INTO @UserName, @GroupId
    END

    -- Đóng con trỏ
    CLOSE @InsertedCursor
    DEALLOCATE @InsertedCursor
END;


--trigger insert data vao absentPercent theo student_group_id

CREATE TRIGGER insert_absentPercent_on_student_group
ON student_group
AFTER INSERT
AS
BEGIN
    -- Chèn một bản ghi mới vào bảng absentPercent cho mỗi bản ghi mới được thêm vào bảng student_group
    INSERT INTO absentPercent (student_group_id, absentCount, totalClasses,emailCheck)
    SELECT id, 0, 0,0
    FROM inserted;
END;



---- tinh toan so buoi nghi cua sinh vien

CREATE TRIGGER update_absentPercent
ON Attendance
AFTER INSERT, UPDATE
AS
BEGIN
    DECLARE @groupid int
    DECLARE @studentid nvarchar(50)

    -- Khai báo con trỏ
    DECLARE updateCursor CURSOR FOR
    SELECT s.group_id, student_id FROM inserted a join Session s on a.session_id=s.id

    -- Mở con trỏ
    OPEN updateCursor;

    -- Lấy giá trị đầu tiên từ con trỏ
    FETCH NEXT FROM updateCursor INTO @groupid, @studentid;

    -- Lặp qua từng hàng trong con trỏ
    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Cập nhật bảng absentPercent cho mỗi studentid và groupid
        UPDATE absentPercent
        SET absentCount = (SELECT COUNT(*) FROM Attendance a JOIN [Session] s ON s.id = a.session_id
                           WHERE a.student_id = @studentid AND s.group_id = @groupid AND a.[status] = 'false'),
            totalClasses = (SELECT COUNT(*) FROM [Session] WHERE group_id = @groupid)
        WHERE student_group_id IN (SELECT id FROM student_group WHERE Studentid = @studentid AND groupid = @groupid);

        -- Lấy giá trị tiếp theo từ con trỏ
        FETCH NEXT FROM updateCursor INTO @groupid, @studentid;
    END;

    -- Đóng con trỏ
    CLOSE updateCursor;
    DEALLOCATE updateCursor;
END;

