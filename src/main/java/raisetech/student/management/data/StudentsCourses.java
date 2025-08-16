package raisetech.student.management.data;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StudentsCourses {

    private String courseId;
    private Integer studentId;
    private String courseName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}

