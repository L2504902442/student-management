package raisetech.student.management.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import raisetech.student.management.data.Student;
import raisetech.student.management.domain.StudentSearchCondition;

@Mapper
public interface StudentRepository {

    List<Student> searchStudentList(StudentSearchCondition condition);

}