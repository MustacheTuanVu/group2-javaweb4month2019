package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;

import com.laptrinhjavaweb.dto.BuildingDTO;

public class BuildingMapper implements RowMapper<BuildingDTO>{

	@Override
	public BuildingDTO mapRow(ResultSet resultSet) {
		try {
			BuildingDTO buildingModel = new BuildingDTO();
			buildingModel.setName(resultSet.getString("name"));
			return buildingModel;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
