package com.qui.shopping.product.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.qui.shopping.product.entity.Product;
import com.qui.shopping.product.respository.ProductRepository;

@Service
public class ProductService implements ProductInterface { 
	@Autowired
	private ProductRepository repository;

	@Override
	public Product findProduct(String code) {
		// TODO Auto-generated method stub
		return repository.findOne(code);
	}

	@Override
	public Set<Product> findAll() {
		// TODO Auto-generated method stub
		return (Set<Product>) repository.findAll();
	}

	@Override
	public Page<Product> findByName(Pageable pageable, String name) {
		// TODO Auto-generated method stub
		 return repository.findByName(name,pageable);
	}

	@Override
	public Product save(Product product) {
		// TODO Auto-generated method stub
		 return repository.save(product);
	}

	@Override
	public void delete(String code) {
		// TODO Auto-generated method stub
		  repository.delete(code);
	}

	@Override
	public Product updateUProduct(String id, Product product) {
		// TODO Auto-generated method stub
		 return repository.save(product);
	}

	@Override
	public boolean isProductExist(Product product) {
		// TODO Auto-generated method stub
		return repository.exists(product.getCode());
	}
	
}