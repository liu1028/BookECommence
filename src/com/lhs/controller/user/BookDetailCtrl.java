package com.lhs.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhs.InjectionFrawork.Injection;
import com.lhs.entity.BookDetail;
import com.lhs.service.BookService;
import com.lhs.service.UserService;

public class BookDetailCtrl extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public BookDetailCtrl() {
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
		String id=request.getParameter("id");
		if(id!=null && !id.equals("")){
			
			/*******Ioc******/
			BookService bookService=(BookService)injector.GetClassObject(BookService.class);

			BookDetail bookd=bookService.GetBook(id);
			request.setAttribute("book", bookd);
			
		}
		
		request.getRequestDispatcher("/WEB-INF/user/bookdetail.jsp").forward(request, response);
		
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

		this.doPost(request, response);
	}

	Injection injector=null;
	public void init() throws ServletException {
		injector=Injection.getInstance();
	}

}
