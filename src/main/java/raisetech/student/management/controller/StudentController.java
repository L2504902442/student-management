package raisetech.student.management.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.student.management.data.Student;
import raisetech.student.management.domain.StudentSearchCondition;
import raisetech.student.management.service.StudentService;

@RestController
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping("/studentList")
    public List<Student> searchStudentList(StudentSearchCondition condition) {
        return service.searchStudentList(condition);
    }
}