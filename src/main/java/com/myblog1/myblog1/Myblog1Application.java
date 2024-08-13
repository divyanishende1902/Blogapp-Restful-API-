package com.myblog1.myblog1;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class
Myblog1Application {
	//here we r writing code bcz model mapper is not the library of spring so if we d not
	//mention it will nt create a bean f model mapper thstwhy extra library bean create in
	//@SpringBootApplication file
	@Bean
	public ModelMapper  modelMapper(){
		return new ModelMapper();
	}


	public static void main(String[] args) {
		SpringApplication.run(Myblog1Application.class, args);
	}

}
