/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fpt.gradesystem.repository;

import com.fpt.gradesystem.model.Curriculum;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author ADMIN
 */
public interface CurriculumRepository extends JpaRepository<Curriculum, String>{
    @Query("SELECT cu FROM Curriculum cu JOIN cu.students s WHERE s.studentId =:studentId")
    Curriculum getCurriculumByStudentId(String studentId);
}
