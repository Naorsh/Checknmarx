package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductDAO;
import com.example.demo.datatypes.requests.BaseProductReq;
import com.example.demo.datatypes.response.BaseResponse;
import com.example.demo.datatypes.response.GetProductsRes;
import com.example.demo.model.ProductModel;
import com.example.demo.services.interfaces.IMainService;

@Service
public class MainService implements IMainService{
	
	@Autowired
	private ProductDAO productRepo;

	@Override
	public ResponseEntity<?> addProduct(BaseProductReq request) {
		ProductModel model = new ProductModel();
		model.setName(request.getName());
		model.setPrice(request.getPrice());
		model.setFreeAmount(request.getFreeAmount());
		productRepo.save(model);
		
		BaseResponse response = new BaseResponse();
		response.setMessage("Product added successfully");
		response.setStatus("Success");
		return new ResponseEntity<BaseResponse>(response ,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteProduct(String name) {
		//add input validation for name 
		productRepo.deleteById(name);
		BaseResponse response = new BaseResponse();
		response.setMessage("Product deleted successfully");
		response.setStatus("Success");
		return new ResponseEntity<BaseResponse>(response ,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getProducts() {
		GetProductsRes response = new GetProductsRes();
		response.setProducts(productRepo.findAll());
		return new ResponseEntity<GetProductsRes>(response ,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getCriteriaProducts(String name, Integer budget) {
		BaseResponse response = new BaseResponse();
		Optional<ProductModel> result = productRepo.findById(name);
		Integer total = getMaxAmmountOfProduct(result.get().getPrice(), budget, result.get().getFreeAmount());
		response.setMessage("you can have " + total.toString() + " " + name + " in total");
		return new ResponseEntity<BaseResponse>(response ,HttpStatus.OK);
	}
	
	private Integer getMaxAmmountOfProduct(Integer price, Integer budget, Integer refundable) {
		Integer total = 0;
		Integer intialAmmount = budget/price;
		total+=intialAmmount;
		Integer stockRenewal = intialAmmount/refundable;
		total+=stockRenewal;
		Integer leftovers = intialAmmount%refundable;
		Integer currnetStock = stockRenewal + leftovers;
		while(currnetStock >= refundable){
		  	stockRenewal = currnetStock/refundable;
		    total+=stockRenewal;
		    leftovers = currnetStock%refundable;
		    currnetStock = stockRenewal + leftovers;
		  }
	  return total;
	}

}
