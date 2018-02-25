package com.pcw.wind;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.pcw.service.Question;

public class Control {
	private JFrame jf;
	private JButton jb1,jb2;
	
	public Control() {
		init();
		addLis();
	}
	
	public void init(){
		jf = new JFrame("µ¯Ä»¿ØÖÆ");
		jb1 = new JButton("¿ªÆô×ÀÃæµ¯Ä»");
		jb2 = new JButton("¿ªÆôÓïÒôµ¯Ä»");
		jf.setLayout(new FlowLayout());
		jf.setBounds(400, 300, 400, 300);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(jb1);
		jf.add(jb2);
		jf.setResizable(false);
		jf.setVisible(true);
	}
	
	public void addLis(){
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton jb = (JButton)e.getSource();
				if(jb.getText().equals("¿ªÆô×ÀÃæµ¯Ä»")){
					Question.setShow(true);
					jb.setText("¹Ø±Õ×ÀÃæµ¯Ä»");
				}else{
					Question.setShow(false);
					jb.setText("¿ªÆô×ÀÃæµ¯Ä»");
				}
			}
		});
		
		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton jb = (JButton)e.getSource();
				if(jb.getText().equals("¿ªÆôÓïÒôµ¯Ä»")){
					Question.setRead(true);
					jb.setText("¹Ø±ÕÓïÒôµ¯Ä»");
				}else{
					Question.setRead(false);
					jb.setText("¿ªÆôÓïÒôµ¯Ä»");
				}
			}
		});
		jf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				e.getWindow().setVisible(false);
			}
		});
	}
}
