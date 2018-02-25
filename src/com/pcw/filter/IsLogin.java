package com.pcw.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pcw.bean.User;
import com.pcw.dao.UserDao;
import com.pcw.dao.impl.UserDaoImpl;

@WebFilter("/al/*")
public class IsLogin implements Filter {

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hr = (HttpServletRequest)request;
		HttpServletResponse ponse = (HttpServletResponse)response;
		HttpSession session = hr.getSession(false);
		if(session == null){
			Cookie cook = null;
			for(Cookie c : hr.getCookies()){
				if(c.getName().equals("id")){
					cook = c;
				}
			}
			if(cook == null){
				System.out.println("cook is null");
				if(hr.getHeader("X-Requested-With").equals("XMLHttpRequest"))
					ponse.getWriter().print("/bulletscreen/login.html");
				else
					ponse.sendRedirect("/bulletscreen/login.html");
				return ;
			}else{
				UserDao ud = UserDaoImpl.instance();
				User user = ud.findById(Integer.parseInt(cook.getValue()));
				session = hr.getSession();
				session.setAttribute("user", user);
				System.out.println(user.getName() + user.getpassword());
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {}

}
