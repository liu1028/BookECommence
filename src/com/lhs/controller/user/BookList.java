package com.lhs.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhs.InjectionFrawork.Injection;
import com.lhs.entity.BookDetail;
import com.lhs.service.BookService;
import com.lhs.util.GetUTF8String;
import com.lhs.util.WrapperGZIPResp;

public class BookList extends HttpServlet {

	
	/**
	 * Constructor of the object.
	 */
	public BookList() {
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
		
		if(cmd!=null && !cmd.equals("")){
			
			String category=cmd;
			
			//String category=GetUTF8String.GetString(cmd);
			request.setAttribute("category", category);
			
			/*******Ioc******/
			BookService bookService=(BookService)injector.GetClassObject(BookService.class);

			List<BookDetail> list=bookService.GetBooks(category, 1, 6);
			request.setAttribute("detail", list);
		}
		
		request.getRequestDispatcher("/WEB-INF/user/booklist.jsp").forward(request, response);
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
		if(cmd!=null && cmd.equals("moreblist"))
		{
			String curpage=request.getParameter("pages");
			String category=request.getParameter("category");
			
			/*******Ioc******/
			BookService bookService=(BookService)injector.GetClassObject(BookService.class);

			List<BookDetail> list=bookService.GetBooks(category,Integer.parseInt(curpage) , 6);
			
			if(list.size()>0){
				
				StringBuilder sb=new StringBuilder();
			    int num = 6* (Integer.parseInt(curpage) - 1) + 1,i=0;
			    
				for(BookDetail book:list){
					String bookname =book.getBookname();
	                bookname = (bookname.length() > 17) ? bookname.substring(0, 17) : bookname;
	                String publisher =book.getPublisher();
	                publisher = publisher.length() > 15 ? publisher.substring(0, 15) : publisher;
	                String author = book.getAuthor();
	                author = (author.length()> 17) ? author.substring(0, 17) : author;
	
	                sb.append("<li class=\"booklist_item\">");
	                sb.append("<div class=\"img left\">");
	                sb.append("<img src=\"" +request.getContextPath()+ book.getImagePath() + "\" alt=\"该图片逃跑啦!\" />");
	                sb.append("</div>");
	                sb.append("<div class=\"con left\">");
	                sb.append("<div class=\"bname\"><a href=\"user_bookdetail.aspx?id=" + book.getGuid() + "\">" + bookname + "</a></div>");
	                sb.append("<div class=\"l\">作者："+author+"</div>");
	                sb.append("<div class=\"l\">出版社："+publisher+"</div>");
	                sb.append("<div class=\"l\">出版日期："+book.getPubdate()+"</div>");
	                sb.append("<div class=\"l\">人气指数："+book.getAccess()+"</div>");
	                sb.append("<div class=\"l\">价格："+book.getPrice()+"</div>");               
	                sb.append("</div>");
	                sb.append("<div class=\"num right\">"+(num+i) +"</div>");
	                sb.append("</li>");
	                i++;
				}
				
                String json="{\"hasMore\":true,\"data\":\""+sb.toString().replace("\"","@")+"\"}";
                response.getWriter().write(json);

			}else{
				response.getWriter().write("{\"hasMore\":false}");
			}
		}
	}

	Injection injector=null;
	public void init() throws ServletException {
		injector=Injection.getInstance();
	}

}
