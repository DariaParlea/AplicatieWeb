package web.app.project.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.app.project.project.entities.Student;
import web.app.project.project.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public String createStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/students/list";
    }

    @GetMapping("/get/{id}")
    public String getStudentById(@PathVariable Long id, Model model) {
        Student student = studentService.findStudentById(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "student-details"; // Render student-details.html template
        } else {
            return "not-found"; // Render not-found.html template
        }
    }

    @GetMapping("/search")
    public String getStudentByName(@RequestParam String name, Model model) {
        Student student = studentService.findStudentByName(name);
        if (student != null) {
            model.addAttribute("student", student);
            return "student-details"; // Render student-details.html template
        } else {
            return "not-found"; // Render not-found.html template
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        boolean deleted = studentService.deleteStudentById(id);
        if (deleted) {
            return "redirect:/students/list";
        } else {
            return "not-found"; // Render not-found.html template
        }
    }

    @GetMapping("/list")
    public String getAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students"; // Render students.html template
    }
}
