package raisetech.student.management.repository;

import org.apache.ibatis.annotations.Mapper;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentSearchCondition;
import java.util.List;

@Mapper
public interface StudentRepository {

    List<Student> searchStudentList(StudentSearchCondition condition);

    List<StudentCourse> searchStudentCourseList();
}