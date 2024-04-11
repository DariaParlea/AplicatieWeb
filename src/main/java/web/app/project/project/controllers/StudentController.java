package web.app.project.project.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.app.project.project.entities.Student;
import web.app.project.project.entities.University;
import web.app.project.project.service.StudentService;
import web.app.project.project.service.UniversityService;
import java.util.List;
@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final UniversityService universityService;

    @Autowired
    public StudentController(StudentService studentService, UniversityService universityService) {
        this.studentService = studentService;
        this.universityService = universityService;
    }

    @GetMapping("/main")
    public String showMainPage() {
        return "students-main";
    }

    @GetMapping("/create")
    public String showCreateStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "students-create";
    }


    @PostMapping("/create")
    public String createStudent(@ModelAttribute("student") Student student, @RequestParam("faculty") Long universityId) {
        University university = universityService.findUniversityById(universityId);
        student.setUniversity(university);
        studentService.saveStudent(student);
        return "redirect:/students/list";
    }



    @GetMapping("/list")
    public String getAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students-list";
    }

    @GetMapping("/get")
    public String getStudentById(@RequestParam Long id, Model model) {
        Student student = studentService.findStudentById(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "student-details";
        } else {
            return "not-found";
        }
    }

    @GetMapping("/get/{id}")
    public String getStudentById1(@PathVariable Long id, Model model) {
        Student student = studentService.findStudentById(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "student-details";
        } else {
            return "not-found";
        }
    }


    @GetMapping("/search")
    public String getStudentByName(@RequestParam String name, Model model) {
        Student student = studentService.findStudentByName(name);
        if (student != null) {
            model.addAttribute("student", student);
            return "student-details";
        } else {
            return "not-found";
        }
    }

    @GetMapping("/search/{name}")
    public String getStudentByName1(@PathVariable String name, Model model) {
        Student student = studentService.findStudentByName(name);
        if (student != null) {
            model.addAttribute("student", student);
            return "student-details";
        } else {
            return "not-found";
        }
    }

    @GetMapping("/delete")
    public String deleteStudent1(@RequestParam Long id) {
        boolean deleted = studentService.deleteStudentById(id);
        if (deleted) {
            return "redirect:/students/list";
        } else {
            return "not-found";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        boolean deleted = studentService.deleteStudentById(id);
        if (deleted) {
            return "redirect:/students/list";
        } else {
            return "not-found";
        }
    }
}