package com.laptrinhjavaweb.controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.entity.BuildingEntity;

@WebServlet(urlPatterns = {"/trang-chu"})
public class HomeController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//AbstractJDBC<?> abstractJDBC = new AbstractJDBC<Object>();
		
		/*--------------------*/
		String query = "SELECT * FROM building";
		//List<BuildingEntity> building = abstractJDBC.query(query, BuildingEntity.class);
		//System.out.println(building.size());
		//request.setAttribute("building",building);
		/*--------------------*/
		
		
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/web/home.jsp");
		requestDispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(request, response);
	}
}
