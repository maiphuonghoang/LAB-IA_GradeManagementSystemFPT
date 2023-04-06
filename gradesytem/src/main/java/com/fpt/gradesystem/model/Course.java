/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.gradesystem.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Course")
public class Course implements Serializable {

    @Id
    @Column(name = "courseId")

    private String courseId;
    @Column
    private String courseName;
    
    @Column
    private int noCredit;
    
    @Column
    private int termNo;

    @ManyToMany(targetEntity = Curriculum.class, mappedBy = "courses")
    private List<Curriculum> curriculums;

    @OneToMany(mappedBy = "course")
    private List<Group> groups;

    @OneToMany(mappedBy = "course")
    private List<GradeCategory> gradeCategories;
}
