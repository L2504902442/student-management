package raisetech.student.management.repository;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
class StudentRepositoryTest {

  @Autowired
  private StudentRepository sut;

  @Test
  void 受講生の全件検索が行えること() {
    List<Student> actual = sut.search();
    assertThat(actual).isNotNull();
    assertThat(actual.size()).isEqualTo(0);
  }

  @Test
  void 受講生のID指定検索が行えること() {
    Student student = sut.searchStudent("1");
    assertThat(student).isNotNull();
    assertThat(student.getStudentId()).isEqualTo("1");
  }

  @Test
  void 受講生コース情報の全件検索が行えること() {
    List<StudentCourse> actual = sut.searchStudentCourseList();
    assertThat(actual).isNotNull();
    assertThat(actual.size()).isGreaterThan(0);
  }

  @Test
  void 受講生IDに基づく受講生コース情報の検索が行えること() {
    List<StudentCourse> studentCourse = sut.searchStudentCourse("1");
    assertThat(studentCourse).isNotNull();

      assertThat(studentCourse.size()).isEqualTo(1);
  }


  @Test
  void 受講生の登録が行えること() {
      int beforeSize = sut.search().size();
    Student student =  new Student();
    student.setName("伊東 剛");
    student.setKanaName("イトウツヨシ");
    student.setNickname("つよ");
    student.setEmail("tsuyoshi@example.com");
    student.setArea("東京");
    student.setAge(37);
    student.setGender("男");
    student.setRemark("");
    student.setDeleted(false);

    sut.registerStudent(student);

    List<Student> actual = sut.search();
    assertThat(actual).isNotEmpty();
    assertThat(actual.size()).isEqualTo(beforeSize + 1);
  }

  @Test
  void 受講生コース情報の新規登録が行えること() {
      LocalDateTime startDate = LocalDateTime.now();
      LocalDateTime endDate = startDate.plusYears(1);

      StudentCourse studentCourse = new StudentCourse(
          null,"1", "Python入門", startDate, endDate
      );

      sut.registerStudentCourse(studentCourse);
      List<StudentCourse> studentCourseList = sut.searchStudentCourse("1");
      assertThat(studentCourseList)
              .extracting("courseName")
              .contains("Python入門");
  }

  @Test
  void 受講生の更新が行えること() {
      Student student = sut.searchStudent("1");
      student.setName("伊東 剛");
      sut.updateStudent(student);

      Student updated = sut.searchStudent("1");
      assertThat(updated.getName()).isEqualTo("伊東 剛");
  }

  @Test
  void 受講生コース情報の更新が行えること() {
      List<StudentCourse> studentCourseList = sut.searchStudentCourse("1");
      StudentCourse studentCourse = studentCourseList.get(0);
      studentCourse.setCourseName("更新コース");
      sut.updateStudentCourse(studentCourse);

      List<StudentCourse> updatedCourse = sut.searchStudentCourse("1");
      assertThat(updatedCourse.get(0).getCourseName()).isEqualTo("更新コース");
  }
}