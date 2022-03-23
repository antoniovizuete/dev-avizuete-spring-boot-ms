package dev.avizuete.springbootms;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.avizuete.springbootms.repositories.ProductRepository;
import dev.avizuete.springbootms.services.ProductService;
import dev.avizuete.springbootms.web.ProductController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SpringBootMsApplicationTests {
	
	@Autowired
	ProductController productController;

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	void contextLoads() {
		Assertions.assertThat(this.productController).isNotNull();
		Assertions.assertThat(this.productService).isNotNull();
		Assertions.assertThat(this.productRepository).isNotNull();
	}

}
