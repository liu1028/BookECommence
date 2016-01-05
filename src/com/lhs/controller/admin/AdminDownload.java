package com.lhs.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdminDownload extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AdminDownload() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String cmd=request.getParameter("cmd");
		System.out.println(cmd);
		if(cmd!=null)
		{
			if( cmd.equals("getUI"))
				request.getRequestDispatcher("/WEB-INF/admin/download.jsp").forward(request, response);
			else if(cmd.equals("getPic"))
			{
				PrintWriter writer=response.getWriter();
				
				String path=request.getServletContext().getRealPath("/upload");
				File file=new File(path);
				if(file.exists() && file.isDirectory())
				{
					String [] files=file.list();
					
					try{
						JSONArray jarray=new JSONArray();
						for(String f:files)
						{
							JSONObject jobj=new JSONObject();						
							jobj.put("filename", f);
							jobj.put("file", f);
							jarray.put(jobj);												
						}		
						writer.println(jarray.toString());
						
					}catch(JSONException e){
						System.out.println("JSON 解析错误");
						e.printStackTrace();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			else if(cmd.equals("down"))
			{
				String name=request.getParameter("file");
				if(name!=null && !name.equals(""))
				{
					name=new String(name.getBytes("ISO-8859-1"),"UTF-8");
					System.out.println(name);
					
					String path=request.getServletContext().getRealPath("/upload")+"\\"+name;
					System.out.println(path);
					File file=new File(path);
					if(!file.exists())
						return;
					
					response.setHeader("Content-Type", "application/octet-stream");
					response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(name, "UTF-8"));
					
					byte[] bytes=new byte[1024];
					OutputStream out=response.getOutputStream();
					InputStream in=new FileInputStream(file);
					int length=0;
					
					while((length=in.read(bytes))!=-1)
					{
						out.write(bytes,0,length);
					}
					
					in.close();
					out.flush();
					out.close();
				}				
     		}
		}

	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
