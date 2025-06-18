package com.fisemployeeservice.model;

public enum PlanType {
	PLAN_401K("401(k) plan"), PLAN_502K("502(k) plan");
	
	private String plan;
	
	
	PlanType(){}

	private PlanType(String plan) {
		this.plan = plan;
	}
	
	public String getPlan() {
		return plan;
	}
	
	public static PlanType fromPlan(String plan) {
		for(PlanType type : PlanType.values()) {
			if(type.getPlan().equalsIgnoreCase(plan)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Unknow plan type Expection: "+ plan);
	}
}
