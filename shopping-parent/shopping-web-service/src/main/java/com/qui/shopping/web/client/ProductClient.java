package com.qui.shopping.web.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qui.shopping.common.model.ProductModel;

@FeignClient(name="api-gateway-product-service",url="${api.gateway.product.service}")
public interface ProductClient {

	 @RequestMapping(path = "products/{id}", method = RequestMethod.GET,name="getProduct")
	 public ProductModel getProduct(@PathVariable(value = "id") String id) ;
}
