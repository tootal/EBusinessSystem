package com.demo.servlet.manager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.demo.model.Clothes;
import com.demo.model.Category;
import com.demo.model.Page;
import com.demo.service.BusinessService;
import com.demo.service.impl.BusinessServiceImpl;
import com.demo.utils.WebUtils;

/**
 * Servlet-商品管理
 * @author MouseHappy
 */
public class ClothesServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取request数据
		String method = request.getParameter("method");
		
		if (method.equalsIgnoreCase("addUI")) {// 添加成功后跳转
			addUI(request, response);
		}
		if (method.equalsIgnoreCase("add")) {// 添加
			add(request, response);
		}
		if (method.equalsIgnoreCase("modify")) {// 修改
			modify(request, response);
		}
		if (method.equalsIgnoreCase("delete")) {// 删除
			delete(request, response);
		}
		if(method.equalsIgnoreCase("list")){// 获取全部商品
			list(request, response);
		}
	}

	/**
	 * 获取全部商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取request数据
		String pageNum = request.getParameter("pageNum");
		
		// 执行业务逻辑
		BusinessService service = new BusinessServiceImpl();
		Page page = service.getClothesPageData(pageNum);
		
		request.setAttribute("page", page);// 要传送的数据
		// 跳转
		request.getRequestDispatcher("/manager/listClothes.jsp").forward(request, response);
	}

	/**
	 * 添加商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//
			Clothes clothes = doupLoad(request);
			
			if(clothes.getName().equals("") || clothes.getName().length() <= 0)
				throw new Exception();
			if(clothes.getBrand().equals("") || clothes.getBrand().length() <= 0)
				throw new Exception();
			if(clothes.getPrice() <= 0)
				throw new Exception();
			
			// 生成商品ID
			clothes.setId(WebUtils.makeID());
			
			// 执行业务逻辑
			BusinessService service = new BusinessServiceImpl();
			service.addClothes(clothes);
			
			request.setAttribute("message", "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "添加失败");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	
	/**
	 * 修改商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			Clothes clothes = doupLoad(request);
			//System.out.println(clothes.getId());
			if(clothes.getName().equals("") || clothes.getName().length() <= 0)
				throw new Exception();
			if(clothes.getBrand().equals("") || clothes.getBrand().length() <= 0)
				throw new Exception();
			if(clothes.getPrice() <= 0)
				throw new Exception();
			
			
			// 执行业务逻辑
			BusinessService service = new BusinessServiceImpl();
			service.modifyClothes(clothes);
			
			request.setAttribute("message", "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "修改失败");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
	
	/**
	 * 删除商品
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 获取request数据
			String id = request.getParameter("id");
			
			// 执行业务逻辑
			BusinessService service = new BusinessServiceImpl();
			service.deleteClothes(id);
			
			request.setAttribute("message", "删除成功");
			// 跳转
			request.getRequestDispatcher("/manager/listClothes.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "删除失败");
		}
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	/**
	 * 保存商品图片，并将路径添加到商品对象中
	 * @param request
	 * @return
	 */
	private Clothes doupLoad(HttpServletRequest request) {
		//把上传的图片保存到images目录中，并把request中的请求参数封装到Book中
		Clothes clothes = new Clothes();
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();// 文件操作工具DiskFileItemFactory
			ServletFileUpload upload = new ServletFileUpload(factory);// 文件操作工具ServletFileUpload
			List<FileItem> list = upload.parseRequest(request);
			
			for(FileItem item : list){
				if(item.isFormField()){
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					BeanUtils.setProperty(clothes, name, value);
				}else{
					String filename = item.getName();
					String savefilename = makeFileName(filename);//得到保存在硬盘的文件名
					String savepath= this.getServletContext().getRealPath("/images");
					InputStream in = item.getInputStream();
					FileOutputStream out = new FileOutputStream(savepath + "\\" + savefilename);
					
					// 转为byte数据写入
					int len = 0;
					byte buffer[] = new byte[1024];
					while((len = in.read(buffer)) > 0){
						out.write(buffer, 0, len);
					}
					
					in.close();
					out.close();
					item.delete();
					clothes.setImage(savefilename);// 记录文件路径
				}
			}
			return clothes;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 生成保存在image文件夹下的商品图片名称
	 * @param fileName
	 * @return
	 */
	public String makeFileName(String fileName){
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);//lastIndexOf("\\.")这样写不行
		return UUID.randomUUID().toString() + "." + ext;
	}

	/**
	 * 添加成功跳转
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 执行业务逻辑
		BusinessService service = new BusinessServiceImpl();
		List<Category> category = service.getAllCategory();
		
		request.setAttribute("categories", category);// 要传送的数据
		// 跳转
		request.getRequestDispatcher("/manager/addClothes.jsp").forward(request,
				response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
