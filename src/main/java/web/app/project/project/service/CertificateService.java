package web.app.project.project.service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
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

//    public Certificate generateCertificate(Student student, University university, String title) {
//        Certificate certificate = new Certificate();
//
//        certificate.setStudent(student);
//        certificate.setUniversity(university);
//        certificate.setIssuanceDate(new Date());
//
//        return certificateRepository.save(certificate);
//    }

//    public byte[] generateCertificate(Student student, University university, String title) {
//        try {
//            PDDocument document = new PDDocument();
//            PDPage page = new PDPage(PDRectangle.A4);
//            document.addPage(page);
//
//            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
//                // Add background color (sage green)
//                contentStream.setNonStrokingColor(new Color(189, 204, 182)); // RGB values for sage green
//                contentStream.fillRect(0, 0, (int) PDRectangle.A4.getWidth(), (int) PDRectangle.A4.getHeight());
//
//                // Add border
//                contentStream.setStrokingColor(Color.BLACK);
//                contentStream.addRect(50, 50, (int) PDRectangle.A4.getWidth() - 100, (int) PDRectangle.A4.getHeight() - 100);
//                contentStream.stroke();
//
//                // Add school logo (scaled to be smaller)
//                PDImageXObject logo = PDImageXObject.createFromFile("src/main/resources/static/university_logo.png", document);
//                float logoWidth = logo.getWidth() / 30; // Adjust the divisor to change the size of the logo
//                float logoHeight = logo.getHeight() / 30;
//                contentStream.drawImage(logo, 60, 780 - logoHeight, logoWidth, logoHeight);
//
//                contentStream.beginText();
//                PDType0Font yourFont = PDType0Font.load(document, new FileInputStream("src/main/resources/static/LeckerliOne-Regular.ttf"));
//
//                // Set the font and other properties for "Adeverinta de Student" text
//                contentStream.setFont(yourFont, 30); // Larger font size
//                contentStream.setNonStrokingColor(Color.BLACK);
//
//                // Calculate the width of the text
//                float textWidth = yourFont.getStringWidth("Adeverinta de Student") / 1000f * 30; // 30 is the font size
//
//                // Calculate the x-coordinate to center the text horizontally
//                float startX = (PDRectangle.A4.getWidth() - textWidth) / 2;
//
//                // Set the starting point and show the text
//                contentStream.newLineAtOffset(startX, 730); // Higher position
//                contentStream.showText("Adeverinta de Student");
//
////// Set the font and other properties
////                contentStream.setFont(yourFont, 20);
////                contentStream.setNonStrokingColor(Color.BLACK);
////
////// Calculate the width of the text
////                float textWidth = yourFont.getStringWidth("Adeverinta de Student") / 1000f * 20; // 20 is the font size
////
////// Calculate the x-coordinate to center the text horizontally
////                float startX = (PDRectangle.A4.getWidth() - textWidth) / 2;
////
////// Set the starting point and show the text
////                contentStream.newLineAtOffset(startX, 700);
////                contentStream.showText("Adeverinta de Student");
//
//
////                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
////                contentStream.setNonStrokingColor(Color.BLACK); // Set text color to black
////                contentStream.newLineAtOffset(100, 700);
////                contentStream.showText("Adeverinta de Student");
//
////                contentStream.setFont(PDType1Font.HELVETICA, 14);
////                contentStream.setNonStrokingColor(Color.BLACK);
//                // Load the custom font
//                PDType0Font customFont = PDType0Font.load(document, new File("src/main/resources/static/RougeScript-Regular.ttf"));
//
//// Set the font and other properties for the remaining text
////                contentStream.setFont(customFont, 14);
////                contentStream.setNonStrokingColor(Color.BLACK);
////                contentStream.newLineAtOffset(0, -50);
////                contentStream.showText("Acordata doamnei/lui: " + student.getName());
//                String prefixText = "Acordata doamnei/lui: ";
//                float prefixWidth = customFont.getStringWidth(prefixText) / 1000f * 20;
//
//// Calculate the x-coordinate to center the prefix text horizontally
//                float startX1 = (PDRectangle.A4.getWidth() - prefixWidth) / 2;
//
//
//                // Set the font and other properties for the following text
//                contentStream.setFont(customFont, 20); // Larger font size
//                contentStream.setNonStrokingColor(Color.BLACK);
//                contentStream.newLineAtOffset(startX1-startX, -50);
//                contentStream.showText("Acordata doamnei/lui: "); // Removed the name to show it separately
//
//                // Calculate the width of the student's name
//                float studentNameWidth = customFont.getStringWidth(student.getName()) / 1000f * 40;
//
//// Calculate the x-coordinate to center the student's name horizontally
//                float startX2 = (PDRectangle.A4.getWidth() - studentNameWidth) / 2;
//
//
//                // Show the student's name
//                contentStream.setFont(customFont, 40);
//                contentStream.newLineAtOffset(startX2-startX1, -30); // Adjust the vertical spacing
//                contentStream.showText(student.getName()); // Show the student's name
//
//
//                contentStream.setFont(customFont, 20);
//                contentStream.newLineAtOffset(0, -20);
//                contentStream.showText("Pentru: " + title);
//
//                contentStream.newLineAtOffset(0, -20);
//                contentStream.showText("La data de: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
//
//                contentStream.newLineAtOffset(0, -20);
//                contentStream.showText("Universitate: " + university.getName());
//
//                contentStream.endText();
//            }
//
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            document.save(byteArrayOutputStream);
//            document.close(); // Close the document after saving
//
//            return byteArrayOutputStream.toByteArray();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new byte[0]; // Return an empty byte array if an error occurs
//    }

    public byte[] generateCertificate(Student student, University university, String title) {
        try {
            PDDocument document = new PDDocument();
            PDRectangle landscape = new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth());
            PDPage page = new PDPage(landscape);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
//                // Add background color (sage green)
//                contentStream.setNonStrokingColor(new Color(189, 204, 182)); // RGB values for sage green
//                contentStream.fillRect(0, 0, (int) PDRectangle.A4.getHeight(), (int) PDRectangle.A4.getWidth()); // Height and width are swapped for landscape
//
//// Add border with a fancier style
//                float[] dashPattern = {5, 5}; // Adjust the dash pattern as needed
//                contentStream.setStrokingColor(Color.BLACK);
//                contentStream.setLineDashPattern(dashPattern, 0);
//                contentStream.addRect(50, 50, (int) PDRectangle.A4.getHeight() - 100, (int) PDRectangle.A4.getWidth() - 100); // Height and width are swapped for landscape
//                contentStream.stroke();

                PDImageXObject backgroundImage = PDImageXObject.createFromFile("src/main/resources/static/certificate_bg.jpg", document);

                // Draw the background image
                contentStream.drawImage(backgroundImage, 0, 0, (int) PDRectangle.A4.getHeight(), (int) PDRectangle.A4.getWidth());

                // Add school logo (scaled to be smaller)
                PDImageXObject logo = PDImageXObject.createFromFile("src/main/resources/static/university_logo.png", document);
                float logoWidth = logo.getWidth() / 20; // Adjust the divisor to change the size of the logo
                float logoHeight = logo.getHeight() / 20;
                contentStream.drawImage(logo, 50, 550 - logoHeight, logoWidth, logoHeight); // Adjust x and y coordinates to position the image properly

                contentStream.beginText();
                PDType0Font customFont = PDType0Font.load(document, new File("src/main/resources/static/RougeScript-Regular.ttf"));
                PDType0Font yourFont = PDType0Font.load(document, new FileInputStream("src/main/resources/static/LeckerliOne-Regular.ttf"));

                contentStream.setFont(customFont, 30); // Larger font size
                contentStream.setNonStrokingColor(Color.BLACK);
                contentStream.newLineAtOffset(400, 500);
                contentStream.showText( university.getName());


                // Set the font and other properties for "Adeverinta de Student" text
                contentStream.setFont(yourFont, 50); // Larger font size
                contentStream.setNonStrokingColor(Color.BLACK);

                // Calculate the width of the text
                float textWidth = yourFont.getStringWidth("Adeverinta de Student") / 1000f * 30; // 30 is the font size

                // Calculate the x-coordinate to center the text horizontally
                float startX = (PDRectangle.A4.getHeight() - textWidth) / 2; // Height and width are swapped for landscape

                float x1=-200;
                float y1 = -70;
                // Set the starting point and show the text
                contentStream.newLineAtOffset(x1,y1); // Higher position
                contentStream.showText("Adeverinta de Student");

                // Load the custom font

                // Set the font and other properties for the following text
                contentStream.setFont(customFont, 30); // Larger font size
                contentStream.setNonStrokingColor(Color.BLACK);

                String prefixText = "Acordata doamnei/lui: ";
                float prefixWidth = customFont.getStringWidth(prefixText) / 1000f * 20;
                float startX1 = (PDRectangle.A4.getHeight() - prefixWidth) / 2;

                float x2 = startX1 - startX +50;
                float y2 = -60;
                // Set the font and other properties for the following text
                contentStream.setFont(customFont, 30); // Larger font size
                contentStream.setNonStrokingColor(Color.BLACK);
                contentStream.newLineAtOffset(x2, y2);
                contentStream.showText("Acordata doamnei/lui: "); // Removed the name to show it separately

                // Calculate the width of the student's name
                float studentNameWidth = customFont.getStringWidth(student.getName()) / 1000f * 40;
                float startX2 = (PDRectangle.A4.getHeight() - studentNameWidth) / 2;

                float x3 = startX2 - startX1;
                // Show the student's name
                contentStream.setFont(customFont, 50);
                contentStream.newLineAtOffset(x3, y2); // Adjust the vertical spacing
                contentStream.showText(student.getName()); // Show the student's name


// Add "Pentru: " + title text with wrapping
                contentStream.setFont(customFont, 30); // Set the font size and type
                contentStream.setNonStrokingColor(Color.BLACK); // Set the text color

// Define the maximum width for the text
                float maxWidth = 500; // Adjust as needed for the maximum width available for the text

// Calculate the starting position for the text
                float startX3 = (PDRectangle.A4.getHeight() - maxWidth) / 2; // Height and width are swapped for landscape

// Define the starting Y position for the text
                float startY = -60; // Adjust as needed for the initial position of the text

// Call the method to justify and wrap the text
                justifyAndWrapText(contentStream, customFont, 30, "Pentru: " + title, -80, startY, maxWidth);

//                contentStream.setFont(customFont, 30);
//                contentStream.newLineAtOffset(0, -60);
//                contentStream.showText("Pentru: " + title);
//
                float x6 = -140;
                float y6 = -150;
                contentStream.newLineAtOffset(x6, y6);
                contentStream.showText("La data de: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));



                contentStream.endText();
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            document.close(); // Close the document after saving

            //ASTEA SA LE DECOMENTEZ DUPA CE MA ASIGUR CA MERGE BINE CA SA MI SE SALVEZE CERTIFICATELE IN BAZA DE DATE!!!!!!!

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
        return new byte[0]; // Return an empty byte array if an error occurs
    }



    // Method to justify and wrap text
    private void justifyAndWrapText(PDPageContentStream contentStream, PDType0Font font, int fontSize, String text, float startX, float startY, float maxWidth) throws IOException {
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        float lineWidth = 0;
        float lineSpacingFactor = fontSize * 0f; // Adjust line spacing factor as needed

        for (String word : words) {
            float wordWidth = font.getStringWidth(word) / 1000f * fontSize;

            if (lineWidth + wordWidth < maxWidth) {
                // Add the word to the line
                line.append(word).append(" ");
                lineWidth += wordWidth;
            } else {
                // Draw the justified line and start a new line
                contentStream.newLineAtOffset(startX, startY);
                contentStream.showText(line.toString().trim());
                startY -= lineSpacingFactor -10; // Adjust line spacing
                line = new StringBuilder(word + " ");
                lineWidth = wordWidth;
            }
        }

        // Draw the last line
        contentStream.newLineAtOffset(startX, startY);
        contentStream.showText(line.toString().trim());
    }





}
