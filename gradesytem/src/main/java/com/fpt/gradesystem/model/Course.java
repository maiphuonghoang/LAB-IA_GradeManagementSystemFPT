/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.gradesystem.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Entity
@Table(name = "Course")
public class Course implements Serializable {

    @Id
//    @Column(name = "courseId")
    private String courseId;
    private String courseName;
    private int noCredit;
    private int termNo;

    @ManyToMany(targetEntity = Curriculum.class, mappedBy = "courses")
    private List<Curriculum> curriculums;

    @OneToMany(mappedBy = "course")
    private List<Group> groups;

    @OneToMany(mappedBy = "course")
    private List<GradeCategory> gradeCategories;
}
