package com.fisemployeeservice.model;

public enum ShortPlanYear {
	YES("yes"), NO("no");
	
	private String shortPlanYear;
	
	ShortPlanYear(){}
	
	ShortPlanYear(String shortPlanYear){
		this.shortPlanYear = shortPlanYear;
	}
	public String getShortPlanYear() {
		return shortPlanYear;
	}
	
	public static ShortPlanYear fromShortYearPlan(String shortPlanYear) {
		for(ShortPlanYear type : ShortPlanYear.values()) {
			if(type.getShortPlanYear().equalsIgnoreCase(shortPlanYear)) {
				return type;
			}
		}
		throw new IllegalArgumentException("Unknow short year plan type: "+shortPlanYear);
	}
	
	
}
