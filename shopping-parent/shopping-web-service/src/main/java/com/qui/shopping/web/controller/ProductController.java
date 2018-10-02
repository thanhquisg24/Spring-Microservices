package com.qui.shopping.web.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.UriComponentsBuilder;


@Controller
public class ProductController {

	@RequestMapping(path = "/products", method = RequestMethod.GET,name="getProducts")
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
    }

}
