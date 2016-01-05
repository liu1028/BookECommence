package com.lhs.formvaliation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.lhs.formdomain.RegisterForm;

public class RegisterFormValidator {

	private RegisterForm regform=null;
	
	public RegisterFormValidator() {
	}
	
	public RegisterFormValidator(RegisterForm r)
	{
		regform=r;
	}

	public List<String> getErrors()
	{
		List<String> elist=new ArrayList<String>();
		
		try{
			Integer.parseInt(regform.getAge());
		}catch(NumberFormatException e){
			elist.add("年龄必须为数字！");
		}
		
		try {
			new SimpleDateFormat("yyyy-MM-dd").parse(regform.getBirth());
		} catch (ParseException e) {
			elist.add("日期必须的格式为：XXXX-XX-XX!");
		}
		
		return elist;
	}
}
