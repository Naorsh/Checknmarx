package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.datatypes.requests.BaseProductReq;
import com.example.demo.services.interfaces.IMainService;

@RequestMapping("api/checkmarx/products")
@RestController
public class MainController {
	
	@Autowired
	private IMainService mainService;
	
	@PostMapping
	public ResponseEntity<?> addProduct(@RequestBody BaseProductReq request){
		return mainService.addProduct(request);
	}
	
	@DeleteMapping("{name}")
	public ResponseEntity<?> deleteProduct(@PathVariable("name") String name){
		return mainService.deleteProduct(name);
	}
	
	@GetMapping()
	public ResponseEntity<?> getAllProducts(){
		return mainService.getProducts();
	}
	
	@GetMapping("criteria")
	public ResponseEntity<?> getCriteriaProducts(@RequestParam("name") String name, @RequestParam("badget") Integer budget){
		return mainService.getCriteriaProducts(name, budget);
	}
}
