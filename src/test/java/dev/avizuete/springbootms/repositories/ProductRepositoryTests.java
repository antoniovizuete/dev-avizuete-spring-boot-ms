package dev.avizuete.springbootms.repositories;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.avizuete.springbootms.models.Product;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryTests {

	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void testCRUD() {
		final long id = 1L;
		final String desc = "Desc";
		final Product product = new Product();
		product.setId(id);
		product.setDescription(desc);
		
		productRepository.save(product);
		
		final Iterable<Product> products = productRepository.findAll();
		Assertions.assertThat(products).extracting(Product::getDescription).containsOnly(desc);
		
		productRepository.deleteById(id);
		final Optional<Product> optionalProduct = productRepository.findById(id);
		Assertions.assertThat(optionalProduct.orElseGet(() -> null)).isNull();
	}
}
