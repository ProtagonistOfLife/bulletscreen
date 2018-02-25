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
		jf = new JFrame("��Ļ����");
		jb1 = new JButton("�������浯Ļ");
		jb2 = new JButton("����������Ļ");
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
				if(jb.getText().equals("�������浯Ļ")){
					Question.setShow(true);
					jb.setText("�ر����浯Ļ");
				}else{
					Question.setShow(false);
					jb.setText("�������浯Ļ");
				}
			}
		});
		
		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton jb = (JButton)e.getSource();
				if(jb.getText().equals("����������Ļ")){
					Question.setRead(true);
					jb.setText("�ر�������Ļ");
				}else{
					Question.setRead(false);
					jb.setText("����������Ļ");
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
