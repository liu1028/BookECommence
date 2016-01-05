package com.lhs.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhs.InjectionFrawork.Injection;
import com.lhs.entity.BookChart;
import com.lhs.service.ChartService;
import com.lhs.util.UserInfo;

public class MyChartCtrl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MyChartCtrl() {
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

		if(UserInfo.getAuthentication(request) && UserInfo.getJSM(request).equals("user"))
		{
			request.getRequestDispatcher("/WEB-INF/user/mycharts.jsp").forward(request, response);
		}
		else
			response.sendRedirect(request.getContextPath()+"/login.jsp");
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
		
		PrintWriter writer=response.getWriter();
		
		if(UserInfo.getAuthentication(request) && UserInfo.getJSM(request).equals("user"))
		{
			String cmd=request.getParameter("cmd");
			if(cmd!=null && cmd.equals("addChart")){
				String bguid=request.getParameter("bguid");
				String price=request.getParameter("price");
				Integer num=1;
				
				if(bguid==null || price==null)
					return;
				Double dprice=null;
				try{
					dprice=Double.parseDouble(price);
				}catch(Exception  e){
					return;
				}
				/*************Ioc***********/
				ChartService chartService=(ChartService)injector.GetClassObject(ChartService.class);
				
				BookChart bookc=new BookChart();
				bookc.setGuid(bguid);
				bookc.setT_price(dprice);
				bookc.setNum(num);
				
				Integer result=chartService.AddChart(bookc, UserInfo.getUsername(request));
				
				if(result==0){
					writer.write("{\"IsLogged\":true,\"IsExist\":false}");					
				}else{
					writer.write("{\"IsLogged\":true,\"IsExist\":true}");										
				}
			}
		}
		else
		{
			writer.write("{\"IsLogged\":false}");
		}
		
	}

	Injection injector=null;
	public void init() throws ServletException {
		injector=Injection.getInstance();
	}

}
