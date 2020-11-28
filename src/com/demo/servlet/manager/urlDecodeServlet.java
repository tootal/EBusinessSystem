package com.demo.servlet.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class urlDecodeServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = (String)request.getParameter("id");
		String name = request.getParameter("name");
		String brand = (String)request.getParameter("brand");
		String price = (String)request.getParameter("price");
		String image = (String)request.getParameter("image");
		String description = (String)request.getParameter("description");
		
		request.setAttribute("name",name);
		request.setAttribute("brand",brand);
		request.setAttribute("image",image);
		request.setAttribute("description",description);
		
		// Ìø×ª
		request.getRequestDispatcher("/manager/modifyClothes.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
