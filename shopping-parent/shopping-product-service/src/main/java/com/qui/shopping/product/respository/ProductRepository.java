package com.qui.shopping.product.respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.qui.shopping.product.entity.Product;

public interface ProductRepository extends  CrudRepository<Product, String>{

	 @Query("SELECT t FROM Product t where lower(t.name) like %:p_name%  order by p.createDate desc" )//only work with Named query ,not native query
	 Page<Product> findByName(@Param("p_name") String name,Pageable pageable);
}

