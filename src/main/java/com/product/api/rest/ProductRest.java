package com.product.api.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.api.dao.ProductsDAO;
import com.product.api.entity.Product;

@RestController
@RequestMapping("products")
public class ProductRest {

	@Autowired
	private ProductsDAO productDAO;

	/* Mostrando todos los productos */
	@GetMapping("allProducts") // url de respuesta :8080/products/allProducts
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> products = productDAO.findAll();
		return ResponseEntity.ok(products);
	}

	/* Mostrando producto usando id */
	// url de respuesta :8080/products/{id}
	@GetMapping("{productId}")
	// @RequestMapping(value = "{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId) {
		Optional<Product> optionalProduct = productDAO.findById(productId);
		if (optionalProduct.isPresent()) {
			/* Si encuentra el producto lo muestra */
			return ResponseEntity.ok(optionalProduct.get());
		} else {
			/* Si no encuentra el producto responde con not content */
			return ResponseEntity.noContent().build();
		}
	}

	// Guardar un nuevo producto -> url de respuesta :8080/products
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		Product newProduct = productDAO.save(product);
		return ResponseEntity.ok(newProduct);
	}

	// Borrar un product -> url de respuesta :8080/products
	@DeleteMapping(value = "{productId}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("productId") Long productId) {
		Optional<Product> productDelete = productDAO.findById(productId);
		if (productDelete.isPresent()) {
			productDAO.deleteById(productId);
			return ResponseEntity.ok(null);
		}
		return ResponseEntity.noContent().build();
	}

	// Actualizando datos
	@PutMapping
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		Optional<Product> optionalProduct = productDAO.findById(product.getId());
		if (optionalProduct.isPresent()) {
			Product updateProduct = optionalProduct.get();
			updateProduct.setName(product.getName());
			productDAO.save(updateProduct);
			return ResponseEntity.ok(updateProduct);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	// url de respuesta :8080/products/hello
	// @GetMapping
	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String hello() {
		return "Hello World";
	}
}
