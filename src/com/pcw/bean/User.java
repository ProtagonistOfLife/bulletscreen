package com.pcw.bean;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class User {
	private Integer id;
	private String nickname;
	private String name;
	private String password;
	private int rank;
	
	public User(){}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getpassword() {
		return password;
	}
	
	public void setpassword(String password){
		this.password = password;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public String encodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
//		确定计算方法
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base = new BASE64Encoder();
//		加密后的字符串
		String newstr = base.encode(md5.digest(str.getBytes("gbk")));
		return newstr;
	}
//	加密测试主函数select dbms_metadata.get_ddl('TABLE','BULLET_USER') from dual; 
	/*public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		User user = new User();
		System.out.println(user.encodeByMD5("123456"));
		System.out.println(user.encodeByMD5("renshengzhujue"));
		System.out.println(user.encodeByMD5("pengciwen"));
	}*/
}
