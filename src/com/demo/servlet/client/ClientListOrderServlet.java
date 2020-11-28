package com.demo.servlet.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.service.BusinessService;
import com.demo.service.impl.BusinessServiceImpl;
import com.demo.model.Order;

/**
 * Servlet-获取用户订单
 * @author MouseHappy
 */
public class ClientListOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取request数据
		String userId = request.getParameter("userId");
		
		// 执行业务逻辑
		BusinessService service = new BusinessServiceImpl();
		List<Order> orders = service.clientListOrder(userId);
		
		request.setAttribute("orders", orders);// 要传送的数据
		// 跳转
		request.getRequestDispatcher("/client/clientListOrder.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
