package web.app.project.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.app.project.project.entities.University;
import web.app.project.project.service.UniversityService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/universities")
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping("/create")
    public String showCreateUniversityForm(Model model) {
        model.addAttribute("university", new University());
        return "university-create";
    }

    @PostMapping("/create")
    public String createUniversity(@ModelAttribute University university) {
        universityService.saveUniversity(university);
        return "redirect:/universities";
    }

    @GetMapping("/details/{id}")
    public String showUniversityDetails(@PathVariable Long id, Model model) {
        University university = universityService.findUniversityById(id);
        if (university != null) {
            model.addAttribute("university", university);
            return "university-details";
        } else {
            return "not-found";
        }
    }

    @GetMapping("/all")
    public String getAllUniversities(Model model) {
        List<University> universities = universityService.getAllUniversities();
        model.addAttribute("universities", universities);
        return "university-list"; // Assuming you have a Thymeleaf template named university-list.html
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUniversity(@PathVariable Long id) {
        boolean deleted = universityService.deleteUniversityById(id);
        if (deleted) {
            return "redirect:/universities/all"; // Redirect to the list of universities
        } else {
            return "not-found"; // Or any other appropriate error page
        }
    }
    @GetMapping("/list")
    public String showAllUniversities(Model model) {
        List<University> universities = universityService.getAllUniversities();
        model.addAttribute("universities", universities);
        return "universities"; // The name of the Thymeleaf template (universities.html)
    }
}
