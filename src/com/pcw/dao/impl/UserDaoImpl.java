package com.pcw.dao.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.pcw.dao.UserDao;

public class UserDaoImpl {
	private static UserDao userdao;
	public static UserDao instance(){
		if(userdao == null){
			try {
				InputStream is = Resources.getResourceAsStream("config.xml");
				SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
				SqlSession session = factory.openSession();
				userdao = session.getMapper(UserDao.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userdao;
	}
}
