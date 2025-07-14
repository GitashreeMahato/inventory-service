package com.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.model.Product;
import com.inventory.repository.ProductRepo;

@Service
public class ProductService {
	@Autowired
	private ProductRepo repo;
	
	public ProductService(ProductRepo repo) {
        this.repo = repo;
    }
	
	public Product addProduct(Product product) {
		return repo.save(product);
	}
	
	//Get list of product	
	public List<Product> getAllProduct() {
		return repo.findAll();
	}
	
	// get a product by id
	public Product getProductById(Long id) {
		return repo.findById(id).orElseThrow(()-> new RuntimeException("Product not found with id: " + id));
	}
	
	// update stock quantity
	public Product updateQuantity(Long id, int amount) {
		Product product = getProductById(id);
		int newQuantity = product.getQuantity() + amount;
		if(newQuantity<0) throw new RuntimeException("Stock cannot be negative");
		product.setQuantity(newQuantity);
		return repo.save(product);	
	}
	
	//Reserve Stock
	public Product reserveStock(Long id, int amount) {
		Product product = getProductById(id);
		if(product.getQuantity() - product.getReserved() < amount) { // 10 -2 <3
			throw new RuntimeException("Insufficient available stock");
		}
		product.setReserved(product.getReserved()+ amount);
		return repo.save(product);
	}
	
	//release stock
	public Product releaseReservedStock(Long id, int amount) {
		Product product = getProductById(id);
		if(product.getReserved() < amount) { 
			throw new RuntimeException("Cannot release more than reserved");
		}
		product.setReserved(product.getReserved()- amount);
		return repo.save(product);
	}
	
	// finalize stock
	public Product finalStock(Long id, int amount) {
		Product product = getProductById(id);
		if(product.getReserved() < amount) { // 10 -2 <3
			throw new RuntimeException("Not enough reserved stock to finilize");
		}
		product.setReserved(product.getReserved()- amount);
		product.setReserved(product.getQuantity()- amount);
		return repo.save(product);
	}
	
}
