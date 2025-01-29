package com.ee.githubApiApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class GithubApiAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubApiAppApplication.class, args);
	}

}
