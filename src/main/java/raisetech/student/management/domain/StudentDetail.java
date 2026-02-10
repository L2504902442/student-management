package raisetech.student.management.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class StudentDetail {

    @NotNull
    @Valid
    private Student student;

    @Valid
    private List<StudentCourse> studentCourseList;

    public StudentDetail() {
    }

    public StudentDetail(Student student, List<StudentCourse> studentCourseList) {
        this.student = student;
        this.studentCourseList = studentCourseList;
    }
}
