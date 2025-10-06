package com.fisemployeeservice.controller;

import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.BackgroundJobRequest;
import org.jobrunr.scheduling.JobScheduler;
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

import com.fisemployeeservice.criteriaApi.CriteriaApiServiceDao;
import com.fisemployeeservice.criteriaApi.GlobalSearch;
import com.fisemployeeservice.dto.PlanInfoDto;
import com.fisemployeeservice.fileSevice.FileServiceImpl;
import com.fisemployeeservice.jobFactory.BackGroundJobRequest;
import com.fisemployeeservice.jobFactory.SendEmailJobRequest;
import com.fisemployeeservice.model.GlobalSearchModel;
import com.fisemployeeservice.model.PlanInfoModel;
import com.fisemployeeservice.model.SpecificationInput;
import com.fisemployeeservice.repository.PlanInfoRepository;
import com.fisemployeeservice.response.ResponseHandler;
import com.fisemployeeservice.service.PlanInfoServiceImpl;

import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.Operation;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@ComponentScan(basePackages = "com.fisemployeeservice.service")
@RequestMapping("/planInfo")
public class PlanInfoController {
	
	@Autowired
	public PlanInfoServiceImpl planInfoService;
	
	@Autowired
	private PlanInfoRepository planInfoRepository;
	
	@Autowired
	private CriteriaApiServiceDao criteriaApiService;
	
	@Autowired
	private GlobalSearch globalSearch;
	
//	@Autowired
//	private FileServiceImpl fileServiceImpl;
	
//	@Autowired
//	private JobScheduler jobScheduler;
	
//	@Autowired
//	private BackGroundJobRequest backgroundJobRequest;
	

	@Operation(
	    summary = "Create plan information",
	    description = "This endpoint creates a new plan with the provided details."
	)
	@PostMapping("/create")
	public  ResponseEntity<?> createPlan(@RequestBody PlanInfoDto dto) {
		PlanInfoModel model = null;
		try {
			model = planInfoService.addPlanInfo(dto);
			if (model != null) {
//				BackgroundJobRequest.enqueue(
//						new SendEmailJobRequest("dsivaleelavardhan@deloitte.com", "ttejkumar@deloitte.com", "Plan confirmation", "Congrates"));
				return ResponseHandler.responseBuilder(HttpStatus.CREATED, "EMS000", model, "Plan created successfully");
			} else {
				return ResponseHandler.responseBuilder(HttpStatus.NOT_ACCEPTABLE, "EMS201", "Plan creation not successfully");
			}
		} catch (Exception ex) {
			 return ResponseHandler.responseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, "EMS300", "It's not yours! It's on us, something went wrong");
		}
		
	}
	
	@PostMapping("/batchUpdate")
	public void creatBulkPlan(@RequestBody List<PlanInfoModel> model){
		 planInfoService.insertDataIntoRequestTable(model);
	}
	
	@PostMapping("/batchUpdateTrnTemplate")
	public void creatBulkPlanTransactionTemplate(@RequestBody List<PlanInfoModel> model){
		 planInfoService.insertDataIntoRequestTableTransactionTemplate(model);
	}
	
	@PostMapping("/batchUpdateTransaction")
	public void creatBulkPlanTransaction(@RequestBody List<PlanInfoModel> model){
		 planInfoService.insertDataIntoRequestTableTransactional(model);
	}
	
	@Operation(
	    summary = "Update plan information",
	    description = "This endpoint updates the details of an existing plan based on the provided information."
	)
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
	
	@Operation(
	    summary = "Get plan information by ID",
	    description = "This endpoint retrieves the details of a specific plan using its unique identifier."
	)
	@GetMapping("{planId}")
	public ResponseEntity<?> getRecordsById(@PathVariable int planId){
		try {
		PlanInfoModel responseModel = planInfoService.getEmployyeDetailsByPlanId(planId);
		if (responseModel != null ) {
			return ResponseHandler.responseBuilder(HttpStatus.ACCEPTED, "EMS000", responseModel,
					"Fetched records successfully of plan Id: "+planId);
		} else {
			return ResponseHandler.responseBuilder(HttpStatus.NOT_FOUND, "EMS203", "No record found found with the Plan Id: "+planId);
		}
		}catch(Exception ex) {
			return ResponseHandler.responseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, "EMS302",
					"It's not yours! It's on us, something went wrong");
		}
	}
	
	
	@Operation(
	    summary = "Delete plan information by ID",
	    description = "This endpoint deletes a specific plan from the system using its unique identifier."
	)
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
	
	@Operation(
	    summary = "View all plan information",
	    description = "This endpoint retrieves a list of all available plans."
	)
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
	
	/*
	 * In the request must have column and value
	 */
	@GetMapping("/ByEquals")
	public List<PlanInfoModel> getByEquals(@RequestBody SpecificationInput input){
		return criteriaApiService.getEmployeeData(input);
	}
	
	/*
	 * Fetchs PlanInfo details between specific time period
	 */
	@GetMapping("/ByBetween")
	public List<PlanInfoModel> getEmployeeListbtwDates(@RequestBody SpecificationInput input) throws ParseException{
		return criteriaApiService.getEmpListBetweenDates(input);
		
	}
	
	/*
	 * Get employee list by sort
	 */
	@GetMapping("/BySort")
	 public List<PlanInfoModel> getEmpDetailsBySort(@RequestBody SpecificationInput input){
		return criteriaApiService.getEmpSortedDetails(input);
	}
	/*
	 * Implemented pagination
	 */
	@GetMapping("/ByPage")
	public List<PlanInfoModel> getEmplDetailsByPagination(@RequestBody SpecificationInput input){
		return criteriaApiService.getEmpDetailsaPage(input);
	}
	/*
	 * Implement Like
	 */
	@GetMapping("/ByLike")
	public List<PlanInfoModel> getEmplDetailsLike(@RequestBody SpecificationInput input){
		return criteriaApiService.getEmpDetailsLike(input);
	}
	
	/*
	 * Implement and through predicates
	 */
	@GetMapping("/ByPredicateAnd")
	public List<PlanInfoModel> getEmplDetailsEqualAndLike(@RequestBody SpecificationInput input){
		return criteriaApiService.getEmplDetailsListByAnd(input);
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> getEmployyeDetails(@RequestBody GlobalSearchModel model){
		try {
		if(!StringUtils.isBlank(model.getSearch().trim())) {
			ImmutablePair<List<PlanInfoModel>, String> result = globalSearch.getEmployeeDetails(model);
			return ResponseHandler.responseBuilder(HttpStatus.OK, result.getLeft(),"EMS000", result.getRight());
		}
		else {
			return ResponseHandler.responseBuilder(HttpStatus.BAD_REQUEST, "EMS203", "Invalid request");
		}
		}catch(Exception ex) {
			return ResponseHandler.responseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, "EMS303",
					"Please provide valid input");
		}
	}
	
//	@PostMapping("/createFromFiles")
//	public ResponseEntity<?> creatPlanFromFiles() throws IOException{
//		fileServiceImpl.process();
//		return null;
//		
//	}
	
}