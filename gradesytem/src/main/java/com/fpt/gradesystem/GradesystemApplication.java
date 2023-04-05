package com.fpt.gradesystem;

import com.fpt.gradesystem.model.Account;
import com.fpt.gradesystem.repository.AccountRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@RestController
public class GradesystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(GradesystemApplication.class, args);
	}

}
