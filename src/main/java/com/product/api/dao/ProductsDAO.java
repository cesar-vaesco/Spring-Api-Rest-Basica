package com.product.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.api.entity.Product;

public interface ProductsDAO extends JpaRepository<Product, Long> {

}
