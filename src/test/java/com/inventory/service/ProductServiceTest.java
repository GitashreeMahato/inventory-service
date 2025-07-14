
	package com.inventory.service;

	import com.inventory.model.Product;
import com.inventory.repository.ProductRepo;
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	import org.mockito.Mockito;

	import java.util.Optional;

	import static org.junit.jupiter.api.Assertions.*;
	import static org.mockito.Mockito.*;

	class ProductServiceTest {

	    private ProductRepo productRepo;
	    private ProductService productService;

	    @BeforeEach
	    void setUp() {
	        productRepo = mock(ProductRepo.class);
	        productService = new ProductService(productRepo);
	    }

	    @Test
	    void testAddProduct() {
	        Product product = new Product("Shoes", "SH-001", 50);
	        when(productRepo.save(any(Product.class))).thenReturn(product);

	        Product result = productService.addProduct(product);

	        assertEquals("Shoes", result.getName());
	        verify(productRepo, times(1)).save(product);
	    }

	    @Test
	    void testUpdateStockPositive() {
	        Product product = new Product("Bag", "BG-001", 10);
	        product.setId(1L);
	        when(productRepo.findById(1L)).thenReturn(Optional.of(product));
	        when(productRepo.save(any(Product.class))).thenReturn(product);

	        Product result = productService.updateQuantity(1L, 5);
	        assertEquals(15, result.getQuantity());
	    }

	    @Test
	    void testUpdateStockNegativeInvalid() {
	        Product product = new Product("Bag", "BG-001", 5);
	        product.setId(1L);
	        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

	        Exception exception = assertThrows(RuntimeException.class, () -> {
	            productService.updateQuantity(1L, -10);
	        });

	        assertEquals("Stock cannot be negative", exception.getMessage());
	    }

	    @Test
	    void testReserveStockSuccess() {
	        Product product = new Product("Laptop", "LT-001", 10);
	        product.setReserved(2);
	        product.setId(1L);
	        when(productRepo.findById(1L)).thenReturn(Optional.of(product));
	        when(productRepo.save(any(Product.class))).thenReturn(product);

	        Product result = productService.reserveStock(1L, 5);
	        assertEquals(7, result.getReserved());
	    }

	    @Test
	    void testReserveStockFailure() {
	        Product product = new Product("Laptop", "LT-001", 5);
	        product.setReserved(4);
	        product.setId(1L);
	        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

	        Exception exception = assertThrows(RuntimeException.class, () -> {
	            productService.reserveStock(1L, 3);
	        });

	        assertEquals("Insufficient available stock", exception.getMessage());
	    }

	    @Test
	    void testReleaseStockFailure() {
	        Product product = new Product("Laptop", "LT-001", 10);
	        product.setReserved(2);
	        product.setId(1L);
	        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

	        Exception exception = assertThrows(RuntimeException.class, () -> {
	            productService.releaseReservedStock(1L, 5);
	        });

	        assertEquals("Cannot release more than reserved", exception.getMessage());
	    }
	}



