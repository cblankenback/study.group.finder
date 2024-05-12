package com.project.studygroupfinder;

//import com.project.studygroupfinder.config.SecurityConfig;
import com.project.studygroupfinder.data.entity.Student;
import com.project.studygroupfinder.data.repository.StudentRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@Import(SecurityConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private StudentRepository studentRepository;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

//	@Test
//	public void testSaveStudent() {
//		Student student = new Student();
//		student.setStudentEmail("test@example.com");
//		student.setStudentFirstName("John");
//		student.setStudentLastName("Doe");
//		student.setStudentPassword(passwordEncoder.encode("securepassword"));
//		Student savedStudent = studentRepository.save(student);
//
//		assertThat(savedStudent).isNotNull();
//        assertThat(savedStudent.getStudentId()).isNotNull();
//        // Verify that the password is encoded
//        assertThat(passwordEncoder.matches("securepassword", savedStudent.getStudentPassword())).isTrue();
//	}

	@Test
	public void testFindStudentByEmail() {
	    Student student = new Student();
	    student.setStudentEmail("testfind@example.com");
	    student.setStudentFirstName("Jane");
	    student.setStudentLastName("Doe");
	    student.setStudentPassword("anothersecurepassword");
	    entityManager.persist(student);
	    entityManager.flush();

	    // Use .findByStudentEmail() and handle the Optional result
	    Optional<Student> foundStudentOpt = studentRepository.findByStudentEmail("testfind@example.com");

	    assertThat(foundStudentOpt.isPresent()).isTrue(); // Verify that a result is present
	    foundStudentOpt.ifPresent(foundStudent -> {
	        // Perform assertions inside ifPresent to safely access the Student
	        assertThat(foundStudent.getStudentEmail()).isEqualTo(student.getStudentEmail());
	    });
	}

	@Test
    @Transactional
    public void testFindAllStudents() {
        Student student1 = new Student();
        student1.setStudentEmail("test1@example.com");
        student1.setStudentFirstName("First");
        student1.setStudentLastName("Student");
        student1.setStudentPassword("password1");

        Student student2 = new Student();
        student2.setStudentEmail("test2@example.com");
        student2.setStudentFirstName("Second");
        student2.setStudentLastName("Student");
        student2.setStudentPassword("password2");

        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.flush();

        List<Student> students = studentRepository.findAll();

        System.out.println("Retrieved students: " + students); // Debugging output
        assertThat(students).hasSizeGreaterThanOrEqualTo(2);
        assertThat(students).contains(student1, student2);
    }

}
