package com.myperssonal.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource securityDataSource;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(securityDataSource).passwordEncoder(passwordEncoder());
		// auth.jdbcAuthentication() doesn`t work well
		// auth.inMemory working fine and that is reason I leave code in comments
		/*
		 * .usersByUsernameQuery(
		 * "select username,password,enabled from users where username= ?")
		 * .authoritiesByUsernameQuery(
		 * "select username,authority from authorities where username= ?").
		 * passwordEncoder(passwordncoder()); /*
		 * auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.
		 * crypto.password.NoOpPasswordEncoder.getInstance())
		 * .withUser("user1").password("secret1").roles("USER").and()
		 * .withUser("admin1").password("secret1").roles("USER", "ADMIN");
		 */
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().antMatchers("/").hasRole("CUSTOMER").antMatchers("/api/service/**")
				.hasRole("ADMIN").antMatchers("/api/customers/**").hasRole("ADMIN").and().csrf().disable().headers()
				.frameOptions().disable().and().formLogin().permitAll();
	}
}