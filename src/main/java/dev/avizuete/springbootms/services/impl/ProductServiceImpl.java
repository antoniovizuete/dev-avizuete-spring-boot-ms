package dev.avizuete.springbootms.services.impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import dev.avizuete.springbootms.models.Product;
import dev.avizuete.springbootms.repositories.ProductRepository;
import dev.avizuete.springbootms.services.ProductService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository repository;

	@Override
	public Iterable<Product> findAll() {
		return repository.findAll();
	}

	@Override
	public Product findById(final long id) {
		return repository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Product not found"));
	}

	@Override
	public Product create(final Product product) {
		return repository.save(product);
	}

	@Override
	public Product update(final long id, final Product product) {
		final Product productDb = this.findById(id);
		productDb.setCategory(product.getCategory());
		productDb.setColor(product.getColor());
		productDb.setDescription(product.getDescription());
		productDb.setSize(product.getSize());
		
		return repository.save(productDb);
	}

	@Override
	public void delete(final long id) {
		repository.deleteById(id);
	}
	
	
}
