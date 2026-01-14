package com.korea.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProductRepository extends 
JpaRepository<Product,Long>,
QuerydslPredicateExecutor<Product>{
	
	List<Product> findAllByOrderByIdxDesc();
	
	@Query(value="SELECT IFNULL(max(idx), 10000) + 1 FROM product" ,nativeQuery = true)
    Long findMaxIdx();
}
