package com.project.studygroupfinder.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.studygroupfinder.data.entity.StudyGroup;
import com.project.studygroupfinder.data.repository.StudyGroupRepository;

@Controller
public class HomeController {

	private final StudyGroupRepository studyGroupRepository;

	@Autowired
	public HomeController(StudyGroupRepository studyGroupRepository) {
		this.studyGroupRepository = studyGroupRepository;
	}

	@GetMapping("/home")
	public String viewFeed(Model model) {
		List<StudyGroup> studyGroups = studyGroupRepository.findAll(); // Assuming you have a repository
		model.addAttribute("studyGroups", studyGroups);
		return "home";
	}
	


}
