package com.lhs.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private Integer id;
	private String username;
	private String password;
	private String sex;
	private Integer age;
	private Date birth;
	private String hobby;
	private String motto;
	private String jsm;	
	
	public User() {
		super();
	}

	public User(Integer id, String username, String password, String sex, Integer age,
			Date birth, String hobby, String motto, String jsm) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.age = age;
		this.birth = birth;
		this.hobby = hobby;
		this.motto = motto;
		this.jsm = jsm;
	}

	public Integer getId()
	{
		return id;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public String getJsm() {
		return jsm;
	}

	public void setJsm(String jsm) {
		this.jsm = jsm;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", sex=" + sex + ", age=" + age + ", birth="
				+ birth + ", hobby=" + hobby + ", motto=" + motto + ", jsm="
				+ jsm + "]";
	}
	
	
}
