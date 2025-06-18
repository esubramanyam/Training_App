package com.fisemployeeservice.service;

import com.fisemployeeservice.dto.PlanInfoDto;
import com.fisemployeeservice.model.EmployeeModel;
import com.fisemployeeservice.model.PlanInfoModel;

import java.util.List;
import java.util.Optional;

public interface PlanInfoService {
	//Create plan Info
	PlanInfoModel addPlanInfo(PlanInfoDto planInfoDto);
	
	//Fetch employee details by product Id
	PlanInfoModel getEmployyeDetailsByPlanId(int planId);
	
	//Update plan info
	PlanInfoModel updatePlanInfo(int planId, PlanInfoDto planInfoDto);
	
	//Fetch all plan info details
	List<PlanInfoModel> fetchAllRecords();
	
	//Delete plan by id
	boolean deletePlanById(int planId);
}