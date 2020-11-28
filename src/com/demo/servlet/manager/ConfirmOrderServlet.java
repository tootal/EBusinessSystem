package com.demo.servlet.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.service.BusinessService;
import com.demo.service.impl.BusinessServiceImpl;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Servlet-确认发货
 * @author MouseHappy
 */
public class ConfirmOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			// 获取request数据
			String orderId = request.getParameter("orderId");
			String orderEmail = request.getParameter("orderEmail");
			
			// 执行业务逻辑
			BusinessService service = new BusinessServiceImpl();
			service.confirmOrder(orderId);
			
	        Properties properties = new Properties();
	        properties.put("mail.transport.protocol", "smtp");// 连接协议
	        properties.put("mail.smtp.host", "smtp.163.com");// 邮箱服务器主机名
	        properties.put("mail.smtp.port", 465);// 端口号
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.ssl.enable", "true");//是否使用ssl安全连接
	        properties.put("mail.debug", "true");//是否在控制台显示相关信息

	        //获取会话对象
	        Session session = Session.getInstance(properties);
	        //获取邮件对象
	        Message message = new MimeMessage(session);

	        
	        // 设置发件人邮箱地址
	        message.setFrom(new InternetAddress("mousehappy33@163.com"));
	        // 设置收件人邮箱地址
	        message.setRecipient(Message.RecipientType.TO, new InternetAddress(orderEmail));

	        // 设置邮件标题
	        message.setSubject("通知商品发货");
	        // 设置邮件内容
	        message.setText("您购买的商品已发货，请注意查收");

	        //获取邮差对象
	        Transport transport = session.getTransport();
	        //连接自己的邮箱账户，第二个参数是授权码
	        transport.connect("mousehappy33@163.com", "UBSTYFEEIAOJZVRL");
	        //发送邮件
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	        
			
			// 携带成功信息跳转至提示信息界面
			request.setAttribute("message", "订单已置为发货状态，请及时配送");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		} catch(Exception e){
			e.printStackTrace();
			// 携带失败信息跳转至提示信息界面
			request.setAttribute("message", "确认失败");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
