package com.demo.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.model.Clothes;
import com.demo.model.Cart;
import com.demo.service.BusinessService;
import com.demo.service.impl.BusinessServiceImpl;

/**
 * Servlet-购买商品
 * @author MouseHappy
 */
public class BuyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			// 获取request数据
			String clothesId = request.getParameter("clothesId");
			
			// 执行业务逻辑
			BusinessService service = new BusinessServiceImpl();
			Clothes clothes = service.findClothes(clothesId);
			
			// 获取session数据
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			
			if(cart == null){
				cart = new Cart();
				// 存放session数据
				request.getSession().setAttribute("cart", cart);
			}
			
			// 执行业务逻辑
			service.buyClothes(cart, clothes);
			
			// 跳转
			request.getRequestDispatcher("/client/listCart.jsp").forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
			
			// 携带失败信息跳转至提示信息界面
			request.setAttribute("message", "购买失败");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
