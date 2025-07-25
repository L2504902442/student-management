package raisetech.student.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
public class Application {

	@Autowired
	private StudentRepository repository;

	@Autowired
	private StudentsCoursesRepository studentsCoursesRepository;



	public static void main(String[] args) {
	  SpringApplication.run(Application.class, args);
	}

	@GetMapping("/studentList")
	public List<Student> getStudentList() {
		 return repository.search();
	}

	@GetMapping("/students_coursesList")
	public List<StudentsCourses> getStudentsCoursesList() {
		return studentsCoursesRepository.search();
	}
}
