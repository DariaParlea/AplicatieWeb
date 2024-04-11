package web.app.project.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.app.project.project.entities.University;
import web.app.project.project.service.UniversityService;
import java.util.List;

@Controller
@RequestMapping("/universities")
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping("/main")
    public String showMainPage() {
        return "universities-main";
    }

    @GetMapping("/create")
    public String showCreateUniversityForm(Model model) {
        model.addAttribute("university", new University());
        return "university-create";
    }

    @PostMapping("/create")
    public String createUniversity(@ModelAttribute University university) {
        universityService.saveUniversity(university);
        return "redirect:/universities/all";
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
        return "university-list";
    }

    @GetMapping("/get/{id}")
    public String getUniversityById(@PathVariable Long id, Model model) {
        University university = universityService.findUniversityById(id);
        if (university != null) {
            model.addAttribute("university", university);
            return "university-details";
        } else {
            return "not-found";
        }
    }

    @GetMapping("/get")
    public String getUniversityById1(@RequestParam Long id, Model model) {
        University university = universityService.findUniversityById(id);
        if (university != null) {
            model.addAttribute("university", university);
            return "university-details";
        } else {
            return "not-found";
        }
    }

    @GetMapping("/search")
    public String findUniversityByName(@RequestParam String name, Model model) {
        University university = universityService.findUniversityByName(name);
        if (university != null) {
            model.addAttribute("university", university);
            return "university-details";
        } else {
            return "not-found";
        }
    }

    @GetMapping("/search/{name}")
    public String findUniversityByName1(@PathVariable String name, Model model) {
        University university = universityService.findUniversityByName(name);
        if (university != null) {
            model.addAttribute("university", university);
            return "university-details";
        } else {
            return "not-found";
        }
    }


    @GetMapping("/delete/{id}")
    public String deleteUniversity(@PathVariable Long id) {
        boolean deleted = universityService.deleteUniversityById(id);
        if (deleted) {
            return "redirect:/universities/all";
        } else {
            return "not-found";
        }
    }

    @GetMapping("/delete")
    public String deleteUniversity1(@RequestParam Long id) {
        boolean deleted = universityService.deleteUniversityById(id);
        if (deleted) {
            return "redirect:/universities/all";
        } else {
            return "not-found";
        }
    }
}
