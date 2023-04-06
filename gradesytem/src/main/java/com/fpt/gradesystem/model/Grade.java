/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.gradesystem.model;

import com.fpt.gradesystem.modelkey.GradeKey;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
@Table(name = "Grade")
public class Grade implements Serializable{
    @EmbeddedId
    private GradeKey gradeId;
    
    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "studentId")
    private Student student;
    
    @ManyToOne
    @MapsId("gradeCategoryId")
    @JoinColumn(name = "gradeCategoryId")
    private GradeCategory gradeCategory;
    
    @Column
    private double gradeValue;
}
