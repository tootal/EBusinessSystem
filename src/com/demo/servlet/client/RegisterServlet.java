package com.demo.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.service.BusinessService;
import com.demo.service.impl.BusinessServiceImpl;
import com.demo.utils.WebUtils;
import com.demo.model.User;

/**
 * Servlet-游客注册
 * @author MouseHappy
 */
public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			// 获取request数据
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String phone = request.getParameter("phone");
			String cellphone = request.getParameter("cellphone");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			
			// 生成注册用户
			User user = new User();
			user.setAddress(address);
			user.setCellphone(cellphone);
			user.setEmail(email);
			user.setId(WebUtils.makeID());
			user.setPassword(password);
			user.setPhone(phone);
			user.setUsername(username);
			
			// 执行业务逻辑
			BusinessService service = new BusinessServiceImpl();
			service.registerUser(user);
			
			request.setAttribute("message", "注册成功");// 要传送的数据
			// 跳转
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			
		}catch(Exception e){
			e.printStackTrace();
			// 携带失败信息跳转至提示信息界面
			request.setAttribute("message", "注册失败");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
