package web.app.project.project.service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.app.project.project.repositories.CertificateRepository;
import web.app.project.project.entities.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class CertificateService {

    private final CertificateRepository certificateRepository;

    @Autowired
    public CertificateService(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

//    public Certificate generateCertificate(Student student, University university, String title) {
//        Certificate certificate = new Certificate();
//
//        certificate.setStudent(student);
//        certificate.setUniversity(university);
//        certificate.setIssuanceDate(new Date());
//
//        return certificateRepository.save(certificate);
//    }

    public byte[] generateCertificate(Student student, University university, String title) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
                contentStream.newLineAtOffset(100, 750);
                contentStream.showText("Certificate of Maturity");

                contentStream.setFont(PDType1Font.HELVETICA, 14);
                contentStream.newLineAtOffset(0, -50);
                contentStream.showText("Awarded to: " + student.getName());

                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("For: " + title);

                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Issued on: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("At: " + university.getName());

                contentStream.endText();
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }




    public Certificate saveCertificate(Certificate certificate) {
        return certificateRepository.save(certificate);
    }

}
