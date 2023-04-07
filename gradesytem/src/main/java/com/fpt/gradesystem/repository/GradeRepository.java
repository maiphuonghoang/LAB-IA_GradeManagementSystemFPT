/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fpt.gradesystem.repository;

import com.fpt.gradesystem.model.Grade;
import com.fpt.gradesystem.modelkey.GradeKey;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author ADMIN
 */
public interface GradeRepository extends JpaRepository<Grade, GradeKey>{
    @Query("select distinct gr,c from Course c join c.gradeCategories gc join gc.grades gr join gr.student s join s.groups g join g.semester ses"
            + " WHERE s.studentId =:studentId and c.courseId = :courseId and ses.semesterId = :semesterId" )
    List<Grade> getGradeByStudentIdCourseIdSemesterId(String studentId, String courseId, String semesterId);
}
