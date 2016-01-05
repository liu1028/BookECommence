package com.lhs.InjectionFrawork;

import com.lhs.service.*;
import com.lhs.service.impl.BookServiceImpl;
import com.lhs.service.impl.ChartServiceImpl;
import com.lhs.service.impl.UserServiceImpl;
import com.lhs.dao.*;
import com.lhs.dao.impl.BookImpl;
import com.lhs.dao.impl.ChartImpl;
import com.lhs.dao.impl.UserDaoImpl;
import com.lhs.jdbc.JdbcUtil;
import com.lhs.jdbc.dbcp.DbcpUtil;



public class Injection {

	public Object GetClassObject(Class type)
	{
		
		if(type==UserService.class)
		{
			/***UserService****/
			return CreateUserService();
		}
		else if(type==UserDao.class)
		{
			return CreateUserDao();
		}
		else if(type==BookDao.class)
		{
			/***BookDao****/
			return CreateBookDao();
		}
		else if(type==BookService.class)
		{
			return CreateBookService();
		}
		else if(type==ChartDao.class)
		{
			/***ChartDao****/
			return CreateChartDao();
		}
		else if(type==ChartService.class)
		{
			return CreateChartService();
		}
		
		return null;
	}

	private ChartDao CreateChartDao()
	{
		ChartImpl charti=new ChartImpl();
		charti.setJdbcUtil(CreateJdbcUtil());
		return charti;
	}
	
	private ChartService CreateChartService()
	{
		ChartServiceImpl chartimpl=new ChartServiceImpl();
		chartimpl.setChartDao(CreateChartDao());
		return chartimpl;
	}
	
	private BookDao CreateBookDao()
	{
		BookImpl book_i=new BookImpl();
		book_i.setJdbcUtil(CreateJdbcUtil());
		return book_i;
	}
	
	private BookService CreateBookService()
	{
		BookServiceImpl book_s_i=new BookServiceImpl();
		book_s_i.setBookDao(CreateBookDao());
		return book_s_i;
	}
	
	private UserService CreateUserService() 
	{
		UserServiceImpl user_s=new UserServiceImpl();
		user_s.setUserDao(CreateUserDao());
		return user_s;
	}
	
	private UserDao CreateUserDao() {
		UserDaoImpl user_d=new UserDaoImpl();
		user_d.setJdbcUtil(CreateJdbcUtil());
		return user_d;
	}

	private JdbcUtil CreateJdbcUtil() {
		JdbcUtil jdbc=new JdbcUtil();
		jdbc.setDbcpUtil(DbcpUtil.getInstance());
		return jdbc;
	}


	/****单例模式*****/
	private static Injection injector=null;
	private Injection()
	{
		
	}
	
	public static synchronized Injection getInstance()
	{
		if(injector==null)
			injector=new Injection();
		return injector;
	}
	
	
}
