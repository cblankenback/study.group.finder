package com.project.studygroupfinder.service;
import com.project.studygroupfinder.data.entity.StudyGroup;
import com.project.studygroupfinder.data.entity.Course;
import com.project.studygroupfinder.data.entity.Student;
import com.project.studygroupfinder.data.repository.StudyGroupRepository;

import jakarta.persistence.EntityNotFoundException;

import com.project.studygroupfinder.data.repository.CourseRepository;
import com.project.studygroupfinder.data.repository.StudentRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudyGroupService {

    private final StudyGroupRepository studyGroupRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public StudyGroupService(StudyGroupRepository studyGroupRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studyGroupRepository = studyGroupRepository;
        this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
    }

    @Transactional
    public void addParticipant(Integer sgId, String username) {
        StudyGroup studyGroup = studyGroupRepository.findById(sgId)
                .orElseThrow(() -> new IllegalArgumentException("Study Group not found with ID: " + sgId));
        Optional<Student> studentOptional = studentRepository.findByStudentEmail(username);
        Student student = studentOptional.orElseThrow(() -> new EntityNotFoundException("Student not found with email: " + username));

        studyGroup.getParticipants().add(student);
        studyGroupRepository.save(studyGroup);
    }
    
    @Transactional
    public void removeParticipant(Integer sgId, String username) {
        StudyGroup studyGroup = studyGroupRepository.findById(sgId)
                .orElseThrow(() -> new IllegalArgumentException("Study Group not found with ID: " + sgId));
        Optional<Student> studentOptional = studentRepository.findByStudentEmail(username);
        Student student = studentOptional.orElseThrow(() -> new EntityNotFoundException("Student not found with email: " + username));
                  
        studyGroup.getParticipants().remove(student);
        studyGroupRepository.save(studyGroup);
    }
    @Transactional
    public StudyGroup updateStudyGroup(Integer sgId, String name, String weekday, String time, String location, Integer courseId) {
        StudyGroup studyGroup = studyGroupRepository.findById(sgId)
                .orElseThrow(() -> new IllegalArgumentException("Study Group not found with ID: " + sgId));
        studyGroup.setSgName(name);
        studyGroup.setSgWeekday(weekday);
        studyGroup.setSgTime(time);
        studyGroup.setSgLocation(location);
        
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + courseId));
        studyGroup.setCourse(course);

        return studyGroupRepository.save(studyGroup);
    }
    @Transactional
    public void deleteStudyGroup(Integer sgId) {
        studyGroupRepository.deleteById(sgId);
    }

	public void save(StudyGroup studyGroup) {
		 studyGroupRepository.save(studyGroup);
		
	}

	
}
