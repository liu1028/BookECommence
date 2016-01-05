package com.lhs.formdomain;

import java.util.Date;

public class RegisterForm {
	
	private String username;
	private String password;
	private String sex;
	private String age;
	private String birth;
	private String hobby;
	private String motto;
	
	public RegisterForm() {
		super();
	}

	public RegisterForm(String username, String password, String sex,
			String age, String birth, String hobby, String motto) {
		super();
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.age = age;
		this.birth = birth;
		this.hobby = hobby;
		this.motto = motto;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
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

	
}
