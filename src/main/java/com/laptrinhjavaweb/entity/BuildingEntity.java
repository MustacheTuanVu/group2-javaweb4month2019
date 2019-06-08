package com.laptrinhjavaweb.entity;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Entity;
import com.laptrinhjavaweb.annotation.Table;


@Entity
@Table(name = "building")
public class BuildingEntity extends BaseEntity {
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "ward")
	private String ward;
	
	@Column(name = "street")
	private String street; 
	
	@Column(name = "structure")
	private String structure;
	
	@Column(name = "numberOfBasement")
	private Integer numberOfBasement;
	
	@Column(name = "buildingArea")
	private Integer buildingArea;
	
	@Column(name = "district")
	private String district;
	
	@Column(name = "costRent")
	private Integer costRent;
	
	@Column(name = "costDescription")
	private String costDescription;
	
	@Column(name = "serviceCost")
	private String serviceCost;
	
	@Column(name = "carCost")
	private String carCost;
	
	@Column(name = "motorbikecost")
	private String motorbikeCost;
	
	@Column(name = "overtimecost")
	private String overtimeCost;
	
	@Column(name = "electricitycost")
	private String electricityCost;
	
	@Column(name = "deposit")
	private String deposit;
	
	@Column(name = "payment")
	private String payment;
	
	@Column(name = "timerent")
	private String timeRent;
	
	@Column(name = "timedecorator")
	private String timeDecorator;
	
	@Column(name = "managername")
	private String managerName;
	
	@Column(name = "managerphone")
	private String managerPhone;
	
	@Column(name = "buildingtype")
	private String buildingType;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStructure() {
		return structure;
	}
	public void setStructure(String structure) {
		this.structure = structure;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public Integer getBuildingArea() {
		return buildingArea;
	}
	public void setBuildingArea(Integer buildingArea) {
		this.buildingArea = buildingArea;
	}
	
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public Integer getCostRent() {
		return costRent;
	}
	public void setCostRent(Integer costRent) {
		this.costRent = costRent;
	}
	public String getCostDescription() {
		return costDescription;
	}
	public void setCostDescription(String costDescription) {
		this.costDescription = costDescription;
	}
	public String getServiceCost() {
		return serviceCost;
	}
	public void setServiceCost(String serviceCost) {
		this.serviceCost = serviceCost;
	}
	public String getCarCost() {
		return carCost;
	}
	public void setCarCost(String carCost) {
		this.carCost = carCost;
	}
	public String getMotorbikeCost() {
		return motorbikeCost;
	}
	public void setMotorbikeCost(String motorbikeCost) {
		this.motorbikeCost = motorbikeCost;
	}
	public String getOvertimeCost() {
		return overtimeCost;
	}
	public void setOvertimeCost(String overtimeCost) {
		this.overtimeCost = overtimeCost;
	}
	public String getElectricityCost() {
		return electricityCost;
	}
	public void setElectricityCost(String electricityCost) {
		this.electricityCost = electricityCost;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getTimeRent() {
		return timeRent;
	}
	public void setTimeRent(String timeRent) {
		this.timeRent = timeRent;
	}
	public String getTimeDecorator() {
		return timeDecorator;
	}
	public void setTimeDecorator(String timeDecorator) {
		this.timeDecorator = timeDecorator;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	
	public String getBuildingType() {
		return buildingType;
	}
	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}
	@Override
	public String toString() {
		return "BuildingEntity [name=" + name + ", ward=" + ward + ", street=" + street + ", structure=" + structure
				+ ", numberOfBasement=" + numberOfBasement + ", buildingArea=" + buildingArea + "]";
	}
	
}
