package com.project.studygroupfinder.data.repository;

import com.project.studygroupfinder.data.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    // Custom query methods can be defined here
}