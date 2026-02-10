package raisetech.student.management.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.student.management.controller.converter.StudentConverter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.CourseDetail;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final StudentConverter converter;

    public StudentService(StudentRepository repository, StudentConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public List<StudentDetail> searchStudentList() {
        return converter.convertStudentDetails(
                repository.search(),
                repository.searchStudentCourseList()
        );
    }

    public StudentDetail searchStudent(String studentId) {
        Student student = repository.searchStudent(studentId);
        if (student == null) return null;

        return new StudentDetail(
                student,
                repository.searchStudentCourse(studentId)
        );
    }

    public List<CourseDetail> searchCourseByStudentId(String studentId) {
        return repository.searchCourseDetailsByStudentId(studentId);
    }

    @Transactional
    public StudentDetail registerStudent(StudentDetail studentDetail) {
        Student student = studentDetail.getStudent();
        if (student.getDeleted() == null) {
            student.setDeleted(false);
        }

        repository.registerStudent(student);

        if (studentDetail.getStudentCourseList() != null) {
            studentDetail.getStudentCourseList().forEach(course -> {
                initStudentCourse(course, String.valueOf(student.getStudentId()));
                repository.registerStudentCourse(course);
            });
        }
        return studentDetail;
    }

    void initStudentCourse(StudentCourse course, String studentId) {
        LocalDate now = LocalDate.now();
        course.setStudentId(studentId);
        course.setStartDate(now);
        course.setEndDate(now.plusYears(1));
    }

    @Transactional
    public void updateStudent(StudentDetail studentDetail) {

        if (studentDetail.getStudent().getDeleted() == null) {
            studentDetail.getStudent().setDeleted(false);
        }
        repository.updateStudent(studentDetail.getStudent());

        if (studentDetail.getStudentCourseList() != null) {
            studentDetail.getStudentCourseList()
                    .forEach(repository::updateStudentCourse);
        }

    }
}