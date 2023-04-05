/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.gradesystem.controller;

import com.fpt.gradesystem.model.Account;
import com.fpt.gradesystem.repository.AccountRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
@Controller
public class AccountController {

    @Autowired
    AccountRepository a;

//    @RequestMapping("/accounts")
//    public List<Account> getAccount() {
//        List<Account> l = a.findAll();
//        return l;
//
//    }
    @RequestMapping("/")
    public String hello() {
        return "new";
    }

}
