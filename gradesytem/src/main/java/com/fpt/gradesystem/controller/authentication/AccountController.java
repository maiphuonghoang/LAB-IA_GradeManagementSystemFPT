/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.gradesystem.controller.authentication;

import com.fpt.gradesystem.model.Account;
import com.fpt.gradesystem.repository.AccountRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ADMIN
 */
//@Controller
@RestController
@RequestMapping("/accounts")

public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/{username}")
    public Optional<Account> getAccount(@PathVariable String username) {
        return accountRepository.findById(username);
    }

}
