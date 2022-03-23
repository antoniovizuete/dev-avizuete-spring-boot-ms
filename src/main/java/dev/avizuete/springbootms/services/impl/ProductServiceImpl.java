package dev.avizuete.springbootms.services.impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
	@Cacheable("products")
	public Iterable<Product> findAll() {
		return repository.findAll();
	}

	@Override
	@Cacheable(value="product", key="#id")
	public Product findById(final long id) {
		return repository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Product not found"));
	}

	@Override
	@Caching(evict = {			
			@CacheEvict(value="products", allEntries = true),
			@CacheEvict(value="product", key="#result.id")
	})
	public Product create(final Product product) {
		return repository.save(product);
	}

	@Override
	@Caching(evict = {			
			@CacheEvict(value="products", allEntries = true),
			@CacheEvict(value="product", key="#id")
	})
	public Product update(final long id, final Product product) {
		final Product productDb = this.findById(id);
		productDb.setCategory(product.getCategory());
		productDb.setColor(product.getColor());
		productDb.setDescription(product.getDescription());
		productDb.setSize(product.getSize());
		
		return repository.save(productDb);
	}

	@Override
	@Caching(evict = {			
			@CacheEvict(value="products", allEntries = true),
			@CacheEvict(value="product", key="#id")
	})
	public void delete(final long id) {
		repository.deleteById(id);
	}
	
	
}
