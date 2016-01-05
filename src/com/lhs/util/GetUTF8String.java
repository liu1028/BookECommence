package com.lhs.util;

import java.io.UnsupportedEncodingException;

public class GetUTF8String {

	public static String GetString(String src) {
		
		try {
			src=new String(src.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
		
		return src;
	}

}
