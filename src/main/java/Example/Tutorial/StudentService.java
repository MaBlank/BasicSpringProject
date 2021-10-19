package Example.Tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
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

    public List<Student> getStudentsrich() {
        return studentRepository.
                findAll().
                stream().
                filter(Student -> Student.getGehalt() > 5000).
                collect(Collectors.toList());
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