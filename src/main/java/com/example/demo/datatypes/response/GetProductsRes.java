package com.example.demo.datatypes.response;

import java.util.List;

import com.example.demo.model.ProductModel;

import lombok.Data;

@Data
public class GetProductsRes extends BaseResponse{
	private List<ProductModel> products;
}
