package com.project.studygroupfinder.data.repository;

import com.project.studygroupfinder.data.entity.StudyGroup;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyGroupRepository extends JpaRepository<StudyGroup, Integer> {

	List<StudyGroup> findBySgName(String string);
	
	List<StudyGroup> findAll();
	StudyGroup findBySgId(Integer id);
	
	List<StudyGroup> findByParticipants_StudentId(Integer integer);
	
	List<StudyGroup> findByOwner_StudentId(Integer integer);

}
