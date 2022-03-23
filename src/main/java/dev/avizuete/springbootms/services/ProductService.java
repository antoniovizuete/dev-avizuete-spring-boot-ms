package dev.avizuete.springbootms.services;

import dev.avizuete.springbootms.models.Product;

public interface ProductService {

	Iterable<Product> findAll();
	
	Product findById(long id);
	
	Product create(Product product);
	
	Product update(long id, Product product);
	
	void delete(long id);
}