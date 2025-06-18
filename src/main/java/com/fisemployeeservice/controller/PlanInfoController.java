package com.fisemployeeservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fisemployeeservice.dto.PlanInfoDto;
import com.fisemployeeservice.model.PlanInfoModel;
import com.fisemployeeservice.repository.PlanInfoRepository;
import com.fisemployeeservice.response.ResponseHandler;
import com.fisemployeeservice.service.PlanInfoServiceImpl;

import java.util.List;


@RestController
@ComponentScan(basePackages = "com.fisemployeeservice.service")
@RequestMapping("/planInfo")
public class PlanInfoController {
	
	@Autowired
	public PlanInfoServiceImpl planInfoService;
	
	@Autowired
	private PlanInfoRepository planInfoRepository;
	
	@PostMapping("/create")
	public  ResponseEntity<?> createPlan(@RequestBody PlanInfoDto dto) {
		PlanInfoModel model = null;
		try {
			model = planInfoService.addPlanInfo(dto);
			if (model != null) {
				return ResponseHandler.responseBuilder(HttpStatus.ACCEPTED, "EMS000", model, "Plan created successfully");
			} else {
				return ResponseHandler.responseBuilder(HttpStatus.NOT_ACCEPTABLE, "EMS201", "Plan creation not successfully");
			}
		} catch (Exception ex) {
			 return ResponseHandler.responseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, "EMS300", "It's not yours! It's on us, something went wrong");
		}
		
	}
	
	@PutMapping("/update/{planId}")
	public ResponseEntity<?> updatePlan(@PathVariable int planId, @RequestBody PlanInfoDto dto) {
		try {
			Optional<PlanInfoModel> responseModel = planInfoRepository.findById(planId);
			if (!responseModel.isPresent()) {
				return ResponseHandler.responseBuilder(HttpStatus.NOT_FOUND, "EMS000",
						"No record found with the PlanId: " + planId);
			}
			PlanInfoModel existModel = responseModel.get();
			PlanInfoModel requestModel = planInfoService.mapEntityAndDto(dto);
			requestModel.setPlanId(planId);
			boolean isUpdated = requestModel.equals(existModel);
			if (!isUpdated) {
				PlanInfoModel model = planInfoRepository.save(requestModel);
				return ResponseHandler.responseBuilder(HttpStatus.ACCEPTED, "EMS000", model,
						"Plan updated successfully");
			} else {
				return ResponseHandler.responseBuilder(HttpStatus.NOT_FOUND, "EMS000",
						"No updation performed on PlanId: " + planId);
			}
		} catch (Exception ex) {
			return ResponseHandler.responseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, "EMS301",
					"It's not yours! It's on us, something went wrong");
		}
	}
	
	@GetMapping("{planId}")
	public ResponseEntity<?> getRecordsById(@PathVariable int planId){
		try {
		PlanInfoModel responseModel = planInfoService.getEmployyeDetailsByPlanId(planId);
		if (responseModel != null ) {
			return ResponseHandler.responseBuilder(HttpStatus.ACCEPTED, "EMS000", responseModel,
					"Fetched records successfully of plan Id: "+planId);
		} else {
			return ResponseHandler.responseBuilder(HttpStatus.NOT_FOUND, "EMS203", "No record found found");
		}
		}catch(Exception ex) {
			return ResponseHandler.responseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, "EMS302",
					"It's not yours! It's on us, something went wrong");
		}
	}
	
	@DeleteMapping("/delete/{planId}")
	public ResponseEntity<?> deleteById(@PathVariable int planId) {
		try {
			Optional<PlanInfoModel> responseModel = planInfoRepository.findById(planId);
			if (!responseModel.isPresent()) {
				return ResponseHandler.responseBuilder(HttpStatus.NOT_FOUND, "EMS000",
						"No record found with the PlanId: " + planId);
			}
			boolean isDeleted = planInfoService.deletePlanById(planId);
			if (isDeleted) {
				return ResponseHandler.responseBuilder(HttpStatus.OK, "EMS000",
						"Record delete successfully with the PlanId: " + planId);
			} else {
				return ResponseHandler.responseBuilder(HttpStatus.OK, "EMS000",
						"No record found with the PlanId: " + planId);
			}
		} catch (Exception ex) {
			return ResponseHandler.responseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, "EMS303",
					"It's not yours! It's on us, something went wrong");
		}
	}
	
	
	@GetMapping("/")
	public ResponseEntity<?> viewAllRecords(){
		try {
		List<PlanInfoModel> responseModel = null;
		responseModel = planInfoService.fetchAllRecords();
		if (!responseModel.isEmpty()) {
			return ResponseHandler.responseBuilder(HttpStatus.ACCEPTED, "EMS000", responseModel,
					"Fetched records all successfully");
		} else {
			return ResponseHandler.responseBuilder(HttpStatus.NOT_FOUND, "EMS203", "No record found found");
		}
		}catch(Exception ex) {
			return ResponseHandler.responseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, "EMS304",
					"It's not yours! It's on us, something went wrong");
		}
	}
}