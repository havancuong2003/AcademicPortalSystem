-- Thêm dữ liệu vào bảng Student
INSERT INTO Student (id, name) VALUES
(1, 'John Doe'),
(2, 'Alice Smith'),
(3, 'Bob Johnson'),
(4, 'Emily Brown'),
(5, 'Michael Davis'),
(6, 'Jessica Wilson'),
(7, 'David Martinez'),
(8, 'Emma Garcia'),
(9, 'Daniel Rodriguez'),
(10, 'Sophia Lopez');

-- Thêm dữ liệu vào bảng Teacher
INSERT INTO Teacher (id, name) VALUES
(1, 'Mr. Smith'),
(2, 'Ms. Johnson'),
(3, 'Dr. Williams'),
(4, 'Mrs. Anderson');

-- Thêm dữ liệu vào bảng Course
INSERT INTO Course (id, name) VALUES
(1, 'Math'),
(2, 'Physics'),
(3, 'English'),
(4, 'Biology'),
(5, 'History'),
(6, 'Chemistry'),
(7, 'Computer Science');
	

	-- Thêm dữ liệu vào bảng Department
INSERT INTO Department (id, name) VALUES
(1, 'Science'),
(2, 'Humanities'),
(3, 'Engineering');

-- Thêm dữ liệu vào bảng Group
INSERT INTO [Group] (id, name, course_id) VALUES
(1, 'Group A', 1),
(2, 'Group B', 2),
(3, 'Group C', 3),
(4, 'Group D', 4),
(5, 'Group E', 5);

-- Thêm dữ liệu vào bảng TimeSlot
INSERT INTO TimeSlot (id, description) VALUES
(1, 'Morning'),
(2, 'Afternoon'),
(3, 'Evening'),
(4, 'Night');

-- Thêm dữ liệu vào bảng Room
INSERT INTO Room (id, name) VALUES
(1, 'Room 101'),
(2, 'Room 102'),
(3, 'Room 103'),
(4, 'Room 104'),
(5, 'Room 105');

-- Thêm dữ liệu vào bảng Term
INSERT INTO Term (id, name) VALUES
(1, 'Fall 2023'),
(2, 'Spring 2024'),
(3, 'Summer 2024');


-- Thêm dữ liệu vào bảng Session
INSERT INTO Session (id, status, group_id, room_id, timeSlot_id, lecture_id, date) VALUES
(1, 'Active', 1, 1, 1, 1, '2024-02-07'),
(2, 'Active', 2, 2, 2, 2, '2024-02-08'),
(3, 'Inactive', 3, 3, 3, 3, '2024-02-09'),
(4, 'Active', 4, 4, 4, 4, '2024-02-10');


-- Thêm dữ liệu vào bảng Attendance
INSERT INTO Attendance (id, status, description, student_id, session_id) VALUES
(1, 'Present', 'Attended the session', 1, 1),
(2, 'Present', 'Attended the session', 2, 2),
(3, 'Absent', 'Missed the session', 3, 1),
(4, 'Present', 'Attended the session', 4, 3),
(5, 'Absent', 'Missed the session', 5, 2),
(6, 'Present', 'Attended the session', 6, 4),
(7, 'Present', 'Attended the session', 7, 1),
(8, 'Absent', 'Missed the session', 8, 2),
(9, 'Present', 'Attended the session', 9, 3),
(10, 'Absent', 'Missed the session', 10, 4);
