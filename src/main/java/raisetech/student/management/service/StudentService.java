package raisetech.student.management.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.student.management.data.Student;
import raisetech.student.management.domain.StudentSearchCondition;
import raisetech.student.management.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> searchStudentList(StudentSearchCondition condition) {
        return repository.searchStudentList(condition);
    }
}