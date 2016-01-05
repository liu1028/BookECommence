package com.lhs.util;

import java.security.MessageDigest;

import sun.misc.*;

public class MD5Converter {
	
	@SuppressWarnings("restriction")
	public static String getString(String src_str)
	{		
		String des_str=null;
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			
			BASE64Encoder 	base64en=new BASE64Encoder();

			des_str=base64en.encode(md.digest(src_str.getBytes("UTF-8")));
						
			return des_str;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
