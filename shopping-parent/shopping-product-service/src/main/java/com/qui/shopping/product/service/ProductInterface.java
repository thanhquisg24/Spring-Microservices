package com.qui.shopping.product.service;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.qui.shopping.product.entity.Product;


public interface  ProductInterface {
	
	public void delete (String code);
	public Set<Product> findAll();
	public Page<Product> findByName(Pageable pageable, String string);
	public Product findProduct(String code);
	public boolean isProductExist(Product product);
	public boolean isProductExist(String code);
	public Product save (Product product);
	public Product updateUProduct(String id, Product product);
}