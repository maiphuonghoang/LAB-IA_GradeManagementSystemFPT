/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.gradesystem.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
/*
    - transient: trạng thái dối tượng chưa liên kết với dòng nào dưới csdl -> save() insert
    - persistent cập nhật thì đối tượng phải liên kết với 1 dòng nào đó -> get(c,id) select; sau đó c.setProperty; save() update set description where id= 

    fetch Eager: mỗi lần lấy 1 sẽ tự động lấy nhiều 
        khi truy cập vào đối tượng cha thì tự động join để lấy cả con 
*/

