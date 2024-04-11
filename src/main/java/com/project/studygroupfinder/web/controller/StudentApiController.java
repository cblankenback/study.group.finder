package com.project.studygroupfinder.web.controller;

import com.project.studygroupfinder.data.entity.Course;
import com.project.studygroupfinder.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class StudentApiController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students/{email}/courses")
    public ResponseEntity<Set<Course>> getCoursesByStudentEmail(@PathVariable String email) {
        Set<Course> courses = studentService.getCoursesForStudent(email);
        return ResponseEntity.ok(courses);
    }
}
