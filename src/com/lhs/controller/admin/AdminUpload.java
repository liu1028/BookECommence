package com.lhs.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class AdminUpload extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AdminUpload() {
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
		if(cmd!=null && cmd.equals("getUI"))
		{
			request.getRequestDispatcher("/WEB-INF/admin/upload.jsp").forward(request, response);
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
		
		String path=request.getServletContext().getRealPath("/upload");
		File file=new File(path);
		if(!file.exists() || !file.isDirectory())
			file.mkdir();
		
		DiskFileItemFactory factory=new DiskFileItemFactory();
		
		ServletFileUpload sfu=new ServletFileUpload(factory);
		
		sfu.setHeaderEncoding("utf-8");
		try {
			List<FileItem>  filelist=sfu.parseRequest(request);
			for(FileItem f:filelist)
			{
				if(!f.isFormField())
				{
					
					String fname=f.getName();
					if(fname.indexOf("\\")>-1)
						fname=fname.substring(fname.lastIndexOf("\\")+1);
					fname=fname.replaceAll("[\\/:*\"?<>|]", "_");
					
					String fpath=path+"\\"+fname;
					
					InputStream in=f.getInputStream();
					OutputStream out=new FileOutputStream(fpath);

					byte [] b=new byte[1024];
					int len=0;
					while((len=in.read(b))!=-1)
					{
						out.write(b,0,len);
					}
					
					out.close();
					in.close();
					f.delete();
					
				
					PrintWriter writer=response.getWriter();
					writer.write("{\"status\":true}");
					
				}
			}
		} catch (FileUploadException e) {
			PrintWriter writer=response.getWriter();
			writer.write("{\"status\":false}");

		}

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
