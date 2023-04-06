/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.gradesystem.controller.student;

import com.fpt.gradesystem.model.Student;
import com.fpt.gradesystem.repository.StudentRepository;
import java.util.List;
import java.util.Optional;
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
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

//    @RequestMapping("/student")
//    public List<Student> getStudents() {
//        return studentRepository.findAll();
//    }
//    @GetMapping("student/{studentId}")
//    public Optional<Student> getStudentById(@PathVariable String studentId) {
//        return studentRepository.findById(studentId);
//    }
    @RequestMapping("/student")
    public String getStudents(ModelMap m) {
        List<Student> students = studentRepository.findAll();
        m.addAttribute("students", students);
        return "login/login";
    }

}
