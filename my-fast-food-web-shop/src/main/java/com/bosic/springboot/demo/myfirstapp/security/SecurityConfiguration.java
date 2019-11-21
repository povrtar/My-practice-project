package com.bosic.springboot.demo.myfirstapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	Environment env;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("Mihajlo").password(env.getProperty("miha.pass")).roles("USER", "ADMIN")
				.and().withUser("Dunja").password(env.getProperty("dunja.pass")).roles("USER", "ADMIN").and()
				.withUser("Zvezdan").password(env.getProperty("zvezdan.pass")).roles("USER", "ADMIN").and()
				.withUser("Dragana").password(env.getProperty("dragana.pass")).roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/").access("hasRole('USER')")

				.antMatchers("/customers", "/managerPage").access("hasRole('ADMIN')").and().formLogin();
	}

	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordncoder() {
		return new BCryptPasswordEncoder();
	}
}
