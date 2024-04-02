package com.project.studygroupfinder;

import com.project.studygroupfinder.data.entity.StudyGroup;
import com.project.studygroupfinder.data.repository.StudyGroupRepository;
import com.project.studygroupfinder.data.entity.Course;
import com.project.studygroupfinder.data.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudyGroupRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private StudyGroupRepository studyGroupRepository;

	@Test
	public void testSaveStudyGroup() {
		
		StudyGroup studyGroup = new StudyGroup();
		studyGroup.setSgName("Study Group 1");
		studyGroup.setSgWeekday("Monday");
		studyGroup.setSgTime("10:00");
		studyGroup.setSgLocation("Library");
		Course course = new Course();
		course.setCourseName("Example Course");
		entityManager.persist(course);
		studyGroup.setCourse(course);
		
		Student owner = new Student();
		owner.setStudentEmail("test2@example.com");
		owner.setStudentFirstName("Second");
		owner.setStudentLastName("Student");
		owner.setStudentPassword("password2");
		entityManager.persist(owner);
		studyGroup.setOwner(owner);
		

		StudyGroup savedStudyGroup = studyGroupRepository.save(studyGroup);
		
		

		assertThat(savedStudyGroup).isNotNull();
		assertThat(savedStudyGroup.getSgId()).isNotNull();
	}

	@Test
	public void testFindStudyGroupByName() {
		

		StudyGroup studyGroup = new StudyGroup();
		studyGroup.setSgName("Study Group 2");
		studyGroup.setSgWeekday("Tuesday");
		studyGroup.setSgTime("14:00");
		studyGroup.setSgLocation("Online");
		Course course = new Course();
		course.setCourseName("Example Course");
		entityManager.persist(course);
		studyGroup.setCourse(course);
		
		Student owner = new Student();		
		owner.setStudentEmail("test2@example.com");
		owner.setStudentFirstName("Second");
		owner.setStudentLastName("Student");
		owner.setStudentPassword("password2");
		entityManager.persist(owner);
		studyGroup.setOwner(owner);
		
		entityManager.persist(studyGroup);
		entityManager.flush();

		List<StudyGroup> foundStudyGroups = studyGroupRepository.findBySgName("Study Group 2");

		assertThat(foundStudyGroups).isNotEmpty();
		assertThat(foundStudyGroups.get(0).getSgName()).isEqualTo(studyGroup.getSgName());
	}

//	@Test
//	public void testAddStudentToStudyGroup() {
//		Student student = new Student();
//		student.setStudentEmail("student@example.com");
//		student.setStudentFirstName("John");
//		student.setStudentLastName("Doe");
//		student.setStudentPassword("password");
//		entityManager.persist(student);
//
//		StudyGroup studyGroup = new StudyGroup();
//		studyGroup.setSgName("Study Group 3");
//		studyGroup.setSgWeekday("Wednesday");
//		studyGroup.setSgTime("16:00");
//		studyGroup.setSgLocation("Cafeteria");
//		Course course = new Course();
//		course.setCourseName("Example Course");
//		entityManager.persist(course);
//		studyGroup.setCourse(course);
//
//		Set<Student> participants = new HashSet<>();
//		participants.add(student);
//		studyGroup.setParticipants(participants);
//
//		StudyGroup savedStudyGroup = studyGroupRepository.save(studyGroup);
//
//		assertThat(savedStudyGroup.getParticipants()).contains(student);
//	}

}
