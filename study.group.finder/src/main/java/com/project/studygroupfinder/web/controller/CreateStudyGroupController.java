package com.project.studygroupfinder.web.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.studygroupfinder.data.entity.Course;
import com.project.studygroupfinder.data.entity.Student;
import com.project.studygroupfinder.data.entity.StudyGroup;
import com.project.studygroupfinder.data.repository.StudyGroupRepository;
import com.project.studygroupfinder.service.CourseService;
import com.project.studygroupfinder.service.StudentService;
import com.project.studygroupfinder.service.StudyGroupService;

@Controller
public class CreateStudyGroupController {

	private final StudyGroupRepository studyGroupRepository;
	private final StudentService studentService;
	private final CourseService courseService;
	@Autowired
	private StudyGroupService studyGroupService;

	@Autowired
	public CreateStudyGroupController(StudyGroupRepository studyGroupRepository, StudentService studentService,
			CourseService courseService) { // Modify constructor
		this.studyGroupRepository = studyGroupRepository;
		this.studentService = studentService;
		this.courseService = courseService;
	}

	@GetMapping("/creategroup")
	public String showCreateStudyGroupForm(Model model, Authentication authentication, RestTemplate restTemplate) {
		String email = authentication.getName(); // Assuming the student's email is the username in the authentication
													// object
		Student student = studentService.findByStudentEmail(email);
		if (student == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found");
		}

		String url = "http://localhost:8080/api/students/" + email + "/courses"; // Adjust the URL as necessary
		ResponseEntity<Set<Course>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<Set<Course>>() {
				});
		Set<Course> studentCourses = response.getBody();

		model.addAttribute("studyGroup", new StudyGroup());
		model.addAttribute("allCourses", studentCourses); // Add student courses to the model
		return "createstudygroup";

	}

	@PostMapping("/createStudyGroup")
	public String createStudyGroup(@ModelAttribute StudyGroup studyGroup, @RequestParam("courseId") Integer courseId,
			Authentication authentication, RedirectAttributes redirectAttributes) {
		// Find the course by ID and set it to the studyGroup
		Course course = courseService.findCourseById(courseId);
		studyGroup.setCourse(course);

		// Find the current user and set as the owner of the studyGroup
		String username = authentication.getName();
		Student currentUser = studentService.findByStudentEmail(username);
		if (currentUser != null) {
			studyGroup.setOwner(currentUser);
		} else {
			// Handle the case where the current user cannot be found (though this should
			// ideally not happen if the user is authenticated)
			redirectAttributes.addFlashAttribute("errorMessage", "Could not find the current user to set as owner.");
			return "redirect:/creategroup";
		}

		// Save the studyGroup using your service or repository
		studyGroupService.save(studyGroup);
		redirectAttributes.addFlashAttribute("successMessage", "Study group created successfully!");
		return "redirect:/home";
	}
}