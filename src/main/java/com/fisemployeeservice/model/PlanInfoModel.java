package com.fisemployeeservice.model;

import java.util.List;
import com.fasterxml.jackson.core.sym.Name;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "plan_dtls_tbl")
public class PlanInfoModel {

	@Id
	@Column(name = "plan_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int planId;
	
	@NotBlank(message = "Employee Identification number must not be Blank")
	@Column(name = "employee_identification_number")
	private String empIdentNum;
	
	@NotBlank(message = "Plan year end must not be null")
	@Column(name = "plan_year_end")
	private String planYearEnd;
	
	@NotBlank(message = "Fiscal year end must not be null")
	@Column(name = "fiscal_year_end")
	private String fiscalYearEnd;
	
	@Column(name = "short_plan_year")
	@Enumerated(EnumType.STRING)
	private ShortPlanYear planYear;
	
	
	@Convert(converter = PlanTypeConverter.class)
	@Column(name = "plan_type")
	private PlanType planType;
	
	@NotBlank(message = "Plan Name must not be null")
	//@Pattern(regexp = "[A-Za-z ]{0,32}", message = "Plan name must be within 32 characteres")
	@Column(name = "plane_name")
	private String planName;
	
	@Column(name = "plan_name_line")
	private String planeNameLine1;
	
	//@Pattern(regexp = "//d{0,3}", message = "IRS plan Number must be in within three digits")
	@Column(name = "irs_number")
	private String irsNum;
	
	@NotBlank(message="Plan Contact Number must not be null")
	//@Pattern(regexp = "//d{9}", message = "Plan Contact Number must be in within 9 digits")
	@Column(name = "plan_contact_number")
	private String planContactNum;
	
	@NotBlank(message = "Product template plan must not be null")
	//@Pattern(regexp = "[A-Za-z ]{0,5}", message = "Product template plan be within 6 characteres")
	@Column(name = "product_template_plan")
	private String proTempPlan;
	
	@NotBlank(message = "Product Id plan must not be null")
	//@Pattern(regexp = "[0-9]{0,5}", message = "Product Id be within 6 characteres")
	@Column(name = "product_id")
	private String productId;
	
	//private List<EmployeeModel> emp;

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

	public ShortPlanYear getPlanYear() {
		return planYear;
	}

	public void setPlanYear(ShortPlanYear planYear) {
		this.planYear = planYear;
	}

	public PlanType getPlanType() {
		return planType;
	}

	public void setPlanType(PlanType planType) {
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