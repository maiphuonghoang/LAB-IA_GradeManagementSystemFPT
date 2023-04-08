/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.gradesystem.controller.student;

import com.fpt.gradesystem.model.Course;
import com.fpt.gradesystem.model.Curriculum;
import com.fpt.gradesystem.model.Grade;
import com.fpt.gradesystem.model.Semester;
import com.fpt.gradesystem.repository.CourseRepository;
import com.fpt.gradesystem.repository.CurriculumRepository;
import com.fpt.gradesystem.repository.GradeRepository;
import com.fpt.gradesystem.repository.SemesterRepository;
import com.fpt.gradesystem.repository.StudentRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    SemesterRepository semesterRepository;

    @GetMapping("/curriculum/{studentId}")
    public String viewStudentCurriculum(ModelMap m, @PathVariable String studentId) {
        List<Course> courses = courseRepository.getCourseByStudentId(studentId);
        Curriculum curriculum = curriculumRepository.getCurriculumByStudentId(studentId);
        m.addAttribute("courses", courses);
        m.addAttribute("curriculum", curriculum);
        return "student/viewCurriculum";
    }

    @RequestMapping("/gradeReport")
    public String viewCourseGrade(ModelMap m, HttpServletRequest request) {
        String studentId = "HE171073";
        String courseId = request.getParameter("courseId");
        String semesterId = request.getParameter("semesterId");
        List<Semester> semesters = semesterRepository.findAll();
        List<Course> courses = courseRepository.getCourseByStudentIdSemesterId(studentId, semesterId);
        List<Grade> grades = gradeRepository.getGradeByStudentIdCourseIdSemesterId(studentId, courseId, semesterId);
        m.addAttribute("semesters", semesters);
        m.addAttribute("courses", courses);
        m.addAttribute("grades", grades);
        return "student/gradeList";
    }

}
