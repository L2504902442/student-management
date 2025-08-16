package raisetech.student.management.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;

@Mapper
public interface StudentRepository {

    @Select("SELECT * FROM students")
    List<Student> searchStudents();

    @Select("SELECT * FROM students_courses")
    List<StudentsCourses> searchStudentsCourses();

    @Insert("INSERT INTO students (name,kana_name,nickname,e_mail,area,age,gender,remark,isDeleted) " +
            "VALUES(#{name},#{kanaName},#{nickname},#{email},#{area},#{age},#{gender},#{remark},false)")
    void registerStudent(Student student);

}