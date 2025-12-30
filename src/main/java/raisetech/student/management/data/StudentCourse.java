package raisetech.student.management.data;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Getter
@Setter
public class StudentCourse {


    @Pattern(regexp = "\\d+", message = "数字のみ入力するようにしてください。")
    private String courseId;


    @Pattern(regexp = "\\d+", message = "数字のみ入力するようにしてください。")
    private String studentId;

    @NotBlank(message = "必須入力です。")
    private String courseName;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public StudentCourse() {}

    public StudentCourse(String courseId, String studentId, String courseName,
                         LocalDateTime startDate, LocalDateTime endDate) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.courseName = courseName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}