package com.inventory.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // to generate Id's automatically 
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private String sku;
	@Column(nullable = false)
	private int quantity;
	@Column(nullable = false)
	private int reserved = 0;
	
	public Product() {}
	
	public Product(String name, String sku, int quantity) {
		this.name = name;
		this.sku = sku;
		this.quantity = quantity;
		this.reserved= 0;
	}
//	Getter & Setter
	public Long getId() { 
		return id; 
		}
    public void setId(Long id) { 
    	this.id = id; 
    	}
	public String getName() {
		return name;
		
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getReserved() {
		return reserved;
	}
	public void setReserved(int reserved) {
		this.reserved = reserved;
	}
	
	public String toString() {
		return "Product: [ Product name: "+ name+ ", SKU: "+ sku+ ", Quantity: "+ quantity+ ", Reserved: "+ reserved +"]";
	}
	
}
