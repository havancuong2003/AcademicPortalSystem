ALTER TABLE Student
ADD userName nvarchar(50);

ALTER TABLE Student
ADD CONSTRAINT FK_Student_Account FOREIGN KEY (userName) REFERENCES account(userName);


ALTER TABLE Lecture
ADD userName nvarchar(50);

ALTER TABLE Lecture
ADD CONSTRAINT FK_Teacher_Account FOREIGN KEY (userName) REFERENCES account(userName);


