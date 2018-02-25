package com.pcw.dao;

import java.util.List;

import com.pcw.bean.User;

public interface UserDao {
	void addUser(User user);
	void removeById(int id);
	void update(User user);
	User findById(int id);
	User findByNickname(String nickname);
	List<User> findByName(String name);
	List<User> findAll();
}
