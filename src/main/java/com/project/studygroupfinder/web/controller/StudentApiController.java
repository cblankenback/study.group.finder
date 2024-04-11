package com.project.studygroupfinder.web.controller;

import com.project.studygroupfinder.data.entity.Course;
import com.project.studygroupfinder.service.CourseService;
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
    @Autowired
    private CourseService courseService;

    @GetMapping("/students/{email}/courses")
    public ResponseEntity<Set<Course>> getCoursesByStudentEmail(@PathVariable String email) {
    	
    	
    	Course c = courseService.findCourseById(4);
    //  System.out.println(c.toString());
    //	System.out.println(c.getStudents());
        Set<Course> courses = studentService.getCoursesForStudent(email);
        
        return ResponseEntity.ok(courses);
    }
}
