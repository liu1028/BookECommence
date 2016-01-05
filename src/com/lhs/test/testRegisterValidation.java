package com.lhs.test;

import static org.junit.Assert.*;

import java.net.URLEncoder;
import java.util.List;

import org.junit.Test;

import com.lhs.formdomain.RegisterForm;
import com.lhs.formvaliation.RegisterFormValidator;

public class testRegisterValidation {

	@Test
	public void test() {
		RegisterForm regForm=new RegisterForm("username", "password", "sex", "23", "1990-4-89", "", "");
		
		List<String> elist=new RegisterFormValidator(regForm).getErrors();
		
		for(String s:elist){
			System.out.println(s);
			
		}
		
		String str="username=6af如果他废物&password=34&sex=0&age=4&birthday=1994-10-25&hobby="
		+ "打篮球&hobby=其&motto=范围发ew违法cewf测完饭";
		String s=URLEncoder.encode("如果他废物");
		System.out.println(s);
	}

}
