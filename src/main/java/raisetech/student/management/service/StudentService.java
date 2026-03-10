package raisetech.student.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.student.management.controller.converter.StudentConverter;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentCourse;
import raisetech.student.management.domain.StudentDetail;
import raisetech.student.management.domain.StudentSearchCondition;
import raisetech.student.management.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private StudentConverter converter;

    public List<StudentDetail> searchStudentList(StudentSearchCondition condition) {

        List<Student> studentList = repository.searchStudentList(condition);
        List<StudentCourse> courseList = repository.searchStudentCourseList();

        return converter.convertStudentDetails(studentList, courseList);
    }
}