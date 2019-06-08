package com.laptrinhjavaweb.controller.admin.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Sorter;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.impl.BuildingService;
import com.laptrinhjavaweb.utils.HttpUtil;

@WebServlet(urlPatterns = {"/api-admin-building"})
public class BuildingAPI extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IBuildingService buildingService;
	
	public BuildingAPI() {
		buildingService = new BuildingService();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); 
		BuildingDTO buidingDTO =  HttpUtil.of(request.getReader()).toModel(BuildingDTO.class); //XONG, CHỈ VIỆC BINDING Building qua
		// trả lại về json
		String action = request.getParameter("action");
		
		Map<String, Object> proprerty = new HashMap<>();
		proprerty.put("name", buidingDTO.getName());
		proprerty.put("ward", buidingDTO.getWard());
		proprerty.put("structure", buidingDTO.getStructure());
		
		//proprerty.put("maxPageItem", );
		//proprerty.put("page", );
		
		
		long id = 0;
		switch (action) {
		case "search":
			int page = Integer.parseInt(request.getParameter("page"));
			int maxPage = Integer.parseInt(request.getParameter("maxPage"));
			String sortBy = request.getParameter("sortBy");
			String sortType = request.getParameter("sortType");
			
			Sorter sorter = new Sorter(sortBy, sortType);
			PageRequest pageRequest = new PageRequest(page, maxPage, sorter);
			
			List<BuildingEntity> buildingEntities = buildingService.search(proprerty,pageRequest);
			mapper.writeValue(response.getOutputStream(), buildingEntities);//
			break;
		case "save":
			buidingDTO = buildingService.save(buidingDTO);
			mapper.writeValue(response.getOutputStream(), buidingDTO);
			break;	
		case "update":
			buidingDTO = buildingService.update(buidingDTO);
			mapper.writeValue(response.getOutputStream(), buidingDTO);
			break;
		case "delete":
			id = Long.parseLong(request.getParameter("id"));
			buildingService.delete(id);
			break;
		case "findID":
			id = Long.parseLong(request.getParameter("id"));
			BuildingEntity buildingEntitiess = buildingService.findID(id);
			mapper.writeValue(response.getOutputStream(), buildingEntitiess);
			break;
		default:
			break;
		}
	}

}
