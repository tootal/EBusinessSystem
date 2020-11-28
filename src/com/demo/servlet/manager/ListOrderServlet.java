package com.demo.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.model.Order;
import com.demo.service.BusinessService;
import com.demo.service.impl.BusinessServiceImpl;

/**
 * Servlet-获取订单列表
 * @author MouseHappy
 */
public class ListOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取request数据
		String state = request.getParameter("state");
		
		// 执行业务逻辑
		BusinessService service = new BusinessServiceImpl();
		List<Order> orders = service.listOrder(state);//这里需要获得该用户所有订单消息，不用只看未发货的(state==false)，在后台会区分未发货和已发货，在前台要罗列在一起
		
		request.setAttribute("orders", orders);// 要传送的数据
		// 跳转
		request.getRequestDispatcher("/manager/listOrder.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
