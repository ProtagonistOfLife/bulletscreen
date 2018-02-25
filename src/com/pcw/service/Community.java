package com.pcw.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

@WebServlet("/al/community.do")
public class Community extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JSONObject jsobject;
	private Map<String, String> map = new HashMap<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String msg = request.getParameter("msg");
		if(msg != null){
//			msg = new String(msg.getBytes("gbk"), "utf-8");
			Integer count = (int) (System.currentTimeMillis()%1000000);
			map.put(count.toString(), msg);
			
			response.getWriter().print(count);
		}
		else if(map.size() > 0){
			response.setContentType("text/html;charset=gbk");
			jsobject = JSONObject.fromObject(map);
			response.getWriter().println(jsobject.toString());
		}
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		Timer timer = new Timer();
		TimerTask  task = new TimerTask (){
             public void run() {
            	 if(map.size() > 0){
            		 int last_index = (int) (System.currentTimeMillis()%1000000 - 5000);
            		 Object[] key =  map.keySet().toArray();
            		 for(Object e : key){
            			 if (Integer.parseInt((String)e) <= last_index) {
            				 map.remove(e);
            			 }else{
            				 break;
            			 }
            		 }
            	 }
            }
		};
		timer.schedule (task, 5000L, 5000L);
	}

}
