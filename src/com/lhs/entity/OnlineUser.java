package com.lhs.entity;

import java.io.Serializable;
import java.util.Date;

public class OnlineUser implements Serializable {
	private int id;
	private String username;
	private Date online_time;
	private String ip;
	
	
	public OnlineUser() {
		super();
	}

	public OnlineUser(int id, String username, Date online_time, String ip) {
		super();
		this.id = id;
		this.username = username;
		this.online_time = online_time;
		this.ip = ip;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getOnline_time() {
		return online_time;
	}

	public void setOnline_time(Date online_time) {
		this.online_time = online_time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
