package com.fisemployeeservice.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fisemployeeservice.model.PlanInfoModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResponseHandler {
	public static ResponseEntity<Map<String, Object>> responseBuilder(HttpStatus status, String errorCode,
			List<PlanInfoModel> response, String description) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("Response",response);
		map.put("Error Code", errorCode);
		map.put("Error Message", description);
		return new  ResponseEntity<>(map, status);
	}
	
	public static ResponseEntity<Map<String, Object>> responseBuilder(HttpStatus status, String errorCode,
			PlanInfoModel response, String description) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("Response",response);
		map.put("Error Code", errorCode);
		map.put("Error Message", description);
		return new  ResponseEntity<>(map, status);
	}
	
	public static ResponseEntity<Map<String, Object>> responseBuilder(HttpStatus status, String errorCode, String description) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("Error Code", errorCode);
		map.put("Error Message", description);
		return new  ResponseEntity<>(map, status);
	}
}
