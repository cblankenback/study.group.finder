package com.project.studygroupfinder.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.studygroupfinder.data.entity.Student;
import com.project.studygroupfinder.data.entity.StudyGroup;
import com.project.studygroupfinder.data.repository.StudyGroupRepository;
import com.project.studygroupfinder.service.CourseService;
import com.project.studygroupfinder.service.StudentService;
import com.project.studygroupfinder.service.StudyGroupService;

@Controller
public class StudyGroupDetailsController {
    
	private final StudyGroupRepository studyGroupRepository;
    private final StudentService studentService;
    private final CourseService courseService; 
    @Autowired
    private StudyGroupService studyGroupService;

    @Autowired
    public StudyGroupDetailsController(StudyGroupRepository studyGroupRepository, StudentService studentService, CourseService courseService) { // Modify constructor
        this.studyGroupRepository = studyGroupRepository;
        this.studentService = studentService;
        this.courseService = courseService; 
    }
    
    @GetMapping("/studygroup/{sgId}")
    public String viewStudyGroup(@PathVariable Integer sgId, Model model, Authentication authentication) {
        StudyGroup studyGroup = studyGroupRepository.findById(sgId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Study Group not found"));
        String currentUsername = authentication.getName();

        // Use studentService to find the current user by email
        Student currentUser = studentService.findByStudentEmail(currentUsername);
        Integer currentUserId = currentUser != null ? currentUser.getStudentId() : null;

        // Check if the current user is the owner
        boolean isOwner = studyGroup.getOwner().getStudentId().equals(currentUserId);

        // Check if the current user is already a participant
        boolean isParticipant = studyGroup.getParticipants().stream()
            .anyMatch(participant -> participant.getStudentId().equals(currentUserId));

        // User should see the "Join" button if they are neither the owner nor already a participant
        boolean showJoinButton = !isOwner && !isParticipant;
        
        boolean showLeaveButton = !isOwner && isParticipant;
        
        model.addAttribute("showJoinButton", showJoinButton);
        model.addAttribute("studyGroup", studyGroup);
        model.addAttribute("isOwner", isOwner);
        model.addAttribute("allCourses", courseService.findAllCourses());
        model.addAttribute("showLeaveButton", showLeaveButton);
        return "studygroupdetails";
    }
    @PostMapping("/joinGroup")
    public String joinGroup(@RequestParam("sgId") Integer sgId, Authentication authentication, RedirectAttributes redirectAttributes) {
        String username = authentication.getName(); // Get the current user's username
       
        try {
            studyGroupService.addParticipant(sgId, username); 
            redirectAttributes.addFlashAttribute("successMessage", "Successfully joined the study group!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Could not join the study group.");
        }
        return "redirect:/studygroup/" + sgId; // Redirect back to the study group details
    }
    
    @PostMapping("/leaveGroup")
    public String leaveGroup(@RequestParam("sgId") Integer sgId, Authentication authentication, RedirectAttributes redirectAttributes) {
        String username = authentication.getName(); // Get the current user's username
       
        try {
            studyGroupService.removeParticipant(sgId, username); 
            redirectAttributes.addFlashAttribute("successMessage", "Successfully remove from the study group!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Could not leave the study group.");
        }
        return "redirect:/studygroup/" + sgId; // Redirect back to the study group details
    }
    @PostMapping("/updateStudyGroup")
    public String updateStudyGroup(@RequestParam("sgId") Integer sgId, @RequestParam("sgName") String name, 
                                   @RequestParam("sgWeekday") String weekday, @RequestParam("sgTime") String time,
                                   @RequestParam("sgLocation") String location, @RequestParam("courseId") Integer courseId,
                                   RedirectAttributes redirectAttributes) {
        try {
            studyGroupService.updateStudyGroup(sgId, name, weekday, time, location, courseId);
            redirectAttributes.addFlashAttribute("successMessage", "Study group updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating study group.");
        }
        return "redirect:/studygroup/" + sgId;
    }
    @PostMapping("/deleteStudyGroup")
    public String deleteStudyGroup(@RequestParam("sgId") Integer sgId) {
        studyGroupService.deleteStudyGroup(sgId);
        return "redirect:/home";
    }
}
