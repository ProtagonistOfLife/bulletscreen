package com.pcw.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pcw.bean.User;
import com.pcw.dao.UserDao;
import com.pcw.dao.impl.UserDaoImpl;

@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		System.out.println("--------------------------------------------");
		
		String id = request.getParameter("id");
		System.out.println(id);
		String password = request.getParameter("password");
		String verify = request.getParameter("verify");
		HttpSession session = request.getSession();
		String verified = (String) session.getAttribute("verified");
		
		UserDao ud = UserDaoImpl.instance();
		System.out.println(ud);
		Integer idp = null;
		User user = null;
		if(id != null){
			try {
				idp = Integer.parseInt(id);
				System.out.println(idp);
				System.out.println(verify);
			} catch (Exception e) {}
			if(idp != null){
				user = ud.findById(idp);
			}else {
				user = ud.findByNickname(id);
			}
		}
		
		/*
		 * ��ajax���ش�����
		 * 0��û�д��󷵻�
		 * 1���˺������벻ƥ��
		 * 2����֤�����
		 */
		try {
			if(user == null){
				System.out.println("user is null");
				System.out.println(password);
				response.getWriter().print("1");
			} else if(!user.getpassword().equals(user.encodeByMD5(password))){
				System.out.println("���벻ƥ��");
				System.out.println("ԭ��������Ϊ��"+user.getpassword());
				System.out.println("���ڵ�����Ϊ��"+user.encodeByMD5(password));
				response.getWriter().print("1");
			}else if(!verified.toLowerCase().equals(verify.toLowerCase())){
				response.getWriter().print("2");
			}else{
				session.setAttribute("user", user);
				
				/*�����Ƿ����Ϊ�û���ʽ����*/
				System.out.println(user.getId());
				System.out.println(user.getpassword());
				System.out.println(id);
				System.out.println(password);
				
				session.removeAttribute("verified");
				Cookie cookie = new Cookie("id", user.getId().toString());
				cookie.setMaxAge(3600*24*14);
				response.addCookie(cookie);
				response.sendRedirect("MainPage.html");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}
