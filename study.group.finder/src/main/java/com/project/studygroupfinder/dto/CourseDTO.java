package com.project.studygroupfinder.dto;



import lombok.Data;
import java.util.Set;

@Data  // Generates getters, setters, toString, equals, and hashCode methods
public class CourseDTO {
    private Integer courseId;
    private String courseName;
    private Set<StudentDTO> students;
}

