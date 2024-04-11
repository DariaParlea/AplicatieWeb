package web.app.project.project.service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.app.project.project.repositories.CertificateRepository;
import web.app.project.project.entities.*;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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

    public byte[] generateCertificate(Student student, University university, String title) {
        try {
            PDDocument document = new PDDocument();
            PDRectangle landscape = new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth());
            PDPage page = new PDPage(landscape);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

                PDImageXObject backgroundImage = PDImageXObject.createFromFile("src/main/resources/static/certificate_bg.jpg", document);

                contentStream.drawImage(backgroundImage, 0, 0, (int) PDRectangle.A4.getHeight(), (int) PDRectangle.A4.getWidth());

                PDImageXObject logo = PDImageXObject.createFromFile("src/main/resources/static/university_logo.png", document);
                float logoWidth = logo.getWidth() / 20;
                float logoHeight = logo.getHeight() / 20;
                contentStream.drawImage(logo, 50, 550 - logoHeight, logoWidth, logoHeight);

                contentStream.beginText();
                PDType0Font customFont = PDType0Font.load(document, new File("src/main/resources/static/RougeScript-Regular.ttf"));
                PDType0Font yourFont = PDType0Font.load(document, new FileInputStream("src/main/resources/static/LeckerliOne-Regular.ttf"));

                contentStream.setFont(customFont, 30);
                contentStream.setNonStrokingColor(Color.BLACK);
                contentStream.newLineAtOffset(400, 500);
                contentStream.showText( university.getName());


                contentStream.setFont(yourFont, 50);
                contentStream.setNonStrokingColor(Color.BLACK);

                float textWidth = yourFont.getStringWidth("Adeverinta de Student") / 1000f * 30;

                float startX = (PDRectangle.A4.getHeight() - textWidth) / 2;

                float x1=-200;
                float y1 = -70;
                contentStream.newLineAtOffset(x1,y1);
                contentStream.showText("Adeverinta de Student");

                contentStream.setFont(customFont, 30);
                contentStream.setNonStrokingColor(Color.BLACK);

                String prefixText = "Acordata doamnei/lui: ";
                float prefixWidth = customFont.getStringWidth(prefixText) / 1000f * 20;
                float startX1 = (PDRectangle.A4.getHeight() - prefixWidth) / 2;

                float x2 = startX1 - startX +50;
                float y2 = -60;
                contentStream.setFont(customFont, 30);
                contentStream.setNonStrokingColor(Color.BLACK);
                contentStream.newLineAtOffset(x2, y2);
                contentStream.showText("Acordata doamnei/lui: ");

                float studentNameWidth = customFont.getStringWidth(student.getName()) / 1000f * 40;
                float startX2 = (PDRectangle.A4.getHeight() - studentNameWidth) / 2;

                float x3 = startX2 - startX1;
                contentStream.setFont(customFont, 50);
                contentStream.newLineAtOffset(x3, y2);
                contentStream.showText(student.getName());


                contentStream.setFont(customFont, 30);
                contentStream.setNonStrokingColor(Color.BLACK);


                float maxWidth = 500;
                float startY = -60;

                justifyAndWrapText(contentStream, customFont, 30, "Pentru: " + title, -80, startY, maxWidth);

                float x6 = -140;
                float y6 = -150;
                contentStream.newLineAtOffset(x6, y6);
                contentStream.showText("La data de: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));



                contentStream.endText();
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            document.close();

            Certificate certificate = new Certificate();
            certificate.setStudent(student);
            certificate.setUniversity(university);
            certificate.setIssuanceDate(new Date());
            certificate.setTitle(title);

            certificateRepository.save(certificate);

            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }



    private void justifyAndWrapText(PDPageContentStream contentStream, PDType0Font font, int fontSize, String text, float startX, float startY, float maxWidth) throws IOException {
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        float lineWidth = 0;
        float lineSpacingFactor = fontSize * 0f;

        for (String word : words) {
            float wordWidth = font.getStringWidth(word) / 1000f * fontSize;

            if (lineWidth + wordWidth < maxWidth) {
                line.append(word).append(" ");
                lineWidth += wordWidth;
            } else {
                contentStream.newLineAtOffset(startX, startY);
                contentStream.showText(line.toString().trim());
                startY -= lineSpacingFactor -10;
                line = new StringBuilder(word + " ");
                lineWidth = wordWidth;
            }
        }


        contentStream.newLineAtOffset(startX, startY);
        contentStream.showText(line.toString().trim());
    }



}
