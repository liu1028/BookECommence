package com.lhs.service;

import java.util.ArrayList;
import java.util.Map;

import com.lhs.entity.User;

public interface UserService {
	//增
	Integer addUser(User user);
	
	//删
	Integer deleteUser(int id);
	Integer deleteUser(User user);
	
	//改
	Integer updateUser(User user);
	
	//查
	User findUser(int id);
	Map findUsers(User searcModel,int pageSize,int pageNum);
	ArrayList<User> findAllUsers();
	User findUser(String username);
}
