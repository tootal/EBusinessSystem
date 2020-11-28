package com.demo.servlet.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.service.BusinessService;
import com.demo.service.impl.BusinessServiceImpl;
import com.demo.utils.WebUtils;
import com.demo.model.Category;

/**
 * Servlet-分类管理
 * @author MouseHappy
 */
public class CategoryServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取request数据
		String method = request.getParameter("method");
		
		if (method.equals("add")) {// 添加分类
			add(request, response);
		} else if (method.equals("listAll")) {// 获取全部分类
			listAll(request, response);
		} else {
			// 携带失败信息跳转至提示信息界面
			request.setAttribute("message", "不支持此类操作");
			request.getRequestDispatcher("/message.jsp").forward(request,
					response);
		}
	}

	/**
	 * 获取全部分类
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void listAll(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 执行业务逻辑
		BusinessService service = new BusinessServiceImpl();
		List<Category> CategoryList = service.getAllCategory();
		
		request.setAttribute("categories", CategoryList);// 要传送的数据
		// 跳转
		request.getRequestDispatcher("/manager/listCategory.jsp").forward(request, response);
	}

	/**
	 * 添加分类
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 获取request数据
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			
			if(name.length() <=0 || name.equals("") )
				throw new Exception();
			if(description.length() <=0 || description.equals("") )
				throw new Exception();
			
			// 生成分类
			Category category = new Category();
			category.setName(name);
			category.setDescription(description);
			category.setId(WebUtils.makeID());

			// 执行业务逻辑
			BusinessService service = new BusinessServiceImpl();
			service.addCategory(category);
			
			request.setAttribute("message", "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "添加失败");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
