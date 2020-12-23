package com.example.demo.datatypes.requests;

import lombok.Data;

@Data
public class BaseProductReq {
	private String name;
	private Integer price;
	private Integer freeAmount;
}
