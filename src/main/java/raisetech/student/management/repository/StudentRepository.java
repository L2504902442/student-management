package raisetech.student.management.repository;

import java.util.List;
import org.apache.ibatis.annotations.*;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;

/**
 * 受講生テーブルと受講生コース情報テーブルと紐づくRepositoryです。
 */

@Mapper
public interface StudentRepository {

    /**
     * 受講生の全件検索を行います。
     *
     * @return　受講生一覧(全件)
     */

    @Select("SELECT * FROM students WHERE isDeleted = 0")
    List<Student> searchStudents();

    /**
     * 受講生の検索を行います。
     *
     * @param studentId　受講生ID
     * @return 受講生
     */

    @Select("SELECT * FROM students WHERE Student_id = #{studentId}")
    Student searchStudent(@Param("studentId") String studentId);

    /**
     * 受講生のコース情報の全件検索を行います。
     *
     * @return　受講生のコース情報(全件)
     */

    @Select("SELECT * FROM students_courses")
    List<StudentsCourses> searchStudentsCoursesList();

    /**
     * 受講生IDに紐づく受講生コース情報を検索します。
     *
     * @param studentId　受講生ID
     * @return 受講生IDに紐づく受講生コース情報
     */

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