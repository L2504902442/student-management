package raisetech.student.management.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CourseDetail {

    private String studentId;

    private String courseId;

    private String courseName;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int applicationStatusId;

    private String applicationStatus;
}