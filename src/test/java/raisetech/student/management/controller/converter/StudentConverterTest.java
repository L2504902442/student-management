package raisetech.student.management.controller.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class StudentConverterTest {
  private  StudentConverter sut;

  @BeforeEach
  void before() {
     sut = new StudentConverter();
  }

  @Test
  void 受講生のリストと受講生コース情報のリストを渡して受講生詳細のリストが作成できること() {
      Student student = createStudent();

      StudentCourse studentCourse = new StudentCourse();
      studentCourse.setCourseId("1");
      studentCourse.setStudentId("1");
      studentCourse.setCourseName("Java基礎");
      studentCourse.setStartDate(LocalDateTime.now());
      studentCourse.setEndDate(LocalDateTime.now().plusYears(1));

      List<Student> studentList = List.of(student);
      List<StudentCourse> studentCourseList = List.of(studentCourse);

      List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentCourseList);

      assertThat(actual.get(0).getStudent()).isEqualTo(student);
      assertThat(actual.get(0).getStudentCourseList()).isEqualTo(studentCourseList);
  }



    @Test
  void 受講生のリストと受講生コース情報のリストを渡した時に紐づかない受講生コース情報は除外されること() {
    Student student = createStudent();

    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setCourseId("1");
    studentCourse.setStudentId("2");
    studentCourse.setCourseName("Java基礎");
    studentCourse.setStartDate(LocalDateTime.now());
    studentCourse.setEndDate(LocalDateTime.now().plusYears(1));

    List<Student> studentList = List.of(student);
    List<StudentCourse> studentCourseList = List.of(studentCourse);

    List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentCourseList);

    assertThat(actual.get(0).getStudent()).isEqualTo(student);
    assertThat(actual.get(0).getStudentCourseList()).isEmpty();

  }

    private static Student createStudent() {
        Student student = new Student();
        student.setStudentId("1");
        student.setName("伊東 剛");
        student.setKanaName("イトウツヨシ");
        student.setNickname("つよ");
        student.setEmail("tsuyoshi@example.com");
        student.setArea("東京");
        student.setAge(37);
        student.setGender("男");
        student.setRemark("");
        student.setDeleted(false);
        return student;
    }
}