/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.gradesystem.controller.student;

import com.fpt.gradesystem.model.Course;
import com.fpt.gradesystem.model.Curriculum;
import com.fpt.gradesystem.repository.CourseRepository;
import com.fpt.gradesystem.repository.CurriculumRepository;
import com.fpt.gradesystem.repository.GradeRepository;
import com.fpt.gradesystem.repository.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ADMIN
 */
//@RestController
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GradeRepository gradeRepository;
    @Autowired
    CurriculumRepository curriculumRepository;
    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/curriculum/{studentId}")
    public String viewStudentCurriculum(ModelMap m, @PathVariable String studentId) {
        List<Course> courses = courseRepository.getCourseByStudentId(studentId);
        Curriculum curriculum =  curriculumRepository.getCurriculumByStudentId(studentId);
        m.addAttribute("courses", courses);
        m.addAttribute("curriculum", curriculum);
        return "student/viewCurriculum";
    }

    
}
