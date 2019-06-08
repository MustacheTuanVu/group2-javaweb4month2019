package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.laptrinhjavaweb.entity.BuildingEntity;

@WebServlet(urlPatterns = { "/admin-home" })
public class HomeController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//AbstractJDBC<?> abstractJDBC = new AbstractJDBC<Object>();

		/*--------------------*/
		String query = "SELECT * FROM building";
		//List<BuildingEntity> building = abstractJDBC.query(query, BuildingEntity.class);
		//request.setAttribute("building", building);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/home.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}