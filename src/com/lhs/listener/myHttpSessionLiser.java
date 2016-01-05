package com.lhs.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class myHttpSessionLiser implements HttpSessionListener,ServletContextListener {

	private Properties property=null;
	private File file=null;
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context=sce.getServletContext();
		context.setAttribute("onlineCount", new AtomicInteger());
		
		String path=context.getRealPath("/visitedCount.txt");
	    file=new File(path);
		if(!file.exists())
		{
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
		property=new Properties();
		try {
			property.load(new FileReader(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sessionCreated(HttpSessionEvent hse) {
		Boolean bo=false;
		HttpSession session=hse.getSession();
		ServletContext context=session.getServletContext();
		
		session.setAttribute("IsAuthenticated", bo);
		
		
		//calculate visit count
		synchronized(file)  {
			OutputStream out=null;
			try {
			    out=new FileOutputStream(file);
				String cnt=property.getProperty("count");
				if(cnt==null)
				{
					property.setProperty("count", "1");
					context.setAttribute("visitedCnt", "1");
				}
				else
				{
					Integer count=Integer.parseInt(cnt);
					count++;
					property.setProperty("count", count.toString());
					context.setAttribute("visitedCnt", count.toString());
				}
				property.store(out, "");
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(out!=null){
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		
		//online count add
		AtomicInteger count=(AtomicInteger)context.getAttribute("onlineCount");
		int cnt=count.incrementAndGet();
		System.out.println(cnt);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
	
		//online count remove
		AtomicInteger count=(AtomicInteger) hse.getSession().getServletContext().getAttribute("onlineCount");
		int cnt=count.decrementAndGet();
	}


}
