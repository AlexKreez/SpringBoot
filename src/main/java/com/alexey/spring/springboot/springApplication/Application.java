package com.alexey.spring.springboot.springApplication;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.alexey.spring.springboot")
@EnableJpaRepositories(basePackages = "com.alexey.spring.springboot.repository")
public class Application {

	public static void main(String[] args) {SpringApplication.run(Application.class, args);
	}
}
