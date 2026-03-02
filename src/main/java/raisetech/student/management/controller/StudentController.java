package raisetech.student.management.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import raisetech.student.management.data.Student;
import raisetech.student.management.service.StudentService;
import java.util.List;

@RestController
@RequestMapping("/studentList")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudentList(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String area,
            @RequestParam(required = false) Boolean isDeleted
    ) {
        return studentService.searchStudentList(name, area, isDeleted);
    }
}