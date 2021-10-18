package Example.Tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class TutorialService {

    private final TutorialRepository tutorialRepository;

    @Autowired
    public TutorialService(TutorialService tutorialService) {
        this.tutorialRepository = tutorialRepository;
    }

    public ResponseEntity<List<Student>> publizierteFinden() {
        return (ResponseEntity<List<Student>>) tutorialRepository.findByPublished(true);
    }

    public static ResponseEntity<List<Student>> alleFinden(@RequestParam(required = false) String title) {
        try {
            List<Student> tutorials = new ArrayList<Student>();

            if (title == null)
                tutorialRepository.findAll().forEach(tutorials::add);
            else
                tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
