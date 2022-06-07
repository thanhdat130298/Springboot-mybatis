package com.example.SpringMybatisXml.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeHttpRequests(authorize -> authorize
//        .authorizeRequests()
//            	.antMatchers(HttpMethod.DELETE, "/api/v1/posts**").authenticated()
//            	.antMatchers(HttpMethod.GET, "/api/v1/posts/**").authenticated()
				.anyRequest().permitAll()

		).httpBasic();
		return http.build();
	}
}