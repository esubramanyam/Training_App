package com.fisemployeeservice.dto;

public class PlanInfoDto {
	
    private int planId;
    private String empIdentNum;
    private String planYearEnd;
    private String fiscalYearEnd;
    //Enumerated of ShortPlanYear
    private String planYear;  
    //Enumerated of PlanType
    private String planType;     
    private String planName;
    private String planeNameLine1;
    private String irsNum;
    private String planContactNum;
    private String proTempPlan;
    private String productId;
    
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public String getEmpIdentNum() {
		return empIdentNum;
	}
	public void setEmpIdentNum(String empIdentNum) {
		this.empIdentNum = empIdentNum;
	}
	public String getPlanYearEnd() {
		return planYearEnd;
	}
	public void setPlanYearEnd(String planYearEnd) {
		this.planYearEnd = planYearEnd;
	}
	public String getFiscalYearEnd() {
		return fiscalYearEnd;
	}
	public void setFiscalYearEnd(String fiscalYearEnd) {
		this.fiscalYearEnd = fiscalYearEnd;
	}
	public String getPlanYear() {
		return planYear;
	}
	public void setPlanYear(String planYear) {
		this.planYear = planYear;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getPlaneNameLine1() {
		return planeNameLine1;
	}
	public void setPlaneNameLine1(String planeNameLine1) {
		this.planeNameLine1 = planeNameLine1;
	}
	public String getIrsNum() {
		return irsNum;
	}
	public void setIrsNum(String irsNum) {
		this.irsNum = irsNum;
	}
	public String getPlanContactNum() {
		return planContactNum;
	}
	public void setPlanContactNum(String planContactNum) {
		this.planContactNum = planContactNum;
	}
	public String getProTempPlan() {
		return proTempPlan;
	}
	public void setProTempPlan(String proTempPlan) {
		this.proTempPlan = proTempPlan;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
    
    
}
