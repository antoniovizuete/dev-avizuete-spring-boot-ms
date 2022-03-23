package dev.avizuete.springbootms.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.avizuete.springbootms.models.Product;
import dev.avizuete.springbootms.repositories.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {

	@InjectMocks
	ProductServiceImpl productService;
	
	@Mock
	ProductRepository repository;

	@Test
	public void testFindAll() throws Exception {
		final Product product = new Product();
 		product.setId(1);
		
		final Iterable<Product> products = Arrays.asList(product);
		
		Mockito.when(repository.findAll()).thenReturn(products);
		
		final List<Product> result = (List<Product>) productService.findAll();
		
		assertEquals(1, result.size());
		assertEquals(1L, result.get(0).getId());
		
	}
	
	@Test
	public void testFindById() throws Exception {
		final long id = 1L;
		final Product product = new Product();
 		product.setId(id);
		
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(product));
		
		final Product result = productService.findById(id);
		
		assertEquals(id, result.getId());
		
	}
	
	@Test
	public void testCreate() throws Exception {
		final String description = "description";
		final Product product = new Product();
 		product.setDescription(description);
 		
		Mockito.when(repository.save(Mockito.any(Product.class))).thenReturn(product);
		
		final Product result = productService.create(product);
		
		assertEquals(description, result.getDescription());
	}
	
	@Test
	public void testUpdate() throws Exception {
		final long id = 1L;
		final Product product = new Product();
 		product.setId(id);
 		
		Mockito.when(repository.existsById(Mockito.anyLong())).thenReturn(true);
		Mockito.when(repository.save(Mockito.any(Product.class))).thenReturn(product);
		
		final Product result = productService.update(id, product);
		
		assertEquals(id, result.getId());
	}
	
	@Test
	public void testDelete() throws Exception {
		productService.delete(1L);
	}
}
