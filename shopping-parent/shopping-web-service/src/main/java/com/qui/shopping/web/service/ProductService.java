package com.qui.shopping.web.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.qui.shopping.common.model.ProductModel;
import com.qui.shopping.web.client.ProductClient;
import com.qui.shopping.web.common.CustomSortDeserializer;
import com.qui.shopping.web.common.PageWrapper;
import com.qui.shopping.web.common.ResponePageImpl;



@Service
public class ProductService {

	@Autowired
	private ProductClient productClient;
	
	public ProductModel getProduct(String id) {
		ProductModel model=productClient.getProduct(id);
		return model;
	}
	 public ResponePageImpl<ProductModel>  getProducts(String name,int p) throws JsonParseException, JsonMappingException, IOException {
		 String m=productClient.getProducts(name, p);
		 ResponePageImpl<ProductModel> result=null;
		// ResponePageImpl<ProductModel> result =null;
		 if(!m.isEmpty()) {
			 ObjectMapper objectMapper = new ObjectMapper();
			 JsonNode root = objectMapper.readTree(m);
			// System.out.println(root.path("content").toString());
			 List<ProductModel> content=objectMapper.readValue(root.path("content").toString(),  new TypeReference<List<ProductModel>>(){});
			 int number = root.path("number").asInt();
			 int size=root.path("size").asInt();
			  int totalElements=root.path("totalElements").asInt();
			  int totalPages=root.path("totalPages").asInt();
			  int numberOfElements=root.path("numberOfElements").asInt();
			  boolean last=root.path("last").asBoolean();
			  boolean first=root.path("first").asBoolean();
			  JsonNode nodes=root.get("sort");
			  Order[] orders = new Order[root.path("sort").size()];
			  int i=0;
			  for(JsonNode obj : nodes){
			        orders[i] =  new Order(Direction.valueOf(obj.get("direction").asText()), obj.get("property").asText());
			        i++;
			    }
			    Sort sort = new Sort(orders);
			    //root.path("content").size();
			//  Order orders[]=objectMapper.readValue(root.path("sort").toString(), new TypeReference<Order[]>(){});
			  
			  //Sort sort = new Sort(orders);
			 // Sort sort=objectMapper.readValue(root.path("sort").toString(), new TypeReference<CustomSortDeserializer>(){});
			  result = new  ResponePageImpl<ProductModel>();
			  result.setContent(content);
			  result.setNumber(number);
			  result.setSize(size);
			  result.setTotalElements(totalElements);
			  result.setTotalPages(totalPages);
			  result.setNumberOfElements(numberOfElements);
			  result.setFirst(first);
			  result.setLast(last);
			  result.setSort(sort);
			 // result=resresult.pageImpl();
			// PageRequest.of(number, size), totalElements
			
			// int number = root.path("number").asInt();
			 //int size=root.path("size").asInt();
			// Pageable pageable =PageRequest.;
			 //int totalElements=root.path("totalElements").asInt();
			 
			// result=objectMapper.readValue(m, new TypeReference<ResponePageImpl<ProductModel>>(){});
		 }
		
		return result;
		 
	 }
}
