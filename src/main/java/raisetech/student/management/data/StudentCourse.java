package raisetech.student.management.data;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourse {

    @Pattern(regexp = "^\\d+$", message = "数字のみ入力するようにしてください。")
    private String courseId;

    @Pattern(regexp = "^\\d+$", message = "数字のみ入力するようにしてください。")
    private String studentId;

    private String courseName;
    private LocalDate startDate;
    private LocalDate endDate;

    private String applicationStatus;
}