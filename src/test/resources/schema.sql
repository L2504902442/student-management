DROP TABLE IF EXISTS application_status;
DROP TABLE IF EXISTS students_courses;
DROP TABLE IF EXISTS students;

CREATE TABLE students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    kana_name VARCHAR(100) NOT NULL,
    nickname VARCHAR(100),
    email VARCHAR(255) NOT NULL,
    area VARCHAR(100),
    age INT,
    gender VARCHAR(30),
    remark VARCHAR(50),
    is_deleted BOOLEAN DEFAULT FALSE
);

CREATE TABLE students_courses (
    course_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    course_name VARCHAR(100) NOT NULL,
    start_date DATE,
    end_date DATE,
    FOREIGN KEY (student_id) REFERENCES students(student_id)
);

CREATE TABLE application_status (
    course_id INT PRIMARY KEY,
    status VARCHAR(50) NOT NULL,
    FOREIGN KEY (course_id) REFERENCES students_courses(course_id)
);