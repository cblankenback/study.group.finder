package com.project.studygroupfinder.web.controller;

import com.project.studygroupfinder.data.entity.Course;
import com.project.studygroupfinder.dto.CourseDTO;
import com.project.studygroupfinder.dto.StudentDTO;
import com.project.studygroupfinder.service.CourseService;
import com.project.studygroupfinder.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class StudentApiController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    @GetMapping("/students/{email}/courses")
    public ResponseEntity<Set<CourseDTO>> getCoursesByStudentEmail(@PathVariable String email) {
        Set<CourseDTO> courses = studentService.getCoursesForStudent(email);
        return ResponseEntity.ok(courses);
    }
    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
}
