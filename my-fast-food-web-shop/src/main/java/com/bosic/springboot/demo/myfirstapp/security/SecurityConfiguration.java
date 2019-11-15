package com.bosic.springboot.demo.myfirstapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().withUser("Mihajlo").password("miha2810") .roles("USER", "ADMIN").
        and().withUser("Dunja").password("dunja1311") .roles("USER","ADMIN").
        and().withUser("Zvezdan").password("zvezdan2204") .roles("USER","ADMIN").
        and().withUser("Dragana").password("dragana1708").roles("USER");
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/").access("hasRole('USER')")
                
                .antMatchers("/customers", "/managerPage").access("hasRole('ADMIN')").and()
                .formLogin();
    }
}
