package raisetech.student.management.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.student.management.controller.converter.StudentConverter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.CourseDetail;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.repository.StudentRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @Mock
    private StudentConverter converter;

    private StudentService sut;

    @BeforeEach
    void before() {
        sut = new StudentService(repository, converter);
    }

    @Test
    void 受講生詳細の一覧検索_リポジトリとコンバーターの処理が適切に呼び出せていること() {
        List<Student> studentList = new ArrayList<>();
        List<StudentCourse> studentCourseList = new ArrayList<>();
        when(repository.search()).thenReturn(studentList);
        when(repository.searchStudentCourseList()).thenReturn(studentCourseList);

        sut.searchStudentList();

        verify(repository, times(1)).search();
        verify(repository, times(1)).searchStudentCourseList();
        verify(converter, times(1)).convertStudentDetails(studentList, studentCourseList);
    }

    @Test
    void 受講生詳細の検索_リポジトリの処理が適切に呼び出せていること() {
        Student student = new Student();
        List<StudentCourse> studentCourse = new ArrayList<>();
        String studentId = "777";
        student.setStudentId(studentId);
        when(repository.searchStudent(studentId)).thenReturn(student);
        when(repository.searchStudentCourse(studentId)).thenReturn(studentCourse);

        StudentDetail expected = new StudentDetail(student, studentCourse);

        StudentDetail actual = sut.searchStudent(studentId);

        verify(repository, times(1)).searchStudent(studentId);
        verify(repository, times(1)).searchStudentCourse(studentId);
        assertEquals(expected, actual);
    }

    @Test
    void 受講生コース詳細の検索_リポジトリの処理が適切に呼び出せていること(){
        String studentId= "1";
        CourseDetail courseDetail = new CourseDetail();
        courseDetail.setStudentId(studentId);
        List<CourseDetail> expected = List.of(courseDetail);

        when(repository.searchCourseDetailsByStudentId(studentId)).thenReturn(expected);

        final List<CourseDetail> actual = sut.searchCourseByStudentId(studentId);

        verify(repository, times(1)).searchCourseDetailsByStudentId(studentId);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 受講生詳細の登録_リポジトリの処理が適切に呼び出せていること() {
        Student student = new Student();
        StudentCourse studentCourse = new StudentCourse();
        List<StudentCourse> studentCourseList = new ArrayList<>(List.of(studentCourse));
        StudentDetail studentDetail = new StudentDetail(student, studentCourseList);

        sut.registerStudent(studentDetail);

        verify(repository, times(1)).registerStudent(student);
        verify(repository, times(1)).registerStudentCourse(studentCourse);
    }

    @Test
    void 受講生詳細の登録_初期化処理が行われること() {
      String studentId = "777";
      Student student = new Student();
      student.setStudentId(studentId);
      StudentCourse studentCourse = new StudentCourse();
      LocalDateTime fixedNow = LocalDateTime.now();

      sut.initStudentCourse(studentCourse, student.getStudentId());

      assertEquals(studentId, studentCourse.getStudentId());
      assertEquals(fixedNow, studentCourse.getStartDate());
      assertEquals(fixedNow.plusYears(1), studentCourse.getEndDate());
    }

    @Test
    void 受講生詳細の更新_リポジトリの処理が適切に呼び出せていること() {
        Student student = new Student();
        StudentCourse studentCourse = new StudentCourse();
        List<StudentCourse> studentCourseList = new ArrayList<>(List.of(studentCourse));
        StudentDetail studentDetail = new StudentDetail(student, studentCourseList);

        sut.updateStudent(studentDetail);

        verify(repository, times(1)).updateStudent(studentDetail.getStudent());
        verify(repository, times(1)).updateStudentCourse(studentCourse);
    }
}