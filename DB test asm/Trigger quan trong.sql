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

    -- Con trỏ để lặp qua các bản ghi được chèn vào bảng group
    DECLARE group_cursor CURSOR FOR
    SELECT id, timeStart, timeEnd, firstday, secondday
    FROM inserted

    -- Mở con trỏ để bắt đầu lặp qua các bản ghi
    OPEN group_cursor

    -- Lấy bản ghi đầu tiên từ bảng tạm thời và gán cho các biến tương ứng
    FETCH NEXT FROM group_cursor INTO @GroupID, @StartDate, @EndDate, @FirstDayOfWeek, @SecondDayOfWeek

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
                INSERT INTO [session] ([status],group_id, [date])
                VALUES (0,@GroupID, @CurrentDate)
            END
            SET @CurrentDate = DATEADD(DAY, 1, @CurrentDate)
        END

        -- Lấy bản ghi tiếp theo từ bảng tạm thời
        FETCH NEXT FROM group_cursor INTO @GroupID, @StartDate, @EndDate, @FirstDayOfWeek, @SecondDayOfWeek
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




