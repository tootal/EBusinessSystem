package com.demo.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.model.User;
import com.demo.service.BusinessService;
import com.demo.service.impl.BusinessServiceImpl;

/**
 * Servlet-用户登录
 * @author MouseHappy
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取request数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// 执行业务逻辑
		BusinessService service = new BusinessServiceImpl();
		User user = service.userLogin(username, password);
		
		if(user == null){// 携带失败信息跳转至提示信息界面
			request.setAttribute("message", "用户名和密码不对");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		request.getSession().setAttribute("user", user);// 要传送的数据
		// 跳转
		request.getRequestDispatcher("/client/head.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
