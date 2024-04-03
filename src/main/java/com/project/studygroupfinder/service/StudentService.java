package com.project.studygroupfinder.service;

import com.project.studygroupfinder.data.entity.Student;
import com.project.studygroupfinder.data.repository.StudentRepository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService  implements UserDetailsService {
	@Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByStudentEmail(username);
        System.out.println("Test: "+ student);
        return new org.springframework.security.core.userdetails.User(student.getStudentEmail(), student.getStudentPassword(), new ArrayList<>());
    }

    public Student registerNewStudentAccount(Student student) {
        student.setStudentPassword(passwordEncoder.encode(student.getStudentPassword()));
        return studentRepository.save(student);
    }

    public Integer findStudentIdByEmail(String email) {
        Student student = studentRepository.findByStudentEmail(email);
        if (student != null) {
            return student.getStudentId(); // Assuming getId() returns Long.
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

	public Student findByStudentEmail(String currentUsername) {
		 Student student = studentRepository.findByStudentEmail(currentUsername);
		return student;
	}
    

   
}
