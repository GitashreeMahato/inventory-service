package com.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.model.Product;
import com.inventory.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
//	@Autowired
//	Product product;	
	
	@Autowired
	private ProductService ps;
	
	//1. Add a new product
	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product) {
		return ps.addProduct(product);
	}
	
//	2. Get all products
	@GetMapping
	public List<Product> getAllProduct(){
		return ps.getAllProduct();
	}
	
//	3. Get product by id
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Long id){
		return ps.getProductById(id);
	}
	
//	4. update stock quantity (increase or decrease)
	
	@PutMapping("/{id}/stock")
	public Product updateQuantity(@PathVariable Long id, @RequestParam int amount){
		return ps.updateQuantity(id, amount);
	}
	
//	5. Reserve stock
	
	@PostMapping("/{id}/reserve")
	public Product reserveStock(@PathVariable Long id, @RequestParam int amount){
		return ps.reserveStock(id, amount);
	}
	
	// 6. Release stock
	@PostMapping("/{id}/release")
	public Product releaseReservedStock(@PathVariable Long id, @RequestParam int amount){
		return ps.releaseReservedStock(id, amount);
	}
	
//	7. Final stock
	@PostMapping("/{id}/final")
	public Product finalStock(@PathVariable Long id, @RequestParam int amount){
		return ps.finalStock(id, amount);
	}
	
	
}




