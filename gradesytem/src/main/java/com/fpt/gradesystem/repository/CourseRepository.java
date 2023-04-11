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
public interface CourseRepository extends JpaRepository<Course, String> {

    @Query("SELECT c, cu FROM Course c JOIN c.curriculums cu JOIN cu.students s WHERE s.studentId =:studentId order by c.termNo ASC")
    public List<Course> getCourseByStudentId(String studentId);

    @Query("SELECT c FROM Course c JOIN c.curriculums cu JOIN cu.students s JOIN s.groups g JOIN g.semester ses WHERE s.studentId =:studentId and ses.semesterId = :semesterId and c.courseId = g.course.courseId")
    public List<Course> getCourseByStudentIdSemesterId(String studentId, String semesterId);

    @Query(value = "select c.*, ses.semesterName from course c left join `group` g on c.courseId = g.courseId\n"
            + "join participate p on p.groupId = g.groupId \n"
            + "join semester ses on ses.semesterId = g.semesterId \n"
            + "join student s on s.studentId = p.studentId\n"
            + "where s.studentId = :studentId order by c.termNo", nativeQuery = true)
    public List<Object[]> getCourseAndGroup(String studentId);

    @Query(value = "SELECT * FROM course c Join curriculum_course cc on c.courseId = cc.courseId\n"
            + "join curriculum cu on cu.curriculumId = cc.curriculumId join student s on s.curriculumId = cu.curriculumId\n"
            + "where s.studentId = 'HE171073' AND c.courseId NOT IN (\n"
            + "SELECT c.courseId from Course c join `Group` g on c.courseId = g.courseId\n"
            + "join  participate p on p.groupId = g.groupId\n"
            + "join Student s on s.studentId = p.studentId\n"
            + "where s.studentId = ?1 \n"
            + ")", nativeQuery = true)
    public List<Course> getNotStartedCourse(String studentId);


}
