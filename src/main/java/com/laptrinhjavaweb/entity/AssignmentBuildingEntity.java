package com.laptrinhjavaweb.entity;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Entity;

@Entity
public class AssignmentBuildingEntity {
	
	@Column(name = "id")
	private Long id;
	
	@Column(name = "buildingid")
	private Integer buildingID;
	
	@Column(name = "staffid")
	private Integer staffID;

	public Integer getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(Integer buildingID) {
		this.buildingID = buildingID;
	}

	public Integer getStaffID() {
		return staffID;
	}

	public void setStaffID(Integer staffID) {
		this.staffID = staffID;
	}
}
