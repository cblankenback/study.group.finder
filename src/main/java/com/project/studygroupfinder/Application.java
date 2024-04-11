package com.project.studygroupfinder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import com.project.studygroupfinder.data.entity.Course;
import com.project.studygroupfinder.data.entity.Student;
import com.project.studygroupfinder.data.repository.CourseRepository;
import com.project.studygroupfinder.data.repository.StudentRepository;
import com.project.studygroupfinder.service.StudentCourseService;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EntityScan(basePackages = {"com.project.studygroupfinder.data.entity"})
@ComponentScan(basePackages = {"com.project.studygroupfinder.config", "com.project.studygroupfinder"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	// Autowire StudentCourseService
    @Bean
    CommandLineRunner initDatabase(StudentRepository studentRepository,
                                   CourseRepository courseRepository,
                                   StudentCourseService studentCourseService) {
        return args -> {
            // Your database initialization code

            // Example of adding courses to a student
            Set<String> courseNames = new HashSet<>();
            courseNames.add("Mathematics");
            courseNames.add("Physics");
            courseNames.add("Chemistry");
            courseNames.add("Biology");

           // studentCourseService.addCoursesToStudent("tester@test.ca", courseNames);
        };
    }

}
