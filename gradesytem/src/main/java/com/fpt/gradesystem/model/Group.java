/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.gradesystem.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "'Group'")
public class Group implements Serializable{
    @Id
    private int groupId;
    private String groupName;
    
    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;
    
    @ManyToOne
    @JoinColumn(name = "instructorId")
    private Instructor instructor;
    
    @ManyToOne
    @JoinColumn(name = "semesterId")
    private Semester semester;
    
    @ManyToMany(mappedBy = "groups")
    private List<Student> students;
    
}
