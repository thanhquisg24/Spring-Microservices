package com.qui.shopping.web.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.qui.shopping.common.model.ProductModel;
import com.qui.shopping.web.common.PageWrapper;
import com.qui.shopping.web.common.ResponePageImpl;
import com.qui.shopping.web.pagination.PaginationResult;
import com.qui.shopping.web.service.ProductService;



@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	/*@RequestMapping(path = "/products", method = RequestMethod.GET,name="getProducts")
    public String getProducts(Model model,@RequestParam(value="page",required=false,defaultValue="1") int p) {
		
		if(p==0){
			p=1;
		}
		UriComponentsBuilder  uri=UriComponentsBuilder.fromPath("products");
		PageRequest pageRequest=new  PageRequest(p-1, PageWrapper.MAX_PAGE_ITEM_DISPLAY, Direction.DESC,"id");
    	Page<ProductInfo> pageProduct=iServiceImpl.findByLangClient(pageRequest);
		PageWrapper<ProductInfo> page = new PageWrapper<ProductInfo>(pageProduct, uri.build().toString());
    	//model.addAttribute("lang", locale.toString());
    	model.addAttribute("page",page);
    	model.addAttribute("services",page.getContent());
    	
    	model.addAttribute("headTitle", "layout.title.services");
        return "client/services/list";
    }*/
	
	  @RequestMapping({ "/productList" })
	  public String listProductHandler(Model model, //
	         @RequestParam(value = "name", defaultValue = "") String likeName,
	         @RequestParam(value = "page", defaultValue = "1") int page) throws JsonParseException, JsonMappingException, IOException {
	      
	      final int maxNavigationPage = 10;
	      //get product from client
	      ResponePageImpl<ProductModel>  m =productService.getProducts(likeName, page);
	      
	      PaginationResult<ProductModel> result =new PaginationResult<ProductModel>(m.getContent(), page,
	    		  									m.getTotalPages(), m.getTotalElements(),  maxNavigationPage);
	 
	      model.addAttribute("paginationProducts", result);
	      return "productList";
	   }
	
	@RequestMapping(path = "/products/{id}", method = RequestMethod.GET,name="getProduct")
	@ResponseBody
	public ProductModel getProduct(@PathVariable(value = "id") String id) {	
		ProductModel model=productService.getProduct(id);
		return model;
	}
	
	 @RequestMapping(path = "/products/list", method = RequestMethod.GET,name="getProductlists")
	 @ResponseBody
	 public ResponePageImpl<ProductModel>  getProducts(@RequestParam(value="name",required=false,defaultValue="") String name,
			 	@RequestParam(value="page",required=false,defaultValue="1") int p) throws JsonParseException, JsonMappingException, IOException {
		 		ResponePageImpl<ProductModel>  m =productService.getProducts(name, p);
					return m;
		 
	 }
	

}
