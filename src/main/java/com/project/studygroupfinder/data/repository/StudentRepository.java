package com.project.studygroupfinder.data.repository;


import com.project.studygroupfinder.data.entity.Student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	Optional<Student> findByStudentEmail(String email);
}
