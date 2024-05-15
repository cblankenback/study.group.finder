package com.project.studygroupfinder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.studygroupfinder.data.entity.Course;
import com.project.studygroupfinder.data.entity.Student;
import com.project.studygroupfinder.data.repository.CourseRepository;
import com.project.studygroupfinder.data.repository.StudentRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

//@Service
//public class StudentCourseService {
//
////    @Autowired
////    private StudentRepository studentRepository;
////
////    @Autowired
////    private CourseRepository courseRepository;
//
////    @Transactional
////    public boolean addCoursesToStudent(String email, Set<String> courseNames) {
////        // Find the student by email
////        Optional<Student> studentOpt = studentRepository.findByStudentEmail(email);
////        if (!studentOpt.isPresent()) {
////            return false; // or throw an exception if appropriate
////        }
////        Student student = studentOpt.get();
////
////        // Ensure the student's course set is initialized
////        if (student.getCourses() == null) {
////            student.setCourses(new HashSet<>());
////        }
////
////        // For each course name, find or create the course and add it to the student's set
////        courseNames.forEach(courseName -> {
////            Course course = courseRepository.findByCourseName(courseName)
////                            .orElseGet(() -> {
////                                // Create and save a new course if it doesn't exist
////                                Course newCourse = new Course(courseName);
////                                courseRepository.save(newCourse);
////                                return newCourse;
////                            });
////            // Add the course to the student's set of courses
////            student.getCourses().add(course);
////        });
////
////        // Save the updated student
////        studentRepository.save(student);
////        return true;
////    }
//}
