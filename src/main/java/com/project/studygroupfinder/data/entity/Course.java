package com.project.studygroupfinder.data.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.*;
import java.util.Set;
@Data
@Entity

@ToString
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
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;

   
}
