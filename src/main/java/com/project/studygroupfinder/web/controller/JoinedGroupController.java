package com.project.studygroupfinder.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.studygroupfinder.data.entity.Student;
import com.project.studygroupfinder.data.entity.StudyGroup;
import com.project.studygroupfinder.data.repository.StudyGroupRepository;
import com.project.studygroupfinder.service.StudentService;

@Controller
public class JoinedGroupController {

	private final StudyGroupRepository studyGroupRepository;

	@Autowired
	public JoinedGroupController(StudyGroupRepository studyGroupRepository) {
		this.studyGroupRepository = studyGroupRepository;
	}

	@Autowired
	private StudentService studentService; // Service to fetch student/user details

	@GetMapping("/joinedgroups")
	public String viewFeed(Model model, Authentication authentication) {
	    String username = authentication.getName();
	    Student currentUser = studentService.findByStudentEmail(username); // Assuming this method exists and returns a Student object
	    List<StudyGroup> studyGroups = studyGroupRepository.findByParticipants_StudentId(currentUser.getStudentId()); // Use the updated method
	    model.addAttribute("studyGroups", studyGroups);
	    return "joinedgroups";
	}
	
}
