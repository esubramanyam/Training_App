package com.fisemployeeservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.fisemployeeservice.dto.PlanInfoDto;
import com.fisemployeeservice.model.EmployeeModel;
import com.fisemployeeservice.model.PlanInfoModel;
import com.fisemployeeservice.model.PlanType;
import com.fisemployeeservice.model.ShortPlanYear;
import com.fisemployeeservice.repository.PlanInfoRepository;


@Service
@ComponentScan(basePackages = "com.fisemployeeservice.repository")
public class PlanInfoServiceImpl implements PlanInfoService {
	
	@Autowired
	public PlanInfoRepository planInfoRepo;


	@Override
	public PlanInfoModel addPlanInfo(PlanInfoDto planInfoDto) {
		PlanInfoModel planInfoEntity = mapEntityAndDto(planInfoDto);
		PlanInfoModel model = null;
		model = planInfoRepo.save(planInfoEntity);
		if(model != null) {
			return model;
		}
		return null;
	}

	@Override
	public PlanInfoModel getEmployyeDetailsByPlanId(int planId) {
		Optional<PlanInfoModel> requestModel = planInfoRepo.findById(planId);
		if(requestModel.isPresent()) {
			return requestModel.get();
		}
		return null;
	}

	@Override
	public PlanInfoModel updatePlanInfo(int planId, PlanInfoDto planInfoDto) {
		PlanInfoModel planInfoEntity = mapEntityAndDto(planInfoDto);
		PlanInfoModel model = null;
		model = planInfoRepo.save(planInfoEntity);
		if(model != null) {
			return model;
		}
		return null;
	}
	
	@Override
	public boolean deletePlanById(int planId) {
		Optional<PlanInfoModel> requestModel = planInfoRepo.findById(planId);
		if(requestModel.isPresent()) {
			planInfoRepo.deleteById(planId);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public List<PlanInfoModel> fetchAllRecords(){
		List<PlanInfoModel> model = planInfoRepo.findAll();
		if(model != null) {
			return model;
		}
		return null;
	}
	
	public PlanInfoModel mapEntityAndDto(PlanInfoDto planInfoDto) {
		PlanInfoModel entity = new PlanInfoModel();
		
		entity.setEmpIdentNum(planInfoDto.getEmpIdentNum());
		entity.setFiscalYearEnd(planInfoDto.getFiscalYearEnd());
		entity.setPlanYearEnd(planInfoDto.getPlanYearEnd());
		entity.setIrsNum(planInfoDto.getIrsNum());
		entity.setPlanContactNum(planInfoDto.getPlanContactNum());
		entity.setPlaneNameLine1(planInfoDto.getPlaneNameLine1());
		entity.setPlanName(planInfoDto.getPlanName());
		

		entity.setPlanType(PlanType.fromPlan(planInfoDto.getPlanType()));
		entity.setPlanYear(ShortPlanYear.fromShortYearPlan(planInfoDto.getPlanYear()));
		
		entity.setProductId(planInfoDto.getProductId());
		entity.setProTempPlan(planInfoDto.getProTempPlan());
		
		return entity;
	}
}
