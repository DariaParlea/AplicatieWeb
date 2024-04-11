package web.app.project.project.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import web.app.project.project.entities.Certificate;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {

}
