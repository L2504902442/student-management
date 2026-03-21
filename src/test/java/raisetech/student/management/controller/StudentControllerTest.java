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
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.service.StudentService;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private StudentService service;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  // ============================
  // 一覧検索テスト
  // ============================
  @Test
  void 一覧検索が実行できて空リストが返る() throws Exception {
    when(service.searchStudentList()).thenReturn(List.of());

    mockMvc.perform(get("/studentList"))
            .andExpect(status().isOk())
            .andExpect(content().json("[]"));

    verify(service, times(1)).searchStudentList();
  }

  // ============================
  // ID検索テスト
  // ============================
  @Test
  void ID検索で受講生詳細が返る() throws Exception {
    String id = "100";
    Student student = new Student(id, "渡辺 恵子", "わたなべ けいこ", "けいこ",
            "unique.user1937@example.com", "東京都", 30, "女", "特になし", false);
    StudentCourse sc = new StudentCourse("100", id, "JAVAコース",
            LocalDate.parse("2024-01-01"), LocalDate.parse("2024-04-01"), null);
    StudentDetail detail = new StudentDetail(student, List.of(sc));

    when(service.searchStudent(id)).thenReturn(detail);

    mockMvc.perform(get("/student/{studentId}", id))
            .andExpect(status().isOk())
            .andExpect(content().json("""
                        {
                          "student": {
                            "studentId": "100",
                            "name": "渡辺 恵子",
                            "kanaName": "わたなべ けいこ",
                            "nickname": "けいこ",
                            "email": "unique.user1937@example.com",
                            "area": "東京都",
                            "age": 30,
                            "gender": "女",
                            "remark": "特になし",
                            "deleted": false
                          },
                          "studentCourseList": [
                            {
                              "courseId": "100",
                              "studentId": "100",
                              "courseName": "JAVAコース",
                              "startDate": "2024-01-01",
                              "endDate": "2024-04-01",
                              "applicationStatus": null
                            }
                          ]
                        }
                        """));

    verify(service, times(1)).searchStudent(id);
  }

  // ============================
  // コース検索テスト
  // ============================
  @Test
  void コース詳細検索で空が返る() throws Exception {
    String id = "100";
    when(service.searchStudentCourse(id)).thenReturn(List.of());

    mockMvc.perform(get("/course/{studentId}", id))
            .andExpect(status().isOk())
            .andExpect(content().json("[]"));

    verify(service, times(1)).searchStudentCourse(id);
  }

  // ============================
  // 新規登録テスト
  // ============================
  @Test
  void 受講生新規登録が実行できる() throws Exception {
    mockMvc.perform(post("/registerStudent")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                                {
                                  "student": {
                                    "name": "伊東 剛",
                                    "kanaName": "イトウツヨシ",
                                    "nickname": "つよ",
                                    "email": "tsuyoshi@example.com",
                                    "area": "東京",
                                    "age": 37,
                                    "gender": "男"
                                  },
                                  "studentCourseList": [
                                    {
                                      "courseName": "Java基礎"
                                    }
                                  ]
                                }
                                """))
            .andExpect(status().isOk());

    verify(service, times(1)).registerStudent(any());
  }

  // ============================
  // 更新テスト
  // ============================
  @Test
  void 受講生更新が実行できる() throws Exception {
    mockMvc.perform(put("/updateStudent")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                                {
                                  "student": {
                                    "studentId": "1",
                                    "name": "伊東 剛",
                                    "kanaName": "イトウツヨシ",
                                    "nickname": "つよ",
                                    "email": "tsuyoshi@example.com",
                                    "area": "東京",
                                    "age": 37,
                                    "gender": "男"
                                  },
                                  "studentCourseList": [
                                    {
                                      "courseId": "1",
                                      "studentId": "1",
                                      "courseName": "Java基礎"
                                    }
                                  ]
                                }
                                """))
            .andExpect(status().isOk());

    verify(service, times(1)).updateStudent(any());
  }

  // ============================
  // バリデーション正常
  // ============================
  @Test
  void Studentのバリデーション正常() {
    Student student = new Student();
    student.setStudentId("1");
    student.setName("伊東 剛");
    student.setKanaName("イトウツヨシ");
    student.setNickname("つよ");
    student.setEmail("tsuyoshi@example.com");
    student.setArea("東京都");
    student.setAge(37);
    student.setGender("男");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);
    assertThat(violations).isEmpty();
  }

  // ============================
  // Student ID バリデーション
  // ============================
  @Test
  void StudentのIDに数字以外でバリデーションエラー() {
    Student student = new Student();
    student.setStudentId("abc");
    student.setName("伊東 剛");
    student.setKanaName("イトウツヨシ");
    student.setNickname("つよ");
    student.setEmail("tsuyoshi@example.com");
    student.setArea("東京都");
    student.setAge(37);
    student.setGender("男");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);
    assertThat(violations).extracting("message")
            .containsOnly("数字のみ入力するようにしてください。");
  }

  // ============================
  // StudentCourse ID バリデーション
  // ============================
  @Test
  void StudentCourseのIDに数字以外でバリデーションエラー() {
    StudentCourse sc = new StudentCourse();
    sc.setCourseId("xyz");
    sc.setStudentId("abc");
    sc.setCourseName("Java基礎");

    Set<ConstraintViolation<StudentCourse>> violations = validator.validate(sc);
    assertThat(violations).extracting(
                    v -> v.getPropertyPath().toString(),
                    ConstraintViolation::getMessage)
            .containsExactlyInAnyOrder(
                    tuple("courseId", "数字のみ入力するようにしてください。"),
                    tuple("studentId", "数字のみ入力するようにしてください。")
            );
  }
}