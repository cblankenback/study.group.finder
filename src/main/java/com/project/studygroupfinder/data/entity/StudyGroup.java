package com.project.studygroupfinder.data.entity;



import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter // Generates getters for all fields
@Entity
@Table(name = "STUDYGROUP")
public class StudyGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SG_ID")
    private Integer sgId; 

    @Setter
    @Column(name = "SG_NAME", length = 255, nullable = false)
    private String sgName;

    @Setter
    @Column(name = "SG_WEEKDAY", length = 50)
    private String sgWeekday;

    @Setter
    @Column(name = "SG_TIME", length = 50)
    private String sgTime;

    @Setter
    @Column(name = "SG_LOCATION", length = 255)
    private String sgLocation;

    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OWNER_ID", referencedColumnName = "STUDENT_ID")
    private Student owner;

    @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
      name = "STUDENTSTUDYGROUP",
      joinColumns = @JoinColumn(name = "SG_ID"),
      inverseJoinColumns = @JoinColumn(name = "STUDENT_ID")
    )
    private Set<Student> participants = new HashSet<>();
    
    
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COURSE_ID")
    private Course course;
    
    
}
