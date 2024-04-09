package web.app.project.project.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.app.project.project.entities.Certificate;
import org.springframework.data.repository.CrudRepository;


public interface CertificateRepository extends JpaRepository<Certificate, Long> {

}
