package com.project.studygroupfinder.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiDocController {
    @GetMapping("/api/docs")
    public String index() {
        return "apidoc";
    }
}
