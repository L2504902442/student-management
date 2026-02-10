package raisetech.student.management.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import raisetech.student.management.domain.CourseDetail;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.service.StudentService;

@Validated
@RestController
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // 正しい一覧取得API
    @GetMapping("/studentList")
    public List<StudentDetail> getStudentList() {
        return service.searchStudentList();
    }

    // 間違ったURLにアクセスされた場合のエラーレスポンス
    @GetMapping("/students")
    public ResponseEntity<Map<String, Object>> students(HttpServletRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", 400);
        body.put("error", "Bad Request");
        body.put(
                "message",
                "現在このAPIは利用できません。URLは「students」ではなく「studentList」を利用してください。"
        );
        body.put("path", request.getRequestURI());

        return ResponseEntity.badRequest().body(body);
    }

    // 受講生ID検索
    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDetail> getStudent(
            @PathVariable("id")
            @NotBlank
            @Pattern(regexp = "^\\d+$")
            String studentId) {

        StudentDetail student = service.searchStudent(studentId);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(student);
    }

    // 受講生IDに紐づくコース検索
    @GetMapping("/course/{studentId}")
    public ResponseEntity<List<CourseDetail>> getCourse(
            @PathVariable
            @NotBlank
            @Pattern(regexp = "^\\d+$")
            String studentId) {

        List<CourseDetail> courses = service.searchCourseByStudentId(studentId);
        return ResponseEntity.ok(courses);
    }

    // 受講生登録
    @PostMapping("/registerStudent")
    public ResponseEntity<StudentDetail> registerStudent(
            @RequestBody @Valid StudentDetail studentDetail) {
        return ResponseEntity.ok(service.registerStudent(studentDetail));
    }

    // 受講生更新
    @PutMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(
            @RequestBody @Valid StudentDetail studentDetail) {
        service.updateStudent(studentDetail);
        return ResponseEntity.ok("更新処理が成功しました。");
    }
}