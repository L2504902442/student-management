package raisetech.student.management.controller.converter;

import org.springframework.stereotype.Component;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentConverter {

    public List<StudentDetail> convertStudentDetails(List<Student> students, List<StudentCourse> courses) {

        return students.stream()
                .map(student -> {

                    List<StudentCourse> studentCourses = courses.stream()
                            .filter(course -> student.getStudentId().equals(course.getStudentId()))
                            .collect(Collectors.toList());

                    StudentDetail detail = new StudentDetail();
                    detail.setStudent(student);
                    detail.setStudentCourseList(studentCourses);

                    return detail;

                }).collect(Collectors.toList());
    }
}