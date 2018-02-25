package com.pcw.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetVerifiedImg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int height = 40;
		int width = 80;
		StringBuffer strbuff = new StringBuffer();
		String temp = null;
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int lastindex = str.indexOf('9');
		Random random = new Random();
		int index = 0;
		OutputStream os = response.getOutputStream();
		
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D)image.getGraphics();
		HttpSession session = request.getSession();
		
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("ËÎÌו", Font.BOLD, 30));
		for(int i = 0;i < 4;i++){
			index = random.nextInt(lastindex);
			temp = str.substring(index, index+1);
			g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
			g.drawString(temp, i*20, 30);
			strbuff.append(temp);
		}
		
		for (int i = 0; i < 10; i++) {
			g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
			g.drawLine(random.nextInt(80), random.nextInt(40), random.nextInt(80), random.nextInt(40));
		}
		session.setAttribute("verified", strbuff.toString());
		ImageIO.write(image, "jpg", os);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
