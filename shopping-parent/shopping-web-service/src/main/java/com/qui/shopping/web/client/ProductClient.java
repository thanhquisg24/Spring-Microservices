package com.qui.shopping.web.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qui.shopping.common.model.ProductModel;
//import com.qui.shopping.web.common.PageWrapper;

@FeignClient(name="api-gateway-product-service",url="${api.gateway.product.service}")
public interface ProductClient {

	 @RequestMapping(path = "products/{id}", method = RequestMethod.GET,name="getProduct")
	 public ProductModel getProduct(@PathVariable(value = "id") String id) ;
	 
	 @RequestMapping(path = "/products", method = RequestMethod.GET,name="getProducts")
	 public String getProducts(@RequestParam(value="name",required=false,defaultValue="") String name,@RequestParam(value="page",required=false,defaultValue="1") int p) ;
}
