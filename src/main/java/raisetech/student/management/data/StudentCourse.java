package raisetech.student.management.data;

import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "受講生コース情報")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class StudentCourse {

    @Pattern(regexp = "^\\d+$", message = "数字のみ入力するようにしてください。")
    private String courseId;

    @Pattern(regexp = "^\\d+$", message = "数字のみ入力するようにしてください。")
    private String studentId;

    @NotBlank(message = "コース名は必須です")
    private String courseName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}

