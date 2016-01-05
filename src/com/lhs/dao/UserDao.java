package com.lhs.dao;

import java.util.ArrayList;

import com.lhs.entity.User;

public interface UserDao {
	//增
	Integer addUser(User user);
	
	//删
	Integer deleteUser(int id);
	Integer deleteUser(User user);
	
	//改
	Integer updateUser(User user);
	
	//查
	User findUser(int id);
	User findUser(String username);
	ArrayList<User> findUsers(User searchModel,int pageSize,int pageNum);
	ArrayList<User> findAllUsers();
	Integer getUserNum();
	Integer getUserNumbyFilter(User searchModel);
}
