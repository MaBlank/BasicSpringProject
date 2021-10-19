package Example.Tutorial;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Transactional
    @Modifying
    @Query("delete from Student s where gehalt =:gehalt")
    void deleteStudentgehalt(@Param("gehalt") long gehalt);

    // Optional<Student> findStudentbyID(Long id);
}
