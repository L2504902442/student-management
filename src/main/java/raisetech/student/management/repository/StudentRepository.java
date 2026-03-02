package raisetech.student.management.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import raisetech.student.management.data.Student;
import java.util.List;

@Mapper
public interface StudentRepository {
    List<Student> searchStudents(
            @Param("name") String name,
            @Param("area") String area,
            @Param("isDeleted") Boolean isDeleted
    );
}