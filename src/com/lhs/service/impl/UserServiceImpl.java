package com.lhs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.lhs.dao.UserDao;
import com.lhs.entity.User;
import com.lhs.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao=null;
	public void setUserDao(UserDao u)
	{
		userDao=u;
	}
	
	@Override
	public Integer addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public Integer deleteUser(int id) {
		return userDao.deleteUser(id);		 
	}

	@Override
	public Integer deleteUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public User findUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map findUsers(User searchModel, int pageSize, int pageNum) {
		Map map=new HashMap();
		
		Integer total=userDao.getUserNumbyFilter(searchModel);
		ArrayList<User> users=userDao.findUsers(searchModel, pageSize, pageNum);
		
		map.put("total",total);
		map.put("users",users);
		
		return map;
	}

	@Override
	public ArrayList<User> findAllUsers() {
		// TODO Auto-generated method stub
		return userDao.findAllUsers();
	}

	@Override
	public User findUser(String username) {
		return userDao.findUser(username);
	}

}
