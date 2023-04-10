/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.gradesystem.controller;

import com.fpt.gradesystem.model.Course;
import com.fpt.gradesystem.model.Curriculum;
import com.fpt.gradesystem.model.Grade;
import com.fpt.gradesystem.model.Semester;
import com.fpt.gradesystem.repository.CourseRepository;
import com.fpt.gradesystem.repository.CurriculumRepository;
import com.fpt.gradesystem.repository.GradeRepository;
import com.fpt.gradesystem.repository.SemesterRepository;
import com.fpt.gradesystem.repository.StudentRepository;
import java.util.Arrays;
import java.util.HashMap;
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
        double sum = 0;
        double feValue = -1;

        //Nếu chưa thi fe thì chưa tính điểm tổng: chưa xử lí 
        //Total bằng trung bình tổng các điểm thành phần 
        for (Grade g : grades) {
            if (g.getGradeCategory().getGradeItemName().equals("Total")) {
                double totalWeight = 0;
                double totalValue = 0;
                String category = g.getGradeCategory().getGradeCategoryName();
                for (Grade subGrade : grades) {
                    if (subGrade.getGradeCategory().getGradeCategoryName().equals(category) && !subGrade.getGradeCategory().getGradeItemName().equals("Total")) {
                        totalWeight += subGrade.getGradeCategory().getWeight();
                        totalValue += subGrade.getGradeCategory().getWeight() * subGrade.getGradeValue();
                    }
                }
                double accurateValue = totalValue / totalWeight;
                g.setGradeValue(Math.round(accurateValue * 10.0) / 10.0);

                sum += g.getGradeValue() * g.getGradeCategory().getWeight();

                //nếu thi lại 
                if (g.getGradeCategory().getGradeCategoryName().equalsIgnoreCase("Final Exam")) {
                    feValue = g.getGradeValue() * g.getGradeCategory().getWeight();
                }
                if (g.getGradeCategory().getGradeCategoryName().equalsIgnoreCase("Final Exam Resit")
                        && g.getGradeValue() > 0) {
                    sum = sum - feValue;
                }

            }
        }

        m.addAttribute("sum", sum / 100);
        m.addAttribute("semesters", semesters);
        m.addAttribute("courses", courses);
        m.addAttribute("grades", grades);
        return "student/gradeList";
    }

    @RequestMapping("/academicGrade")
    public String viewCurriculumGrade(ModelMap m) {
        String studentId = "HE171073";
        List<Curriculum> curriculums = curriculumRepository.getStudentCurriculumGrade(studentId);

        HashMap<Object, Object> hm = new HashMap<>();

        courseRepository.getCourseAndGroup("HE171073")
                .stream()
                .forEach(ob -> hm.put(ob[0], new Object[]{ob[0], ob[1], ob[2], ob[3], ob[4], ob[3]}));
        for (Object key : hm.keySet()) {
            Object[] values = (Object[]) hm.get(key);
            double sum = 0;
            double feValue = -1;
            List<com.fpt.gradesystem.model.Grade> grades = gradeRepository.getGradeTotalByStudentIdCourseId("HE171073", key.toString());
                for (com.fpt.gradesystem.model.Grade g : grades) {

                    if (g.getGradeCategory().getGradeItemName().equals("Total")) {
                        double totalWeight = 0;
                        double totalValue = 0;
                        String category = g.getGradeCategory().getGradeCategoryName();
                        for (com.fpt.gradesystem.model.Grade subGrade : grades) {
                            if (subGrade.getGradeCategory().getGradeCategoryName().equals(category) && !subGrade.getGradeCategory().getGradeItemName().equals("Total")) {
                                totalWeight += subGrade.getGradeCategory().getWeight();
                                totalValue += subGrade.getGradeCategory().getWeight() * subGrade.getGradeValue();
                            }
                        }
                        double accurateValue = totalValue / totalWeight;
                        g.setGradeValue(Math.round(accurateValue * 10.0) / 10.0);

                        sum += g.getGradeValue() * g.getGradeCategory().getWeight();

                        //nếu thi lại 
                        if (g.getGradeCategory().getGradeCategoryName().equalsIgnoreCase("Final Exam")) {
                            feValue = g.getGradeValue() * g.getGradeCategory().getWeight();
                        }
                        if (g.getGradeCategory().getGradeCategoryName().equalsIgnoreCase("Final Exam Resit")
                                && g.getGradeValue() > 0) {
                            sum = sum - feValue;
                        }

                    }

                    values[values.length - 1] = sum / 100;

                }
        }
        m.addAttribute("hm", hm);

        return "student/reportGrade";
    }

}
