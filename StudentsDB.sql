DROP DATABASE IF EXISTS Students;
CREATE DATABASE Students;
USE Students;

CREATE TABLE Student (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    major VARCHAR(150) NOT NULL,
    grade DOUBLE NOT NULL DEFAULT 60
);

CREATE TABLE Course (
    id CHAR(10) PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    room CHAR(5)
);

CREATE TABLE Registration (
    student_id INT,
    course_id CHAR(10),
    semester VARCHAR(50),
    FOREIGN KEY (student_id) REFERENCES Student(id),
    FOREIGN KEY (course_id) REFERENCES Course(id),
    PRIMARY KEY (student_id, course_id, semester)
);

-- Demo data
INSERT INTO Student
VALUES (120204785, "Ahmad", "IT", 92.5),
       (120197895, "Sami", "Engineering", 95),
       (120179652, "Kareem", "IT", 84.3);
       
INSERT INTO Course       
VALUES ("CSCI201", "Software Engineering", "I308"),
	   ("SDEV105", "Web Development", "K305"),
       ("CSCI607", "Deep Learning", "I502");
       
INSERT INTO Registration
VALUES (120204785, "CSCI201", "First semester 2020/2021"),
       (120179652, "SDEV105", "Second semester 2021/2022");
# Zaki Kurdya, 120200706