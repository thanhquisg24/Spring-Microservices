package com.qui.shopping.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qui.shopping.common.model.ProductModel;
import com.qui.shopping.web.client.ProductClient;



@Service
public class ProductService {

	@Autowired
	private ProductClient productClient;
	
	public ProductModel getProduct(String id) {
		ProductModel model=productClient.getProduct(id);
		return model;
	}
    
}
