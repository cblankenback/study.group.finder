package com.project.studygroupfinder.service;

import com.project.studygroupfinder.data.entity.Course;
import com.project.studygroupfinder.data.entity.Student;
import com.project.studygroupfinder.data.repository.StudentRepository;
import com.project.studygroupfinder.dto.CourseDTO;
import com.project.studygroupfinder.dto.StudentDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.studygroupfinder.data.entity.Course;
import com.project.studygroupfinder.dto.CourseDTO;
import com.project.studygroupfinder.dto.StudentDTO;
import org.springframework.beans.BeanUtils;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class StudentService  implements UserDetailsService {
	@Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByStudentEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        return new org.springframework.security.core.userdetails.User(student.getStudentEmail(), student.getStudentPassword(), new ArrayList<>());
    }


    public Student registerNewStudentAccount(Student student) {
        student.setStudentPassword(passwordEncoder.encode(student.getStudentPassword()));
        return studentRepository.save(student);
    }

    public Integer findStudentIdByEmail(String email) {
        Optional<Student> studentOpt = studentRepository.findByStudentEmail(email);
        return studentOpt.map(Student::getStudentId)
                         .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }


    public Student findByStudentEmail(String currentUsername) {
    //	System.out.println(currentUsername);
        return studentRepository.findByStudentEmail(currentUsername)
                                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + currentUsername));
    }
    

    
	// Inside StudentService class
//    @Transactional
//    public Set<Course> getCoursesForStudent(String email) {
//        Student student = studentRepository.findByStudentEmail(email)
//                                            .orElseThrow(() -> new UsernameNotFoundException("No student found with email: " + email));
//     
////       System.out.println("Student: " + student);
////       System.out.println("Courses loaded: " + student.getCourses().size());
//    //   student.getCourses().forEach(course -> System.out.println(course));
//        return student.getCourses();
//    }
   
  
    public Set<CourseDTO> getCoursesForStudent(String email) {
        try {
            Set<Course> courses = studentRepository.findCoursesByEmail(email);
            return courses.stream().map(this::convertToCourseDTO).collect(Collectors.toSet());
        } catch (Exception e) {
            throw new UsernameNotFoundException("No courses found for the student with email: " + email, e);
        }
    }

    private CourseDTO convertToCourseDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        BeanUtils.copyProperties(course, dto);
        dto.setStudents(course.getStudents().stream().map(this::convertToStudentDTO).collect(Collectors.toSet()));
        return dto;
    }

//    private StudentDTO convertToStudentDTO(Student student) {
//        StudentDTO dto = new StudentDTO();
//        BeanUtils.copyProperties(student, dto);
//        return dto;
//    }
    private StudentDTO convertToStudentDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setStudentId(student.getStudentId());
        dto.setStudentEmail(student.getStudentEmail());
        dto.setStudentFirstName(student.getStudentFirstName());
        dto.setStudentLastName(student.getStudentLastName());
        return dto;
    }
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                                .map(this::convertToStudentDTO)
                                .collect(Collectors.toList());
    }


   
}
