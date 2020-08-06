package com.product.api.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.entity.Product;

@RestController
@RequestMapping("products")
public class ProductRest {

	@GetMapping("/product")
	public ResponseEntity<Product> getProduct(){
		Product product = new Product();
		product.setId(1L);   
		product.setName("Product 1");
		
		return ResponseEntity.ok(product);
	}
	
	//@GetMapping
	@RequestMapping(value="hello", method = RequestMethod.GET)
	public String hello() {
		return "Hello World";
	}
}