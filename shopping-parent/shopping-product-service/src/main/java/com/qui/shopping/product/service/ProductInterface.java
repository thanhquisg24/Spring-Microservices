package com.qui.shopping.product.service;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.qui.shopping.entity.Product;


public interface  ProductInterface {
	
	public Product findProduct(String code);
	public Set<Product> findAll();
	public Page<Product> findByName(Pageable pageable, String string);
	public Product save (Product product);
	public void delete (String code);
}