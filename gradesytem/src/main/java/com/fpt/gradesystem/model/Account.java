/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.gradesystem.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author ADMIN
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Account")
public class Account  implements Serializable{
    //Serializable: Khi gửi dữ liệu ra môi trường internet, nó sẽ thực hiện hành động là 
    //serialize đồng bộ dữ liệu ra ngoài; còn khi trở về hệ thống nó sẽ deserialize để chuyển 
    //thành dạng dữ liệu trên máy xử lí được 
    @Id
    private String username;
    private String password;
    
}
