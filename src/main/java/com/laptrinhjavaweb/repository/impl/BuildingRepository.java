package com.laptrinhjavaweb.repository.impl;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.IBuildingRepository;

public class BuildingRepository extends AbstractJDBC<BuildingEntity> implements IBuildingRepository{

	/* 
	 * Vấn đề: 
	 * excute query đưa xuống tầng database
	 * đưa vào set ngược lại là thừa
	*/
	
	/* 
	 * Bài toán: 
	 * đưa entity vào là đưa xuống tầng database luôn
	 */
	
	/*
	@Override
	public Long insert(BuildingEntity entity) {
		// TODO Auto-generated method stub
		String sql = 
				"INSERT INTO `estate_javaweb_learning`.`building`"+
				"(`name`," + 
				"`ward`," + 
				"`district`,"+
				"`street`,"+
				"`structure`,"+
				"`numberOfBasement`,"+
				"`buildingArea`,"+
				"`costRent`,"+
				"`costDescription`,"+
				"`serviceCost`,"+
				"`carCost`,"+
				"`motorbikecost`,"+
				"`overtimecost`,"+
				"`electricitycost`,"+
				"`deposit`,"+
				"`payment`,"+
				"`timerent`,"+
				"`timedecorator`,"+
				"`managername`,"+
				"`managerphone`,"+
				"`createdate`,"+
				"`createby`,"+
				"`buildingtype`)"+
				"VALUES"+
				"?,"+
				"?,"+
				"?,"+
				"?,"+
				"?,"+
				"?,"+
				"?,"+
				"?,"+
				"?,"+
				"?,"+
				"?,"+
				"?,"+
				"?,"+
				"?,"+
				"?,"+
				"?,"+
				"?,"+
				"?,"+
				"?,"+
				"?,"+
				"?,"+
				"?,"+
				"?);"+
				"";
		return null;
	}
	*/

}
