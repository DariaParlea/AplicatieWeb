package web.app.project.project.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.app.project.project.repositories.CertificateRepository;
import web.app.project.project.entities.*;

import java.util.Date;


@Service
public class CertificateService {

    private final CertificateRepository certificateRepository;

    @Autowired
    public CertificateService(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    public Certificate generateCertificate(Student student, University university) {
        Certificate certificate = new Certificate();

        certificate.setStudent(student);
        certificate.setUniversity(university);
        certificate.setIssuanceDate(new Date());

        return certificateRepository.save(certificate);
    }

}
