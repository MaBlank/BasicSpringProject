package Example.Tutorial;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialRepository extends JpaRepository<Student, Long> {
  List<Student> findByPublished(boolean published);
  List<Student> findByTitleContaining(String title);
}
