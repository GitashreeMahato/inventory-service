package com.inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{
	Optional<Product> findBySku(String sku);  // use optional class when gets one result or none
}
