package web.app.project.project.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import web.app.project.project.entities.University;

public interface UniversityRepository extends JpaRepository<University, Long> {

    University findByName(String name);

}
