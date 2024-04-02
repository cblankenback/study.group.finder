package com.project.studygroupfinder.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	 @GetMapping("/home")
	 @ResponseBody
	    public String test() {
	        return "Test Successful";
	    }
	

}
