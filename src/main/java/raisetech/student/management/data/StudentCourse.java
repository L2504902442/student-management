package raisetech.student.management.data;

import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生コース情報")
@Getter
@Setter

public class StudentCourse {

    private String courseId;

    private String studentId;

    @NotBlank
    private String courseName;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate expectedCompletionDate;

    private ApplicationStatus status;

    public StudentCourse(
            String studentId,
            String courseName,
            LocalDate startDate,
            LocalDate expectedCompletionDate
    ) {
        this.studentId = studentId;
        this.courseName = courseName;
        this.startDate = startDate;
        this.expectedCompletionDate = expectedCompletionDate;
    }

    /** 引数付きコンストラクタ テスト専用(idあり) */
    public StudentCourse(String courseId, String studentId, String courseName,
                         LocalDate startDate, LocalDate expectedCompletionDate) {
        this(studentId, courseName, startDate, expectedCompletionDate);
        this.courseId = courseId;
    }

    /** JPA用にデフォルトコンストラクタ */
    public StudentCourse() {}

}


