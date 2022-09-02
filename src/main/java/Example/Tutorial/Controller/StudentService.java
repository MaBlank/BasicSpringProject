package Example.Tutorial.Controller;

import Example.Tutorial.Entity.Student;
import Example.Tutorial.Entity.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.
                findAll().
                stream().
                sorted(Comparator.comparing(Student::getGehalt).reversed().thenComparing(Student::getAge)).
                collect(Collectors.toList());
    }

    public List<Student> getStudentsRich(int gehalt) {
        return studentRepository.
                findAll().
                stream().
                filter(Student -> Student.getGehalt() > gehalt).
                collect(Collectors.toList());
    }

    public Optional<Student> getStudentsbyId(Long id) {
        return Optional
                .ofNullable(studentRepository
                        .findById(id)
                        .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found")));
    }

    public void addNewStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalArgumentException(
               "student does not exist"
            );
        }
        studentRepository.deleteById(id);
    }

    public void deleteStudentgehalt(long gehalt) {
        studentRepository.deleteStudentgehalt(gehalt);
    }

    public void update(Long id, Student student) {
        studentRepository.findById(id).
        map(s -> {
            s.setGehalt(student.getGehalt());
            return studentRepository.save(s);
        });
    }
}