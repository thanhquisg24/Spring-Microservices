package com.qui.shopping.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.qui.shopping.entity.Product;
import com.qui.shopping.product.service.ProductInterface;

@RestController
public class ProductController {


	@Autowired
	private ProductInterface productService;

		@RequestMapping(path = "product", method = RequestMethod.POST,name="createProduct")
	    public ResponseEntity<Void> createProduct(@RequestBody Product product,UriComponentsBuilder ucBuilder) {
	    	    if (productService.isProductExist(product)) {
	    	       // System.out.println("A User with name " + user.getName() + " already exist");
	    	        return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	    	    }
	    	    productService.save(product);
	    	    HttpHeaders headers = new HttpHeaders();
	    	    headers.setLocation(ucBuilder.path("/product/{id}").buildAndExpand(product.getCode()).toUri());
	    	    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	      
	    }

	    @RequestMapping(path = "product/{id}", method = RequestMethod.GET,name="getProduct")
	    public ResponseEntity<Product> getProduct(@PathVariable(value = "id") String id) {
	    	 Product product = productService.findProduct(id);
	    	    if (product == null) {
	    	        //System.out.println("User with id " + id + " not found");
	    	        return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	    	    }
	    	    return new ResponseEntity<Product>(product, HttpStatus.OK);
	       
	    }

	    @RequestMapping(path = "product/{id}", method = RequestMethod.PUT,name ="updateProduct")
	    public  ResponseEntity<Product> updateProduct(@PathVariable(value = "id") String id,
	                                     @RequestBody Product product) {

	    		Product currentproduct = productService.findProduct(id);
	    	    if (currentproduct==null) {
	    	       // System.out.println("User with id " + id + " not found");
	    	        return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	    	    }
	    	    currentproduct.setName(product.getName());
	    	    currentproduct.setPrice(product.getPrice());
	    	    currentproduct.setImage(product.getImage());
	    	    productService.updateUProduct(id, product);
	    	    return new ResponseEntity<Product>(currentproduct, HttpStatus.OK);
	    
	    }

	    @RequestMapping(path = "product/{id}", method = RequestMethod.DELETE,
	            name = "deleteProduct")
	    public  ResponseEntity<Product>  deleteProduct(@PathVariable(value = "id") String id) {
	

    		Product currentproduct = productService.findProduct(id);
    	    if (currentproduct==null) {
    	       // System.out.println("User with id " + id + " not found");
    	        return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
    	    }
    	    productService.delete(id);
	        return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	       
	    }
	
	
}
