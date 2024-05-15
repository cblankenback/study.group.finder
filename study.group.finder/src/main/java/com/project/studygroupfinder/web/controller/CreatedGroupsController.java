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
public class CreatedGroupsController {
	private final StudyGroupRepository studyGroupRepository;

	@Autowired
	public CreatedGroupsController(StudyGroupRepository studyGroupRepository) {
		this.studyGroupRepository = studyGroupRepository;
	}

	@Autowired
	private StudentService studentService; // Service to fetch student/user details

	@GetMapping("/createdgroups")
	public String viewFeed(Model model, Authentication authentication) {
	    String username = authentication.getName();
	    Student currentUser = studentService.findByStudentEmail(username);
	    List<StudyGroup> studyGroups = studyGroupRepository.findByOwner_StudentId(currentUser.getStudentId()); 
	    model.addAttribute("studyGroups", studyGroups);
	    return "createdgroups";
	}
}
