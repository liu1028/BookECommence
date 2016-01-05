package com.lhs.dao;

import java.util.ArrayList;

import com.lhs.entity.OnlineUser;

public interface OnlineUserDao {
	
	//增
	void addOnlineUser(OnlineUser OnlineUser);
	
	//删
	void deleteOnlineUser(int id);
	void deleteOnlineUser(OnlineUser OnlineUser);
	
	//改
	void updateOnlineUser(OnlineUser OnlineUser);
	
	//查
	OnlineUser findOnlineUser(int id);
	ArrayList<OnlineUser> findOnlineUsers(OnlineUser searcModel,int pageSize,int pageNum);
	ArrayList<OnlineUser> findAllOnlineUsers();

}
