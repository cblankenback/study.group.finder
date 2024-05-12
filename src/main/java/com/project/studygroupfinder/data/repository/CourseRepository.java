package com.project.studygroupfinder.data.repository;

import com.project.studygroupfinder.data.entity.Course;
import com.project.studygroupfinder.data.entity.StudyGroup;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	List<Course> findAll();
	Course findByCourseId(Integer id);
}