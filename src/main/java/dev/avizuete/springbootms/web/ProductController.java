package dev.avizuete.springbootms.web;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.avizuete.springbootms.models.Product;
import dev.avizuete.springbootms.services.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService service;
	
	@GetMapping
	public Iterable<Product> findAll() {
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public Product findById(@PathVariable("id") long id) {
		return service.findById(id);
	}
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody Product product) {
        if (Objects.isNull(product)) {
        	throw new Error("Bad Request");
        }
        return service.create(product).getId();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable( "id" ) Long id, @RequestBody Product product) {
        service.update(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
