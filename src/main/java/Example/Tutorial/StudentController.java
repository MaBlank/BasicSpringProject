package Example.Tutorial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/v1/student")
@RestController
public class StudentController {

	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

/*
	@GetMapping
	public List<Student> getStudents() {
		ArrayList<Student> Liste = new ArrayList<>((studentService.getStudents()));
		Liste.sort(new GehaltSorter());
		return Liste;
	}
*/

	@GetMapping
	public List<Student> getStudents() {
		return studentService.getStudents();
	}


	@GetMapping(path = "/reich")
	public List<Student> getStudentsrich() {
		return studentService.getStudentsrich();
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
