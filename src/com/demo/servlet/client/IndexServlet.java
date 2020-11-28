package com.demo.servlet.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.model.Category;
import com.demo.model.Page;
import com.demo.service.BusinessService;
import com.demo.service.impl.BusinessServiceImpl;

/**
 * Servlet-前台首页
 * @author MouseHappy
 */
public class IndexServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取request数据
		String method = request.getParameter("method");
		
		if(method.equalsIgnoreCase("getAll")){// 获取全部商品
			getAll(request, response);
		}else if(method.equalsIgnoreCase("listClothesWithCategory")){// 获取某分类下的商品
			listClothesWithCategory(request, response);
		}
	}

	/**
	 * 获取全部商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取request数据
		String pageNum = request.getParameter("pageNum");
		
		// 执行业务逻辑
		BusinessService service = new BusinessServiceImpl();
		List<Category> categories = service.getAllCategory();// 获取全部分类
		Page page = service.getClothesPageData(pageNum);// 获取商品分页数据
		
		// 要传送的数据
		request.setAttribute("categories", categories);
		request.setAttribute("page", page);
		// 跳转
		request.getRequestDispatcher("/client/body.jsp").forward(request, response);
	}
	
	/**
	 * 获取某分类下的商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listClothesWithCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// 获取request数据
		String categoryId = request.getParameter("categoryId");
		String pageNum = request.getParameter("pageNum");
		
		// 执行业务逻辑
		BusinessService service = new BusinessServiceImpl();
		List<Category> categories = service.getAllCategory();
		Page page = service.getClothesPageData(pageNum, categoryId);
				
		// 要传送的数据
		request.setAttribute("categories", categories);
		request.setAttribute("page", page);
		// 跳转
		request.getRequestDispatcher("/client/body.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
