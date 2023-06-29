package com.example.toy_survlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
@ServletComponentScan
@SpringBootApplication
public class ToySurvletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToySurvletApplication.class, args);
	}

}
