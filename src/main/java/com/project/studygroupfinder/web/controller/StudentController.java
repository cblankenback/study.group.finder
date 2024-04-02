package com.project.studygroupfinder.web.controller;

import com.project.studygroupfinder.data.entity.Student;
import com.project.studygroupfinder.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;
    
    @PostMapping("/register")
    public String registerStudentAccount(@ModelAttribute("student") Student student) {
        System.out.println("Registering student with email: " + student.getStudentEmail() + " and password: " + student.getStudentPassword()); // Debugging log
        studentService.registerNewStudentAccount(student);
        return "redirect:/login";
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Assuming 'Student' is the name of your model attribute for the form
        model.addAttribute("student", new Student());
        return "register"; // name of your registration Thymeleaf template
    }
    @GetMapping("/login")
    public String login() {
        return "login"; // refers to 'login.html' in 'src/main/resources/templates'
    }
}
