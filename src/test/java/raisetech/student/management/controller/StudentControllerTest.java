package raisetech.student.management.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.service.StudentService;
import java.time.LocalDateTime;
import java.util.Set;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(StudentController.class)
class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private StudentService service;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  void 受講生詳細の一覧検索が実行できて空のリストが返ってくること() throws Exception {
    mockMvc.perform(get("/studentList"))
            .andExpect(status().isOk());

    verify(service, times(1)).searchStudentList();
  }

  @Test
  void 受講生詳細の単一検索が実行できて対象となる受講生のリストが返ってくること() throws Exception {
    String studentId = "1";
    mockMvc.perform(get("/student/{studentId}", studentId))
            .andExpect(status().isOk());

    verify(service, times(1)).searchStudent(studentId);
  }

  @Test
  void 受講生詳細の新規登録が実行できて登録された受講生のリストが返ってくること() throws Exception {
    mockMvc.perform(post("/registerStudent").contentType(MediaType.APPLICATION_JSON).content(
            """
                      {
                       "student" : {
                          "name" : "伊東　剛",
                          "kanaName" : "イトウツヨシ",
                          "nickname" : "つよ",
                          "email" : "tsuyoshi@example.com",
                          "area" : "東京都",
                          "age" : 37,
                          "gender" : "男",
                          "remark" : ""
                       },
                       "studentCourseList" : [
                       {
                          "courseName" : "Java基礎"
                       }
                      ]
                     }        
            """
    ))
    .andExpect(status().isOk());

    verify(service, times(1)).registerStudent(any());
  }

  @Test
  void 受講生詳細の更新が実行できて特定のメッセージが表示されること() throws Exception {
    mockMvc.perform(put("/updateStudent").contentType(MediaType.APPLICATION_JSON).content(
            """
                    {
                       "student" : {
                          "name" : "伊東 剛",
                          "kanaName" : "イトウツヨシ",
                          "nickname" : "つよ",
                          "email" : "tsuyoshi@example.com",
                          "area" : "東京都",
                          "age" : 37,
                          "gender" : "男",
                          "remark" : ""
                        },
                        "studentCourseList" : [
                        {
                           "courseName" : ""
                        }
                       ]
                      }       
                    """
    )).andExpect(status().isOk());

    verify(service, times(1)).updateStudent(any());
  }

  @Test
  void 受講生詳細の受講生で適切な値を入力した時に入力チェックに異常が発生しないこと() {
    Student student = new Student();
    student.setStudentId("1");
    student.setName("伊東　剛");
    student.setKanaName("イトウツヨシ");
    student.setNickname("つよ");
    student.setEmail("tsuyoshi@example.com");
    student.setArea("東京都");
    student.setGender("男");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations.size()).isEqualTo(0);
  }

  @Test
  void 受講生詳細の受講生でIDに数字以外を用いた時に入力チェックに掛かること() {
    Student student = new Student();
    student.setStudentId("テストです。");
    student.setName("伊東 剛");
    student.setKanaName("イトウツヨシ");
    student.setNickname("つよ");
    student.setEmail("tsuyoshi@example.com");
    student.setArea("東京都");
    student.setGender("男");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations.size()).isEqualTo(1);
    assertThat(violations).extracting("message")
            .containsOnly("数字のみ入力するようにしてください。");

  }

  @Test
  void 受講生詳細の受講生コース情報で適切な値を入力した時に入力チェックに異常が発生しないこと() {
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setCourseId("1");
    studentCourse.setStudentId("1");
    studentCourse.setCourseName("Java基礎");
    studentCourse.setStartDate(LocalDateTime.now());
    studentCourse.setEndDate(LocalDateTime.now().plusYears(1));

    Set<ConstraintViolation<StudentCourse>> violations = validator.validate(studentCourse);

    assertThat(violations.size()).isEqualTo(0);
  }

  @Test
  void 受講生詳細の受講生コース情報でIDに数字以外を用いた時に入力チェックに異常が発生しないこと() {
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setCourseId("テストです。");
    studentCourse.setStudentId("1");
    studentCourse.setCourseName("Java基礎");
    studentCourse.setStartDate(LocalDateTime.now());
    studentCourse.setEndDate(LocalDateTime.now().plusYears(1));

    Set<ConstraintViolation<StudentCourse>> violations = validator.validate(studentCourse);

    assertThat(violations.size()).isEqualTo(1);
    assertThat(violations).extracting("message")
            .containsOnly("数字のみ入力するようにしてください。");

  }
}