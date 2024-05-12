package com.project.studygroupfinder.data.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity

@Getter
@Table(name = "COURSE")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_ID", nullable = false)
    private Integer courseId;

    @Setter
    @Column(name = "COURSE_NAME", length = 50, nullable = false)
    private String courseName;

    @Setter
    
    @ManyToMany(mappedBy = "courses",fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Student> students;

//    // Manually added constructor for courseName
    public Course(String courseName) {
        this.courseName = courseName;
    }
    public Course() {
        
    }

    
}
