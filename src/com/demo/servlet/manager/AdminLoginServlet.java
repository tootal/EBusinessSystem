package com.demo.servlet.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.model.Admin;
import com.demo.service.BusinessService;
import com.demo.service.impl.BusinessServiceImpl;

/**
 * Servlet-管理员登录
 * @author MouseHappy
 */
public class AdminLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取request数据
		String adminName = request.getParameter("adminName");
		String password = request.getParameter("password");
		
		// 执行业务逻辑
//		if(adminName.equalsIgnoreCase("管理员") && password.equalsIgnoreCase("admin")) {
//			// 跳转
//			request.getRequestDispatcher("/manager/manager.jsp").forward(request, response);
//		} else {
//			request.setAttribute("message", "管理员用户名和密码不对");
//			request.getRequestDispatcher("/message.jsp").forward(request, response);
//			return;
//		}
		// 执行业务逻辑
		BusinessService service = new BusinessServiceImpl();
		Admin admin = service.adminLogin(adminName, password);
				
		if(admin == null){// 携带失败信息跳转至提示信息界面
			request.setAttribute("message", "管理员用户名和密码不对");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
				
		request.getSession().setAttribute("admin", admin);// 要传送的数据
		// 跳转
		request.getRequestDispatcher("/manager/manager.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
