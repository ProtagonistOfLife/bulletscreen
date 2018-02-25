package com.pcw.wind;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import com.sun.awt.AWTUtilities;

public class PaintWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private PaintWindow pw;
	private int lastheight = 20;

	public PaintWindow() {
		this.setUndecorated(true);
//		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setBounds(0, 0, 800, 300);
		this.setResizable(false);
		this.setLayout(null);
		AWTUtilities.setWindowOpacity(this, 0.6f);
		this.setAlwaysOnTop(true);
		
		this.setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				e.getWindow().setVisible(true);
			}
			
		});
	}
	
	public void move(String str){
		pw = this;
		int lastheighttemp = lastheight;
		JLabel jl = new JLabel(str);
		int width = 35 * str.length();
		jl.setForeground(new Color((int) (Math.random()*16777215)));
		jl.setFont(new Font("华文行楷", Font.BOLD, 30));
		jl.setBounds(800, lastheight, width,30);
		if((lastheight += 40) > 259){
			lastheight = 20;
		}
		this.add(jl);
		new Timer(150, new ActionListener() {
			int pointx = 800;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(pointx > -width){
					pointx -= 5;
					jl.setLocation(pointx, lastheighttemp);
					pw.setVisible(true);
				}else {
					Timer t = (Timer) e.getSource();
					t.stop();
				}
			}
		}).start();
	}
	
	/*public static void main(String[] args) {
//		JFrame.setDefaultLookAndFeelDecorated(true);
		PaintWindow pw = new PaintWindow();
		pw.move("大家好才是真的好");
		pw.move("牛逼啊");
	}*/
}
