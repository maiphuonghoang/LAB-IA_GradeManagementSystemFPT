/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.gradesystem.controller.student;

import com.fpt.gradesystem.model.Grade;
import com.fpt.gradesystem.model.Student;
import com.fpt.gradesystem.repository.CurriculumRepository;
import com.fpt.gradesystem.repository.GradeRepository;
import com.fpt.gradesystem.repository.StudentRepository;
import java.util.List;
import java.util.Optional;
import org.apache.taglibs.standard.lang.jstl.GreaterThanOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
//@RestController
@Controller
@RequestMapping("/student")
public class StudentController {

//    @Autowired
//    StudentRepository studentRepository;
//    @Autowired
//    GradeRepository gradeRepository;
    @Autowired
    CurriculumRepository curriculumRepository;
//
//    @GetMapping("/curriculum")
//    public String getStudents(ModelMap m) {
//        Iterable<Student> curriculum = studentRepository.findAll();
//        m.addAttribute("curriculum", curriculum);
//        return "login/login";
//    }
//    @RequestMapping("/test")
//    public String getAllStudents(){
//        curriculumRepository.findAll().forEach(System.out::println);
//        return "hello";
//    }

    
}
