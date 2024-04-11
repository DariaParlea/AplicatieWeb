package web.app.project.project.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import web.app.project.project.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByName(String name);
}
