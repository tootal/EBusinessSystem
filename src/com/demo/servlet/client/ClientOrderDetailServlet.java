package com.demo.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.service.BusinessService;
import com.demo.service.impl.BusinessServiceImpl;
import com.demo.model.Order;

/**
 * Servlet-获取订单详情
 * @author MouseHappy
 */
public class ClientOrderDetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取request数据
		String orderId = request.getParameter("orderId");
		
		// 执行业务逻辑
		BusinessService service = new BusinessServiceImpl();
		Order order = service.findOrder(orderId);
		
		request.setAttribute("order", order);// 要传送的数据
		// 跳转
		request.getRequestDispatcher("/client/clientOrderDetail.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
