package com.demo.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.model.Statistics;
import com.demo.service.BusinessService;
import com.demo.service.impl.BusinessServiceImpl;

/**
 * Servlet-获取统计报表
 * @author MouseHappy
 */
public class StatisticsServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 执行业务逻辑
		BusinessService service = new BusinessServiceImpl();
		List<Statistics> statistics = service.showStatistics();
		
		request.setAttribute("statistics", statistics);// 要传送的数据
		// 跳转
		request.getRequestDispatcher("/manager/statistics.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
