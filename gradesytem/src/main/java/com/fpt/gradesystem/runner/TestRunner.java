/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.gradesystem.runner;

import com.fpt.gradesystem.model.Account;
import com.fpt.gradesystem.model.Curriculum;
import com.fpt.gradesystem.repository.AccountRepository;
import com.fpt.gradesystem.repository.CurriculumRepository;
import com.fpt.gradesystem.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author ADMIN
 */
@Component
public class TestRunner implements CommandLineRunner {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CurriculumRepository curriculumRepository;
    @Autowired
    GradeRepository gradeRepository;
    
    
    @Override
    public void run(String... args) throws Exception {
        for (Account allAccount : accountRepository.getAllAccounts()) {
            System.out.println(allAccount.getStudent());
        }
        curriculumRepository.findAll().forEach(System.out::println);
        gradeRepository.findAll().forEach(g->System.out.println(
                g.getGradeCategory().getGradeCategoryName() +""+ g.getGradeCategory().getGradeItemName() + " " +  g.getGradeValue())
        );
        
    }

}
