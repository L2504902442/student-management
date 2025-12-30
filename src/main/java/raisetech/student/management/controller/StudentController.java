package raisetech.student.management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import raisetech.student.management.domain.CourseDetail;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.service.StudentService;

/**
 * 受講生の検索や登録、更新などを行うREST APIとして受け付けるControllerです。
 */
@Validated
@RestController
public class StudentController {

    private StudentService service;

    /**
     * コンストラクタ
     *
     * @param service 　受講生サービス
     */
    @Autowired
    public StudentController(StudentService service) {
        this.service = service;
    }

    /**
     * 受講生詳細の一覧検索です。
     * 全件検索を行うので、条件指定は行いません。
     *
     * @return 受講生詳細一覧(全件)
     */
    @Operation(summary = "受講生一覧検索", description = "受講生の一覧を検索します。")
    @GetMapping("/studentList")
    public List<StudentDetail> getStudentList() {
        return service.searchStudentList();
    }

    /**
     * 例外を発生させます。
     */
    @GetMapping("/students")
    public ResponseEntity<Map<String, Object>> studentsNotAvailable(HttpServletRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("status", 400);
        body.put("error", "Bad Request");
        body.put("message",
                "現在このAPIは利用できません。URLは「students」ではなく「studentList」を利用してください。");
        body.put("path", request.getRequestURI());

        return ResponseEntity.badRequest().body(body);
    }

    /**
     * 受講生詳細の検索です。
     * IDに紐づく任意の受講生の情報を取得します。
     *
     * @param studentId 　受講生ID
     * @return 受講生
     */

    @Operation(summary = "受講生詳細検索", description = "IDに紐づく任意の受講生を検索します。")
    @GetMapping("/student/{studentId}")
    public StudentDetail getStudent(
            @Parameter(
                description = "受講生ID",
                required = true,
                example = "1"
            )
            @PathVariable @NotBlank @Pattern(regexp = "^\\d+$") String studentId) {
        return service.searchStudent(studentId);
    }

    //受講生IDによる受講コース詳細情報の検索
    @GetMapping("/course/{studentId}")
    public List<CourseDetail> searchCourse(@PathVariable @NotBlank @Pattern(regexp = "^\\d+$") String studentId) {
        return service.searchCourseByStudentId(studentId);
    }

    /**
     * 受講生詳細の登録を行います。
     *
     * @param studentDetail 　受講生詳細
     * @return 実行結果
     */
    @Operation(summary = "受講生登録", description = "受講生を登録します。")
    @PostMapping("/registerStudent")
    public ResponseEntity<StudentDetail> registerStudent(
            @RequestBody @Valid StudentDetail studentDetail) {
        StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
        return ResponseEntity.ok(responseStudentDetail);
    }

    /**
     * 受講生詳細の更新を行います。　キャンセルフラグの更新もここで行います。(論理削除)
     *
     * @param studentDetail 　受講生詳細
     * @return 実行結果
     */
    @Operation(summary = "受講生更新", description = "受講生を更新します。")
    @PutMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody @Valid StudentDetail studentDetail) {
        service.updateStudent(studentDetail);
        return ResponseEntity.ok("更新処理が成功しました。");
    }
}