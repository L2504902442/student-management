package raisetech.student.management.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import raisetech.student.management.data.ApplicationStatus;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.CourseDetail;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@MybatisTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository sut;

    private Student baseStudent;
    private StudentCourse baseCourse;

    @BeforeEach
    void setUp() {
        // 1. 受講生登録
        baseStudent = new Student();
        baseStudent.setName("伊東 剛");
        baseStudent.setKanaName("イトウツヨシ");
        baseStudent.setNickname("つよ");
        baseStudent.setEmail("tsuyoshi@example.com");
        baseStudent.setArea("東京");
        baseStudent.setAge(37);
        baseStudent.setGender("男");
        baseStudent.setRemark("");
        baseStudent.setDeleted(false);
        sut.registerStudent(baseStudent);

        // 2. コース登録（courseIdはnullでDB自動採番）
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(6);
        baseCourse = new StudentCourse();
        baseCourse.setStudentId(baseStudent.getStudentId());
        baseCourse.setCourseName("Java入門");
        baseCourse.setStartDate(startDate);
        baseCourse.setEndDate(endDate);
        baseCourse.setCourseId(null);
        sut.registerStudentCourse(baseCourse);
    }

    @Test
    void 受講生の全件検索が行えること() {
        List<Student> actual = sut.search();
        assertThat(actual).isNotNull();
        assertThat(actual.size()).isGreaterThan(0);
    }

    @Test
    void 受講生のID指定検索が行えること() {
        Student student = sut.searchStudent(baseStudent.getStudentId());
        assertThat(student).isNotNull();
        assertThat(student.getStudentId()).isEqualTo(baseStudent.getStudentId());
    }

    @Test
    void 存在しない受講生IDで検索するとnullが返ること() {
        Student actual = sut.searchStudent("999");
        assertThat(actual).isNull();
    }

    @Test
    void 受講生コース情報の全件検索が行えること() {
        List<StudentCourse> actual = sut.searchStudentCourseList();
        assertThat(actual).isNotNull();
        assertThat(actual.size()).isGreaterThan(0);
    }

    @Test
    void 受講生IDに基づく受講生コース情報の検索が行えること() {
        List<StudentCourse> courses = sut.searchStudentCourse(baseStudent.getStudentId());
        assertThat(courses).isNotNull();
        assertThat(courses.size()).isEqualTo(1);
        assertThat(courses.get(0).getCourseName()).isEqualTo("Java入門");
    }

    @Test
    void 存在しない受講生IDに紐づいたコース情報を検索すると空のリストが返ること() {
        List<StudentCourse> courses = sut.searchStudentCourse("999");
        assertThat(courses).isEmpty();
    }

    @Test
    void 受講生の登録が行えること() {
        int beforeSize = sut.search().size();
        Student student = new Student();
        student.setName("山田 太郎");
        student.setKanaName("ヤマダ タロウ");
        student.setNickname("たろう");
        student.setEmail("taro@example.com");
        student.setArea("大阪");
        student.setAge(25);
        student.setGender("男");
        student.setRemark("");
        student.setDeleted(false);
        sut.registerStudent(student);

        List<Student> actual = sut.search();
        assertThat(actual.size()).isEqualTo(beforeSize + 1);
    }

    @Test
    void 受講生コース情報の新規登録が行えること() {
        StudentCourse course = new StudentCourse();
        course.setStudentId(baseStudent.getStudentId());
        course.setCourseName("Python入門");
        course.setStartDate(LocalDate.now());
        course.setEndDate(LocalDate.now().plusMonths(3));
        course.setCourseId(null);
        sut.registerStudentCourse(course);

        List<StudentCourse> courses = sut.searchStudentCourse(baseStudent.getStudentId());
        assertThat(courses).extracting("courseName").contains("Python入門");
    }

    @Test
    void 受講生IDが空文字だと例外が発生すること() {
        StudentCourse course = new StudentCourse();
        course.setStudentId("");
        course.setCourseName("Java");
        course.setStartDate(LocalDate.now());
        course.setEndDate(LocalDate.now().plusMonths(6));
        assertThatThrownBy(() -> sut.registerStudentCourse(course))
                .isInstanceOf(DataIntegrityViolationException.class)
                .hasMessageContaining("STUDENT_ID");
    }

    @Test
    void 受講生の更新が行えること() {
        baseStudent.setName("伊東 剛 更新");
        sut.updateStudent(baseStudent);
        Student updated = sut.searchStudent(baseStudent.getStudentId());
        assertThat(updated.getName()).isEqualTo("伊東 剛 更新");
    }

    @Test
    void 存在しない受講生IDを指定しても例外は発生しないこと() {
        Student student = new Student();
        student.setStudentId("9999");
        student.setName("名無し");
        assertDoesNotThrow(() -> sut.updateStudent(student));
    }

    @Test
    void 受講生コース情報の更新が行えること() {
        baseCourse.setCourseName("更新コース");
        sut.updateStudentCourse(baseCourse);
        List<StudentCourse> updated = sut.searchStudentCourse(baseStudent.getStudentId());
        assertThat(updated.get(0).getCourseName()).isEqualTo("更新コース");
    }

    @Test
    void 存在しないコースIDを指定しても例外は発生しないこと() {
        StudentCourse course = new StudentCourse();
        course.setCourseId("9999");
        course.setStudentId(baseStudent.getStudentId());
        assertDoesNotThrow(() -> sut.updateStudentCourse(course));
    }

    @Test
    void 申込状況の登録ができること() {
        List<StudentCourse> courses = sut.searchStudentCourse(baseStudent.getStudentId());
        String courseId = courses.get(0).getCourseId();

        ApplicationStatus status = new ApplicationStatus(null, courseId, "仮申込");
        sut.registerApplicationStatus(status);

        List<CourseDetail> details = sut.searchCourseDetailsByStudentId(baseStudent.getStudentId());
        assertThat(details.get(0).getApplicationStatus()).isEqualTo("仮申込");
    }
}
