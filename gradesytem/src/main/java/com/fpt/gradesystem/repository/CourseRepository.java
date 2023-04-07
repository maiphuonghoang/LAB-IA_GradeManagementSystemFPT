/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fpt.gradesystem.repository;

import com.fpt.gradesystem.model.Course;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author ADMIN
 */
public interface CourseRepository extends JpaRepository<Course, String>{
    @Query("SELECT c, cu FROM Course c JOIN c.curriculums cu JOIN cu.students s WHERE s.studentId =:studentId order by c.termNo ASC")
    public List<Course> getCourseByStudentId(String studentId);
}
