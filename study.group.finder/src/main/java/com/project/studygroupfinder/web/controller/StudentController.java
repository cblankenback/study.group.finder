package com.project.studygroupfinder.web.controller;

import com.project.studygroupfinder.data.entity.Student;
import com.project.studygroupfinder.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;
    
    @PostMapping("/register")
    public String registerStudentAccount(@ModelAttribute("student") Student student,BindingResult result, Model model) {
        try {
            studentService.registerNewStudentAccount(student);
            return "redirect:/login"; // Redirect to login on successful registration
        } catch (Exception e) {
            result.rejectValue("studentEmail", "error.student", e.getMessage()); // Specific field rejection
            model.addAttribute("errorMessage", e.getMessage()); // Generic error message for the form
            return "register"; // Stay on the registration page
        }
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
       
        model.addAttribute("student", new Student());
        return "register"; 
    }
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid email or password.");
        }
        return "login";
    }
    
}
