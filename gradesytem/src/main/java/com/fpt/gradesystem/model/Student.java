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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "`Student`")
public class Student implements Serializable {

    @Id
    private String studentId;

    private String studentName;

    @OneToOne
    @JoinColumn(name = "accountId", referencedColumnName = "username")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "curriculumId")
    private Curriculum curriculum;

    @OneToMany(targetEntity = Grade.class, mappedBy = "student")
    private List<Grade> grades;

    @ManyToMany
    @JoinTable(name = "Participate",
            joinColumns = @JoinColumn(name = "studentId"),
            inverseJoinColumns = @JoinColumn(name = "groupId")
    )
    private List<Group> groups;

}
