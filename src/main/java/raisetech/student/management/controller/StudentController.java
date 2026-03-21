package raisetech.student.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import raisetech.student.management.data.Student;
import raisetech.student.management.domain.StudentSearchCondition;
import raisetech.student.management.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/studentList")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public List<Student> searchStudentList(StudentSearchCondition condition) {
        return service.searchStudentList(condition);
    }
}