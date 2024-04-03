package com.project.studygroupfinder.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.studygroupfinder.data.entity.Course;
import com.project.studygroupfinder.data.entity.Student;
import com.project.studygroupfinder.data.repository.CourseRepository;
import com.project.studygroupfinder.data.repository.StudentRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;

	public List<Course> findAllCourses() {
		return courseRepository.findAll();
	}

	public Course findCourseById(Integer courseId) {

		Course course = courseRepository.findByCourseId(courseId);
		return course;
	}
	
}
