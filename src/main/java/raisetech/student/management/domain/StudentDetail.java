package raisetech.student.management.domain;

import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;

import java.util.List;

public class StudentDetail {

    private Student student;

    private List<StudentCourse> studentCourseList;

    public Student getStudent() {
        return student;
    }

    public List<StudentCourse> getStudentCourseList() {
        return studentCourseList;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setStudentCourseList(List<StudentCourse> studentCourseList) {
        this.studentCourseList = studentCourseList;
    }
}