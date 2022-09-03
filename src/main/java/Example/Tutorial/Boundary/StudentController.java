package Example.Tutorial.Boundary;

import java.util.ArrayList;
import java.util.List;

import Example.Tutorial.Controller.StudentService;
import Example.Tutorial.Entity.GehaltSorter;
import Example.Tutorial.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/v1/student")
@RestController
public class StudentController {
	@Autowired
	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping
	public List<Student> getStudentsplain() {
		ArrayList<Student> Liste = new ArrayList<>((studentService.getStudents()));
		Liste.sort(new GehaltSorter());
		return Liste;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Student>> getStudents() {
		List<Student> students = (List<Student>) studentService.getStudents();
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

	@GetMapping(path = "/reich/{gehalt}")
	public List<Student> getStudentsRich(@PathVariable("gehalt") int gehalt ) {
		return studentService.getStudentsRich(gehalt);
		// 1,2,3
	}

	@GetMapping(path = "/{id}")
	public Student getStudentsbyId(@PathVariable("id") final Long id) {
		return studentService.getStudentsbyId(id).get();
		// 1,2,3
	}


	@DeleteMapping(path = "/{id}")
	public void deleteStudent(@PathVariable("id") long id) {
		studentService.deleteStudent(id);
	}

	@DeleteMapping(path = "/gehalt/{gehalt}")
	public void deleteStudentgehalt(@PathVariable("gehalt") long gehalt) {
		studentService.deleteStudentgehalt(gehalt);
	}


	@PostMapping
	public void  registerNewStudent (@RequestBody Student student) {
		studentService.addNewStudent(student);
	}


	@PutMapping(path = "/{id}")
	public void update(@PathVariable("id") final Long id, @RequestBody final Student student) {
		studentService.update(id, student);
	}

}
