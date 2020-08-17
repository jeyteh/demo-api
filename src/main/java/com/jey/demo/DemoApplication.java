package com.jey.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.log4j.Log4j2;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@Log4j2
public class DemoApplication {

	public static void main(String[] args) {
		log.info("App Started");
		SpringApplication.run(DemoApplication.class, args);
	}

}
