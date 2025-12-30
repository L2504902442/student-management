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
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.service.StudentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


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
            .andExpect(status().isOk())
            .andExpect(content().json("[]"));

    verify(service, times(1)).searchStudentList();
  }

  @Test
  void 受講生詳細の検索ができてIDに紐づく受講生詳細が返ってくること() throws Exception {
    String id = "100";
    Student student = new Student(id, "渡辺　恵子", "わたなべ　けいこ", "けいこ",
            "unique.user1937@example.com", "東京都", 30, "女", "特になし", false);
    StudentCourse studentCourse = new StudentCourse("100", id, "JAVAコース",
            LocalDateTime.parse("2024-01-01T00:00:00"), LocalDateTime.parse("2024-04-01T00:00:00"));
    StudentDetail studentDetail = new StudentDetail(student, List.of(studentCourse));
    when(service.searchStudent(id)).thenReturn(studentDetail);
    mockMvc.perform(get("/student/{id}", id))
            .andExpect(status().isOk())
            .andExpect(content().json("""
            {
              "student":{
                "studentId":"100",
                "name":"渡辺　恵子",
                "kanaName":"わたなべ　けいこ",
                "nickname":"けいこ",
                "email":"unique.user1937@example.com",
                "area":"東京都",
                "age":30,
                "gender":"女",
                "remark":"特になし",
                "deleted":false
              },
              "studentCourseList":[
                {
                  "courseId":"100",
                  "studentId":"100",
                  "courseName":"JAVAコース",
                  "startDate":"2024-01-01T00:00:00",
                  "endDate":"2024-04-01T00:00:00"
                }
              ]
            }
            """));
    verify(service, times(1)).searchStudent(id);
  }

  @Test
  void 受講生コース詳細の検索ができて空で返ってくること() throws Exception {
    String id = "100";

   mockMvc.perform(get("/course/{courseId}", id))
            .andExpect(status().isOk());

    verify(service, times(1)).searchCourseByStudentId(id);
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
                          "area" : "東京",
                          "age" : 37,
                          "gender" : "男"
                         },
                       "studentCourseList" : [
                       {
                          "courseName" : "Java基礎"
                       }
                      ]
                    }        
                     """))
    .andExpect(status().isOk());

    verify(service, times(1)).registerStudent(any());
  }

  @Test
  void 受講生詳細の更新が実行できて特定のメッセージが表示されること() throws Exception {
    mockMvc.perform(put("/updateStudent").contentType(MediaType.APPLICATION_JSON).content(
            """
                    {
                       "student" : {
                          "studentId" : "1",
                          "name" : "伊東 剛",
                          "kanaName" : "イトウツヨシ",
                          "nickname" : "つよ",
                          "email" : "tsuyoshi@example.com",
                          "area" : "東京",
                          "age" : 37,
                          "gender" : "男"
                         },
                        "studentCourseList" : [
                        {
                           "courseId" : "1",
                           "studentId" : "1",
                           "courseName" : "Java基礎"
                        }
                       ]
                      }       
                    """))
            .andExpect(status().isOk())
            .andExpect (content().string("更新処理が成功しました。"));

    verify(service, times(1)).updateStudent(any());
  }

  @Test
  void studentsで検索をした際適切なエラーメッセージが返ってくること() throws Exception {
    mockMvc.perform(get("/students"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.status").value(400))
            .andExpect(jsonPath("$.error").value("Bad Request"))
            .andExpect(jsonPath("$.message").value(
                    "現在このAPIは利用できません。URLは「students」ではなく「studentList」を利用してください。"))
            .andExpect(jsonPath("$.path").value("/students"));
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
    student.setAge(37);
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
    student.setAge(37);
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

    Set<ConstraintViolation<StudentCourse>> violations = validator.validate(studentCourse);

    assertThat(violations.size()).isEqualTo(0);
  }

  @Test
  void 受講生詳細の受講生コース情報でIDに数字以外を用いた時に入力チェックに異常が発生しないこと() {
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setCourseId("テストです。");
    studentCourse.setStudentId("abc");
    studentCourse.setCourseName("Java基礎");
    Set<ConstraintViolation<StudentCourse>> violations = validator.validate(studentCourse);

    assertThat(violations.size()).isEqualTo(2);
    assertThat(violations).extracting(v -> v.getPropertyPath().toString(),
            ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(
                    tuple("courseId", "数字のみ入力するようにしてください。"),
                    tuple("studentId", "数字のみ入力するようにしてください。")
            );
  }
}