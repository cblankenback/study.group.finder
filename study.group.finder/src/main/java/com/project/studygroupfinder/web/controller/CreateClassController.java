package com.project.studygroupfinder.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.studygroupfinder.data.entity.Student;
import com.project.studygroupfinder.data.repository.StudyGroupRepository;
import com.project.studygroupfinder.service.CourseService;
import com.project.studygroupfinder.service.StudentService;
import com.project.studygroupfinder.data.entity.Course;

@Controller
public class CreateClassController {

	private final StudentService studentService;
	private final CourseService courseService;

	@Autowired
	public CreateClassController(StudentService studentService, CourseService courseService) { // Modify constructor
		this.courseService = courseService;
		this.studentService = studentService;

	}

	@GetMapping("/createclass")
	public String showRegistrationForm(Model model) {

		model.addAttribute("course", new Course());
		return "createclass";
	}

	@PostMapping("/createclass")
	public String createClass(Model model, Authentication authentication, @ModelAttribute("course") Course course) {
		String email = authentication.getName(); 
		Student student = studentService.findByStudentEmail(email);
		
		
		studentService.addStudentToCourse(course, student);
		
		return "redirect:/home";
		
	}

}
