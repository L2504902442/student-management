CREATE TABLE  students(
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    kana_name VARCHAR(100) NOT NULL,
    nickname VARCHAR(50),
    email VARCHAR(255) NOT NULL,
    area VARCHAR(50) NOT NULL,
    age INT,
    gender VARCHAR(10),
    remark TEXT,
    is_deleted BOOLEAN
);

    CREATE TABLE students_courses
    (
        course_id INT AUTO_INCREMENT PRIMARY KEY,
        student_id INT NOT NULL,
        course_name VARCHAR(100) NOT NULL,
        start_date DATE,
        end_date DATE
);