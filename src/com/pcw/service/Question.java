package com.pcw.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pcw.wind.Control;
import com.pcw.wind.PaintWindow;

public class Question extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static boolean canread = false;
	private static boolean canshow = false;
	private static PaintWindow pw;
	
	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String msg =request.getParameter("msg");
		if(canread){
			Reading.toSound(msg);
		}
		if(canshow){
			pw.move(msg);
		}
	}
	
	public static void setRead(boolean b){
		canread = b;
	}
	
	public static void setShow(boolean b){
		if(pw == null){
			pw = new PaintWindow();
		}
		pw.setVisible(b);
		canshow = b;
	}
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		new Control();
	}

}
