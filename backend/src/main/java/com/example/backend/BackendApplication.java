package com.example.backend;

import com.example.backend.Service.UserService;
import com.example.backend.model.Prole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Prole(null,"ADMIN"));
			userService.saveRole(new Prole(null,"ROLE_01"));
			userService.saveRole(new Prole(null,"ROLE_02"));

//			userService.saveUser(new User(null,"admin","JAvlGPq9JyTdtvBO6x2llnRI1+gxwIyPqCKAn3THIKk=","adcdcsd","ADMIN"));
//			userService.saveUser(new User(null,"jayasith","BYAog22i85F1R0yicAkJRvEv6fsyIqTSjpRcLKQp+Ow=","sdcsdc","ROLE_01"));
//			userService.saveUser(new User(null,"chamindu","z1yvgzB8aEXu8SleLMQDSGWpL/3DPWoRserrq3nBgYU=","sdcsdcsdc","ROLE_02"));


		};

	}

}
