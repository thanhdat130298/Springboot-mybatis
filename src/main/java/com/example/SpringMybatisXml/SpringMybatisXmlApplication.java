package com.example.SpringMybatisXml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})


public class SpringMybatisXmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMybatisXmlApplication.class, args);
	}

}
