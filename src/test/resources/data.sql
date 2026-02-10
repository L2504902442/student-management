-- students
INSERT INTO students (name, kana_name, nickname, email, area, age, gender, remark, is_deleted)
VALUES
('伊東 剛', 'イトウツヨシ', 'つよ', 'tsuyoshi@example.com', '東京', 37, '男', 'テストデータ', 0),
('筒井あやめ', 'ツツイアヤメ', 'あやめん', 'ayame@example.com', '愛知', 21, '女', 'テストデータ', 0),
('遠藤さくら', 'エンドウサクラ', 'さくら', 'sakura@example.com', '愛知', 24, '女', 'テストデータ', 0);
-- student_course
-- student_course
INSERT INTO student_course (student_id, course_name, start_date, end_date)
VALUES
(1, 'Python入門', '2024-04-01', '2024-06-30'),
(2, 'Spring入門', '2024-07-01', '2024-09-30'),
(3, 'Web開発', '2024-05-01', '2024-08-31');


-- application_status
INSERT INTO application_status (course_id, status)
VALUES
(1, '受講中'),
(2, '仮申込'),
(3, '本申込');