package com.project.studygroupfinder.dto;

import lombok.Data;

@Data  // Generates getters, setters, toString, equals, and hashCode methods
public class StudentDTO {
    private Integer studentId;
    private String studentEmail;
    private String studentFirstName;
    private String studentLastName;
}
