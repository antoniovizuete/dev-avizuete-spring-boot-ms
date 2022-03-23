package dev.avizuete.springbootms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "dev.avizuete.springbootms.repositories")
public class SpringBootMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMsApplication.class, args);
	}

}
