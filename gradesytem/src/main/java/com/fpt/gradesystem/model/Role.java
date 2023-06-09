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
@Table(name = "Role")
public class Role implements Serializable {

    @Id
//    @Column(name = "roleId")
    private int roleId;
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<Account> accounts;

    @ManyToMany(targetEntity = Feature.class)
    @JoinTable(name = "Role_Feature",
            joinColumns = {
                @JoinColumn(name = "roleId")},
            inverseJoinColumns = {
                @JoinColumn(name = "featureId")}
    )
    private List<Feature> features;
}
