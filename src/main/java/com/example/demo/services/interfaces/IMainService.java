package com.example.demo.services.interfaces;

import org.springframework.http.ResponseEntity;

import com.example.demo.datatypes.requests.BaseProductReq;

public interface IMainService {
	public ResponseEntity<?> addProduct(BaseProductReq request);
	public ResponseEntity<?> deleteProduct(String name);
	public ResponseEntity<?> getProducts();
	public ResponseEntity<?> getCriteriaProducts(String name, Integer budget);
}
