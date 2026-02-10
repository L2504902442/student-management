package raisetech.student.management.repository;

import org.apache.ibatis.annotations.Mapper;
import raisetech.student.management.data.ApplicationStatus;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.CourseDetail;

import java.util.List;

@Mapper
public interface StudentRepository {

    // 受講生
    List<Student> search();

    Student searchStudent(String studentId);

    void registerStudent(Student student);

    void updateStudent(Student student);

    // 受講生コース
    List<StudentCourse> searchStudentCourseList();

    List<StudentCourse> searchStudentCourse(String studentId);

    void registerStudentCourse(StudentCourse studentCourse);

    void updateStudentCourse(StudentCourse studentCourse);

    // 申込状況
    void registerApplicationStatus(ApplicationStatus applicationStatus);

    List<CourseDetail> searchCourseDetailsByStudentId(String studentId);
}