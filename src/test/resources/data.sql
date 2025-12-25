INSERT INTO students (name, kana_name, nickname, email, area, age, gender)
VALUES('伊東　剛', 'イトウツヨシ', 'つよ', 'tsuyoshi@example.com', '東京', 37, '男'),
      ('筒井あやめ', 'ツツイアヤメ', 'あやめん', 'ayame@example.com', '愛知', 21, '女'),
      ('遠藤さくら', 'エンドウサクラ', 'さくら', 'sakura@example.com', '愛知', 24, '女'),
      ('小川彩', 'オガワアヤ', 'あーや', 'aya@example.com', '千葉', 18, '女'),
      ('佐々木助三郎', 'ササキスケサブロウ', '助さん', 'sasaki@example.com', '茨城', 29, 'その他');

INSERT INTO students_courses(student_id, course_name, start_date, end_date)
VALUES(1, 'Python入門', '2024-04-01', '2024-06-30'),
      (2, 'Spring入門', '2024-07-01', '2024-09-30'),
      (3, 'Python入門', '2024-05-01', '2024-07-31'),
      (4, 'Web開発', '2024-03-01', '2024-06-01'),
      (5, 'Web開発', '2025-10-09', '2026-10-09');

INSERT INTO application_status (course_id, application_status)
VALUES (1,'受講終了'),
       (2,'受講終了'),
       (3,'受講終了'),
       (4,'受講中'),
       (5,'本申込'),
       (6,'仮申込');


