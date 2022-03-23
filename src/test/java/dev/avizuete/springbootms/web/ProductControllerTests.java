package dev.avizuete.springbootms.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import dev.avizuete.springbootms.models.Product;
import dev.avizuete.springbootms.services.ProductService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTests {
	
	@MockBean
	ProductService service;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	private ObjectWriter objectWriter;
	
	@BeforeEach()
	public void init() {
		this.objectWriter = this.objectMapper.writer().withDefaultPrettyPrinter();
	}
	
	@Test
	public void testFindAll() throws Exception {
		final Product product = new Product();
 		product.setId(1);
		
		final Iterable<Product> products = Arrays.asList(product);
		
		Mockito.when(service.findAll()).thenReturn(products);
		
		mockMvc.perform(get("/products"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", Matchers.hasSize(1)))
			.andExpect(jsonPath("$[0].id", Matchers.is(1)));
	}
	
	@Test
	public void testFindById() throws Exception {
		final Product product = new Product();
 		product.setId(1);
		
		Mockito.when(service.findById(Mockito.anyLong())).thenReturn(product);
		
		mockMvc.perform(get("/products/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", Matchers.is(1)));
	}
	
	@Test
	public void testCreate() throws Exception {
		final Product product = new Product();
 		product.setId(1);
 			    
 	    final String requestJson = this.objectWriter.writeValueAsString(product);
		
		Mockito.when(service.create(Mockito.any(Product.class))).thenReturn(product);
		
		mockMvc.perform(post("/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestJson)
			)
			.andExpect(status().isCreated());
	}
	
	@Test
	public void testUpdate() throws Exception {
		final Product product = new Product();
 		product.setId(1);
 			    
 	    final String requestJson = this.objectWriter.writeValueAsString(product);
		
		Mockito.when(service.create(Mockito.any(Product.class))).thenReturn(product);
		
		mockMvc.perform(put("/products/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestJson)
			)
			.andExpect(status().isOk());
	}
	
	@Test
	public void testDelete() throws Exception {
		Mockito.doNothing().when(service).delete(Mockito.anyLong());
		
		mockMvc.perform(delete("/products/1"))
			.andExpect(status().isOk());
	}
}
