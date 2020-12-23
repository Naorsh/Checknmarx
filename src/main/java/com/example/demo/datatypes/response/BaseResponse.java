package com.example.demo.datatypes.response;

import lombok.Data;

@Data
public class BaseResponse {
	private String status;
	private String message;
}
