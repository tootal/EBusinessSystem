package com.demo.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.service.BusinessService;
import com.demo.service.impl.BusinessServiceImpl;
import com.demo.model.Cart;
import com.demo.model.User;

/**
 * Servlet-进入购物车
 * @author MouseHappy
 */
public class OrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			// 获取session数据
			User user = (User) request.getSession().getAttribute("user");
			
			if(user == null){// 携带失败信息跳转至提示信息界面
				request.setAttribute("message", "对不起，请先登录");
				request.getRequestDispatcher("/message.jsp").forward(request, response);
				return;
			}
			
			// 获取session数据
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			
			// 执行业务逻辑
			BusinessService service = new BusinessServiceImpl();
			service.createOrder(cart, user);
			
			// 携带成功信息跳转至提示信息界面
			request.setAttribute("message", "订单已生成");
			request.getSession().removeAttribute("cart");//点购买后，如果不清空购物车，前端点击查看购物车又出现了
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			// 携带失败信息跳转至提示信息界面
			request.setAttribute("message", "订单生成失败");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
