package com.project.studygroupfinder.data.repository;

import com.project.studygroupfinder.data.entity.Course;
import com.project.studygroupfinder.data.entity.StudyGroup;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	List<Course> findAll();

    Optional<Course> findByCourseId(int id);

    // Add this method to find a course by name
    Optional<Course> findByCourseName(String courseName);
}