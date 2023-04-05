/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.gradesystem.controller.authentication;

import com.fpt.gradesystem.model.Account;
import com.fpt.gradesystem.repository.AccountRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ADMIN
 */
@Controller
public class AccountController {

    @Autowired
    AccountRepository a;

    @RequestMapping("/accounts")
    public String getAccount(ModelMap model) {
        List<Account> l = a.findAll();
        model.addAttribute("list", l);
        return ("new");

    }

}
