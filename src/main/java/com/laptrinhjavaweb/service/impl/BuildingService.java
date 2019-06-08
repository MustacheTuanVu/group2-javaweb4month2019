package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.repository.IBuildingRepository;
import com.laptrinhjavaweb.repository.impl.BuildingRepository;
import com.laptrinhjavaweb.service.IBuildingService;

public class BuildingService implements IBuildingService {

	private IBuildingRepository buildingRepository;

	public BuildingService() {
		buildingRepository = new BuildingRepository();
	}

	@Override
	public BuildingDTO save(BuildingDTO buildingDTO) {
		// TODO Auto-generated method stub
		BuildingConverter buildingConverter = new BuildingConverter();
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		buildingEntity.setCreateDate(new Timestamp(System.currentTimeMillis()));

		Long id = buildingRepository.insert(buildingEntity);
		return null;
	}

	@Override
	public BuildingDTO update(BuildingDTO building) {
		// TODO Auto-generated method stub
		BuildingConverter buildingConverter = new BuildingConverter();
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(building);
		buildingEntity.setModifieldDate(new Timestamp(System.currentTimeMillis()));
		buildingRepository.update(buildingEntity);
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		buildingRepository.delete(id);
	}
	
	@Override
	public BuildingEntity findID(long id) {
		// TODO Auto-generated method stub
		return buildingRepository.findByID(id);
	}

	@Override
	public List<BuildingEntity> search(Map<String, Object> property, Pageble pageble, Object... where) {
		// TODO Auto-generated method stub
		return buildingRepository.search((Map<String, Object>) property,pageble,where);
	}

}
