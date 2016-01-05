package com.lhs.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lhs.InjectionFrawork.Injection;
import com.lhs.entity.User;
import com.lhs.formdomain.RegisterForm;
import com.lhs.formvaliation.RegisterFormValidator;
import com.lhs.service.UserService;
import com.lhs.util.WrapperGZIPResp;

public class UserManageCtrl extends HttpServlet {
	/**
	 * Constructor of the object.
	 */
	public UserManageCtrl() {
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
		
		if(cmd!=null && !cmd.equals(""))
		{
			if(cmd.equals("getUI"))
				request.getRequestDispatcher("/WEB-INF/admin/user.jsp").forward(request, response);
			else if(cmd.equals("deletion"))
			{
				String id=request.getParameter("unique");
				if(id!=null && !id.equals(""))
				{
					Integer uid=null;
					try{
						uid=Integer.parseInt(id);
					}catch(Exception e){
						return;
					}
					
					/*******Ioc******/
					UserService userService=(UserService)injector.GetClassObject(UserService.class);

					PrintWriter writer=response.getWriter();
					if(userService.deleteUser(uid)>0)					
						writer.write("{\"status\":true}");
					else writer.write("{\"status\":false}");
					writer.flush();	
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

		String cmd=request.getParameter("cmd");
		
		if(cmd!=null && !cmd.equals(""))
		{
			if(cmd.equals("getPagination"))
			{
				String pagesize=request.getParameter("pagesize");
				String pagenum=request.getParameter("pagenum");
				String username=request.getParameter("username");
				String sex=request.getParameter("sex");
				
				//*******validate querystring******
				Integer pSize=null;
				Integer pNum=null;
				try{
					pSize=Integer.parseInt(pagesize);
					pNum=Integer.parseInt(pagenum);
				}catch(NumberFormatException e){
					request.getRequestDispatcher("/WEB-INF/admin/index.jsp");
					return;
				}
				
				
				/*******Ioc******/
				UserService userService=(UserService)injector.GetClassObject(UserService.class);

				//use service
				User user=new User();
				user.setUsername(username);
				user.setSex(sex);
				Map map=userService.findUsers(user,pSize, pNum);
				
				JSONArray arr=new JSONArray();
				List<User> users=null;
				
				if(map!=null)
				{
					users=(ArrayList)map.get("users");
					
					for(User u:users)
					{
						arr.put(new JSONObject(u));
					}
					
					//serialize to json
					JSONObject o=new JSONObject();
					try {
						o.put("rows",arr);
						o.put("total",((Integer)map.get("total")).toString());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
/*					
					PrintWriter writer=response.getWriter();
					writer.write(o.toString());
					writer.flush();
					writer.close();
*/				
					WrapperGZIPResp resp=(WrapperGZIPResp)response;
					resp.createWriter();
					resp.write(o.toString());
				}
			}
			else if(cmd.equals("edit"))
			{
				String id=request.getParameter("id");
				String username=request.getParameter("username");
				String sex=request.getParameter("sex");
				String birth=request.getParameter("birth");
				String age=request.getParameter("age");
				String motto=request.getParameter("motto");
				
				RegisterForm form=new RegisterForm(username, "", sex, age, birth, "", motto);
				
				List<String> errors= new RegisterFormValidator(form).getErrors();
				if(errors.size()>0)
					return;
				
				Integer uid=null;
				try{
					uid=Integer.parseInt(id);
				}catch(Exception e){
					return;
				}
				
				//Integer id, String username, String password, String sex, Integer age,
				//Date birth, String hobby, String motto, String jsm
				Date d=null;
				try {
					d = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				User user=new User(uid, username, "", sex, Integer.parseInt(age),d, "", motto, "");
				
				/*******Ioc******/
				UserService userService=(UserService)injector.GetClassObject(UserService.class);

				PrintWriter writer=response.getWriter();
				if(userService.updateUser(user)>0)
					writer.write("{\"status\":true}");
				else 
					writer.write("{\"status\":false}");
			}
		}
	
	}

	Injection injector=null;
	public void init() throws ServletException {
		injector=Injection.getInstance();
	}

}
