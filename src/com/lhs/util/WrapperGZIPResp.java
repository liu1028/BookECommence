package com.lhs.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

public class WrapperGZIPResp extends HttpServletResponseWrapper {

	private HttpServletResponse response=null; 
	
	public WrapperGZIPResp(HttpServletResponse response) {
		super(response);
		this.response=response;
	}
	
	ServletOutputStream output=null;
	public void createWriter(){
		try {
			output=response.getOutputStream();
			response.setHeader("Content-Encoding", "gzip");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void write(String s){
		ByteArrayOutputStream boutput=new ByteArrayOutputStream();
		try {
			GZIPOutputStream gzip=new GZIPOutputStream(boutput);
			gzip.write(s.getBytes("utf-8"));
			gzip.close();
			
			output.write(boutput.toByteArray());
			
/*			System.out.println(boutput.toByteArray().length);
*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
