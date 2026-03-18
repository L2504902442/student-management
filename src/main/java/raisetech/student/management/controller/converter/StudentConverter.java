package raisetech.student.management.controller.converter;

import org.springframework.stereotype.Component;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentConverter {

    public List<Student> convertStudentDetails(List<Student> students, List<StudentCourse> courses) {

        return students.stream()
                .map(student -> {

                    List<StudentCourse> studentCourses = courses.stream()
                            .filter(course -> course.getStudentId() != null
                                    && course.getStudentId().equals(student.getStudentId()))
                            .collect(Collectors.toList());

                    student.setCourses(studentCourses);
                    return student;

                }).collect(Collectors.toList());
    }
}