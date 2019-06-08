package com.laptrinhjavaweb.enums;

public enum BuildingTypeEnums {
	
	TANG_TRET("TẦNG TRỆT"),
    NGUYEN_CAN("NGUYÊN CĂN"),
    NOI_THAT("NỘI THẤT");
	
	
    
    private String value;
    
    BuildingTypeEnums(String value) {
        this.value = value;
    }

	public String getValue() {
		return value;
	}
}
