package com.project.studygroupfinder.data.repository;


import com.project.studygroupfinder.data.entity.Course;
import com.project.studygroupfinder.data.entity.Student;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	Optional<Student> findByStudentEmail(String email);
	
	 @Query("SELECT s.courses FROM Student s WHERE s.studentEmail = :email")
	Set<Course> findCoursesByEmail(String email);
}
