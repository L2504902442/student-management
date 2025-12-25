package raisetech.student.management.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.student.management.controller.converter.StudentConverter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.repository.StudentRepository;
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
        String studentId = "777";
        Student student = new Student();
        student.setStudentId(studentId);
        when(repository.searchStudent(studentId)).thenReturn(student);
        when(repository.searchStudentCourse(studentId)).thenReturn(new ArrayList<>());

        StudentDetail expected = new StudentDetail(student, new ArrayList<>());

        StudentDetail actual = sut.searchStudent(studentId);

        verify(repository, times(1)).searchStudent(studentId);
        verify(repository, times(1)).searchStudentCourse(studentId);
        Assertions.assertEquals(expected.getStudent().getStudentId(), actual.getStudent().getStudentId());
    }

    @Test
    void 受講生詳細の登録_リポジトリの処理が適切に呼び出せていること() {
        Student student = new Student();
        StudentCourse studentCourse = new StudentCourse();
        List<StudentCourse> studentCourseList = List.of(studentCourse);
        StudentDetail studentDetail = new StudentDetail(student, studentCourseList);

        sut.registerStudent(studentDetail);

        verify(repository, times(1)).registerStudent(student);
        verify(repository, times(1)).registerStudentCourse(studentCourse);
    }

    @Test
    void 受講生詳細の登録_初期化処理が行われること() {
      String studentId = "777";
      StudentCourse studentCourse = new StudentCourse();

      sut.initStudentsCourse(studentCourse, studentId);

      assertEquals(studentId, studentCourse.getStudentId());
      assertEquals(LocalDate.now(), studentCourse.getStartDate());
      assertEquals(LocalDate.now().plusYears(1), studentCourse.getExpectedCompletionDate());
    }

    @Test
    void 受講生詳細の更新_リポジトリの処理が適切に呼び出せていること() {
        Student student = new Student();
        StudentCourse studentCourse = new StudentCourse();
        List<StudentCourse> studentCourseList = List.of(studentCourse);
        StudentDetail studentDetail = new StudentDetail(student, studentCourseList);

        sut.updateStudent(studentDetail);

        verify(repository, times(1)).updateStudent(student);
        verify(repository, times(1)).updateStudentCourse(studentCourse);
    }
}