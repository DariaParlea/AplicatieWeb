package web.app.project.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.app.project.project.entities.Certificate;
import web.app.project.project.entities.Student;
import web.app.project.project.entities.University;
import web.app.project.project.service.CertificateService;
import web.app.project.project.service.StudentService;
import web.app.project.project.service.UniversityService;

@Controller
@RequestMapping("/certificates")
public class CertificateController {

    private final CertificateService certificateService;
    private final StudentService studentService;
    private final UniversityService universityService;

    public CertificateController(CertificateService certificateService, StudentService studentService, UniversityService universityService) {
        this.certificateService = certificateService;
        this.studentService = studentService;
        this.universityService = universityService;
    }

    @GetMapping("/generate")
    public String showGenerateCertificateForm(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("universities", universityService.getAllUniversities());
        return "generate-certificate";
    }

    @PostMapping("/generate")
    public String generateCertificate(@RequestParam Long studentId,
                                      @RequestParam Long universityId,
                                      @RequestParam String title) {
        Student student = studentService.findStudentById(studentId);
        University university = universityService.findUniversityById(universityId);
        if (student != null && university != null) {
            certificateService.generateCertificate(student, university, title);
        }
        return "redirect:/certificates/generate";
    }

}
