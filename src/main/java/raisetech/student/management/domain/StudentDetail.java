package raisetech.student.management.domain;

import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import java.util.ArrayList;
import java.util.List;

public class StudentDetail {

    private Student student;
    private List<StudentCourse> studentCourseList = new ArrayList<>();

    public StudentDetail() {}

    public StudentDetail(Student student, List<StudentCourse> studentCourseList) {
        this.student = student;
        this.studentCourseList = studentCourseList;
    }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public List<StudentCourse> getStudentCourseList() { return studentCourseList; }
    public void setStudentCourseList(List<StudentCourse> studentCourseList) { this.studentCourseList = studentCourseList; }
}