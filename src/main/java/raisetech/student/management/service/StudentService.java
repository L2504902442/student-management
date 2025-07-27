package raisetech.student.management.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.student.management.data.Student;
import raisetech.student.management.data.StudentsCourses;
import raisetech.student.management.repository.StudentRepository;

@Service
public class StudentService {

    private StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> searchStudentList() {
        return repository.search().stream()
                .filter(student -> student.getAge() >= 30 && student.getAge() < 40 )
                .collect(Collectors.toList());

    }

    public List<StudentsCourses> searchStudentsCoursesList() {
        return repository.searchStudentsCourseList().stream()
                .filter(courses -> "Java基礎".equals(courses.getCourseName()))
                .collect(Collectors.toList());
    }
}
