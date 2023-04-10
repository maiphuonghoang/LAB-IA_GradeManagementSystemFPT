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
public interface CurriculumRepository extends JpaRepository<Curriculum, String> {

    @Query("SELECT cu FROM Curriculum cu JOIN cu.students s WHERE s.studentId =:studentId")
    Curriculum getCurriculumByStudentId(String studentId);

    @Query(value = "Select * from Curriculum cu\n"
            + "           inner join Curriculum_Course  cc on cc.curriculumId = cu.curriculumId\n"
            + "            inner join Course co on co.courseId = cc.courseId\n"
            + "            inner join Student s on s.curriculumId = cu.curriculumId\n"
            + "             join participate p on s.studentId = p.studentId\n"
            + "            join `Group` gr on gr.courseId = co.courseId and p.groupId = gr.groupId\n"
            + "            join GradeCategory gc on gc.courseId = co.courseId\n"
            + "            join Grade g on g.gradeCategoryId = gc.gradeCategoryId and g.studentId = s.studentId\n"
            + "            join Semester sem on sem.semesterId = gr.semesterId\n"
            + "           where s.studentId = 'HE171073' and gc.gradeItemName != 'Total' ORDER BY co.termNo ASC\n"
            + "            ", nativeQuery = true)
    public List<Curriculum> getStudentCurriculumGradev1(String studentId);

    @Query("select cu, c, g, gr, g, gc from Curriculum cu left join cu.courses c join  cu.students s join  s.groups g join c.gradeCategories gc join gc.grades gr "
            + "where s.studentId = :studentId and gc.gradeItemName != 'Total'")
    public List<Curriculum> getStudentCurriculumGrade(String studentId);

}
