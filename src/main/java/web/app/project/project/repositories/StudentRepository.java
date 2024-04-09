package web.app.project.project.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.app.project.project.entities.Student;
import org.springframework.data.repository.CrudRepository;


public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByName(String name);
}
