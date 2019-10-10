package com.bosic.springboot.demo.myfirstapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.bosic.springboot.demo.myfirstapp")
public class MyfirstappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyfirstappApplication.class, args);
	}

}
