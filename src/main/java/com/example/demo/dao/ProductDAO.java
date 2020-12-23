package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ProductModel;

public interface ProductDAO extends JpaRepository<ProductModel, String>{
	
}
