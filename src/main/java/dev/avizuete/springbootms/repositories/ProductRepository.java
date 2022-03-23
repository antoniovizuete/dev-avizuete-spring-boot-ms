package dev.avizuete.springbootms.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dev.avizuete.springbootms.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
