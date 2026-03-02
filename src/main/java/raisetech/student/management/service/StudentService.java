package raisetech.student.management.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import raisetech.student.management.data.Student;
import raisetech.student.management.repository.StudentRepository;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> searchStudentList(String name, String area, Boolean isDeleted) {
        return studentRepository.searchStudents(name, area, isDeleted);
    }
}