package raisetech.student.management.repository;

import java.util.List;

import org.apache.ibatis.annotations.*;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;

@Mapper
public interface StudentRepository {

    @Select("SELECT * FROM students")
    List<Student> searchStudents();

    @Select("SELECT * FROM students WHERE studentId = #{studentId}")
    Student searchStudent(String studentId);

    @Select("SELECT * FROM students_courses")
    List<StudentsCourses> searchStudentsCoursesList();

    @Select("SELECT * FROM students_courses WHERE student_Id = #{studentId}")
    List<StudentsCourses> searchStudentsCourses(String studentId);

    @Insert("INSERT INTO students (name,kana_name,nickname,e_mail,area,age,gender,remark,isDeleted) "
            + "VALUES(#{name},#{kanaName},#{nickname},#{email},#{area},#{age},#{gender},#{remark},false)")
    @Options(useGeneratedKeys = true, keyProperty = "studentId")
    void registerStudent(Student student);

    @Insert("INSERT INTO students_courses(student_Id, course_Name, start_date, end_date) "
            + "VALUES(#{studentId}, #{courseName}, #{startDate}, #{endDate})")
    @Options(useGeneratedKeys = true, keyProperty = "courseId")
    void registerStudentsCourses(StudentsCourses studentsCourses);

    @Update("UPDATE students SET name = #{name}, kana_name = #{kanaName}, nickname = #{nickname},"
    +"e_mail = #{email}, area = #{area}, age = #{age}, gender = #{gender}, remark = #{remark}, isDeleted = #{isDeleted} WHERE student_id = #{id} ")
    void updateStudent(Student student);

    @Update("UPDATE students_courses SET courseName = #{courseName} WHERE student_id = #{id}")
    void updateStudentsCourses(StudentsCourses studentsCourses);


}