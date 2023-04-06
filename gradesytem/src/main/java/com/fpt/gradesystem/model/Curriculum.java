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
import javax.persistence.JoinTable;
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
@Table(name = "Curriculum")
public class Curriculum implements Serializable {

    @Id
    @Column(name = "curriculumId")
    private String curriculumId;
    
    @Column
    private String curriculumName;

    @OneToMany(mappedBy = "curriculum")
    private List<Student> students;

    @ManyToMany(targetEntity = Course.class)
    @JoinTable(name = "Curriculum_Course",
            joinColumns = @JoinColumn(name = "curriculumId"),
            inverseJoinColumns = @JoinColumn(name = "courseId")
    )
    private List<Course> courses;

}
