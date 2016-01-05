package com.lhs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhs.InjectionFrawork.Injection;
import com.lhs.entity.User;
import com.lhs.formdomain.RegisterForm;
import com.lhs.formvaliation.RegisterFormValidator;
import com.lhs.service.UserService;
import com.lhs.util.MD5Converter;

public class RegisterCtrl extends HttpServlet {

	private static final long serialVersionUID = 4082482768517541751L;

	/**
	 * Constructor of the object.
	 */
	public RegisterCtrl() {
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
		PrintWriter writer=response.getWriter();
		
		if(cmd.equals("IsUsernameSame"))
		{
			String username=request.getParameter("uid");
			username=new String(username.getBytes("ISO-8859-1"),"utf-8");

			/*******Ioc******/
			UserService userService=(UserService)injector.GetClassObject(UserService.class);

			if(userService.findUser(username)!=null)
				writer.println("{\"status\":true}");
			else
				writer.println("{\"status\":false}");
			
			writer.flush();
			writer.close();
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
		/*****fetch data from querystring*****/
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String sex=request.getParameter("sex");
		String age=request.getParameter("age");
		String birthday=request.getParameter("birthday");
		String [] hobbyArray= request.getParameterValues("hobby");
		String hobbys="";
		if(hobbyArray!=null)
		{
			for(int i=0;i<hobbyArray.length;i++)
				if(i==hobbyArray.length-1)
					hobbys+=hobbyArray[i];
				else hobbys+=hobbyArray[i]+",";
		}
		String motto=request.getParameter("motto");
		
		/****encapluse data to form object*******/
		RegisterForm regForm=new RegisterForm(username, password, sex, age, birthday, hobbys, motto);
		
		/*****get errors from form data******/
		List<String> elist=new RegisterFormValidator(regForm).getErrors();		
		if(elist.size()>0)
		{
			response.sendRedirect(request.getContextPath()+"/register.jsp");
			return;
		}
		
		Integer age_i=Integer.parseInt(age);
		Date date=null;
		try {
			 date=new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		password=MD5Converter.getString(password); //convent nak content to MD5 string
		
		User user=new User(null, username, password, sex, age_i, date, hobbys, motto, "user");
		
		/*******Ioc******/
		UserService userService=(UserService)injector.GetClassObject(UserService.class);

		if(userService.addUser(user)>0)
		{
			response.setHeader("refresh", "4;url="+request.getContextPath()+"/login.jsp");
			request.setAttribute("message", "恭喜您，注册成功！4秒后，自动转到登录页面！");
			request.getRequestDispatcher("/WEB-INF/message.jsp").forward(request, response);
		}
		else 
		{
			response.sendRedirect("register.jsp");
		}
		/*System.out.println("uid:"+new String(username.getBytes("ISO-8859-1"),"utf-8")+"\npwd:"+(password==""?"空":password)+"\nsex:"+sex+"\nage:"+age
				+"\nbirthday:"+birthday+"\nhobbys:"+hobbys+"\nmotto:"+motto);*/
	}

	Injection injector=null;
	public void init() throws ServletException {
		injector=Injection.getInstance();
	}

}
