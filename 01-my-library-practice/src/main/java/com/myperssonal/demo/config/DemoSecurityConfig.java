package com.myperssonal.demo.config;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	// Authentication : User --> Roles
	@Autowired
	DataSource securityDataSource;
		protected void configure(AuthenticationManagerBuilder auth)
				throws Exception {


			
			auth.jdbcAuthentication().dataSource(securityDataSource)
			 .usersByUsernameQuery(
					   "select username,password, enabled from users where username=?")
					  .authoritiesByUsernameQuery(
					   "select username, role from user_roles where username=?").passwordEncoder(passwordencoder());
		/*	auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
			.withUser("user1").password("secret1").roles("USER").and()
					.withUser("admin1").password("secret1").roles("USER", "ADMIN");*/
		}

		// Authorization : Role -> Access
		// survey -> USER
		protected void configure(HttpSecurity http) throws Exception {
			http.httpBasic().and().authorizeRequests()
			
			.antMatchers("/api/users/**").hasRole("ADMIN")
			.antMatchers("/api/customers/**").hasRole("ADMIN")
			.and().csrf().disable()
					.headers().frameOptions().disable();
		}
		@Bean(name="passwordEncoder")
		public PasswordEncoder passwordencoder() {
			return new BCryptPasswordEncoder();
		}
}





