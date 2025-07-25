package raisetech.student.management;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface StudentsCoursesRepository {
    @Select("SELECT * FROM students_courses")
    List<StudentsCourses> search();
}

