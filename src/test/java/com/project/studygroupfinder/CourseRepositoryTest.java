package com.project.studygroupfinder;

import com.project.studygroupfinder.data.entity.Course;
import com.project.studygroupfinder.data.repository.CourseRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CourseRepositoryTest {

	@Autowired
	private CourseRepository courseRepository;

	@Test
	public void testSaveAndFindCourse() {
		// Create a new Course instance
		Course course = new Course();
		course.setCourseName("Introduction to Spring Boot");
		// courseRepository.flush(); d
//        // Save the course to the database
		Course savedCourse = courseRepository.save(course);
//
		// Assert that the course was saved successfully
		assertThat(savedCourse.getCourseId()).isNotNull();
		assertThat(savedCourse.getCourseName()).isEqualTo("Introduction to Spring Boot");

		// Optionally, you can test finding the course by ID
		Course foundCourse = courseRepository.findById(savedCourse.getCourseId()).orElse(null);
		assertThat(foundCourse).isNotNull();
		assertThat(foundCourse.getCourseName()).isEqualTo("Introduction to Spring Boot");
	}
}