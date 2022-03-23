package dev.avizuete.springbootms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMsApplication.class, args);
	}

}
