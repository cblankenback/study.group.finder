package com.project.studygroupfinder.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.project.studygroupfinder.data.entity.StudyGroup;
import com.project.studygroupfinder.data.repository.StudyGroupRepository;

@Controller
public class StudyGroupDetailsController {
	

	private final StudyGroupRepository studyGroupRepository;

	@Autowired
	public StudyGroupDetailsController(StudyGroupRepository studyGroupRepository) {
		this.studyGroupRepository = studyGroupRepository;
	}
	
	
	@GetMapping("/studygroup/{sgId}")
	public String viewStudyGroup(@PathVariable Integer sgId, Model model) {
	    StudyGroup studyGroup = studyGroupRepository.findById(sgId)
	    		 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Study Group not found"));
	    model.addAttribute("studyGroup", studyGroup); // Make sure studyGroup is not wrapped in Optional here
	    return "studygroupdetails";
	}
}
