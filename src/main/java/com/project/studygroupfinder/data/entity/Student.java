package com.project.studygroupfinder.data.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;

import java.util.Set;



@Entity
@Table(name = "STUDENT")
@Data
@ToString
@Getter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID", nullable = false)
    private Integer studentId;
    @Setter
    @Column(name = "STUDENT_EMAIL", length = 50, nullable = false)
    private String studentEmail;
    @Setter
    @Column(name = "STUDENT_PASSWORD", length = 1000, nullable = false)
    private String studentPassword;
    @Setter
    @Column(name = "STUDENT_FIRST_NAME", length = 50, nullable = false)
    private String studentFirstName;

    @Setter
    @Column(name = "STUDENT_LAST_NAME", length = 45, nullable = false)
    private String studentLastName;
    
    @ManyToMany
    @JoinTable(
        name = "STUDENTCOURSES",
        joinColumns = @JoinColumn(name = "STUDENT_ID"),
        inverseJoinColumns = @JoinColumn(name = "COURSE_ID")
    )
    private Set<Course> courses;
    
    @ManyToMany(mappedBy = "participants")
    private Set<StudyGroup> studyGroups;
    


    
}
