package raisetech.student.management.repository;

import java.util.List;
import org.apache.ibatis.annotations.*;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;

@Mapper
public interface StudentRepository {

    @Select("SELECT * FROM students WHERE isDeleted = 0")
    List<Student> searchStudents();

    @Select("SELECT * FROM students WHERE Student_id = #{studentId}")
    Student searchStudent(@Param("studentId") String studentId);

    @Select("SELECT * FROM students_courses")
    List<StudentsCourses> searchStudentsCoursesList();

    @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
    List<StudentsCourses> searchStudentsCourses(@Param("studentId")String studentId);

    @Insert("INSERT INTO students (name,kana_name,nickname,e_mail,area,age,gender,remark,isDeleted) "
            + "VALUES(#{name},#{kanaName},#{nickname},#{email},#{area},#{age},#{gender},#{remark},false)")
    @Options(useGeneratedKeys = true, keyProperty = "studentId",keyColumn = "Student_id")
    void registerStudent(Student student);

    @Insert("INSERT INTO students_courses(student_id, course_name, start_date, end_date) "
            + "VALUES(#{studentId}, #{courseName}, #{startDate}, #{endDate})")
    @Options(useGeneratedKeys = true, keyProperty = "courseId",keyColumn = "coursr_id")
    void registerStudentsCourses(StudentsCourses studentsCourses);

    @Update("UPDATE students SET name = #{name}, kana_name = #{kanaName}, nickname = #{nickname},"
    +"e_mail = #{email}, area = #{area}, age = #{age}, gender = #{gender}, remark = #{remark}, isDeleted = #{isDeleted} WHERE Student_id = #{studentId} ")
    void updateStudent(Student student);

    @Update("UPDATE students_courses SET course_name = #{courseName} WHERE student_id = #{studentId}")
    void updateStudentsCourses(StudentsCourses studentsCourses);


}