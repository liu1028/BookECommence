package com.lhs.jdbc.dbcp;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.DataSourceConnectionFactory;

public class DbcpUtil {
	private BasicDataSource bds=null;
	private DataSourceConnectionFactory dscf=null;
	private static DbcpUtil dbcpUtil=null;
	
	private DbcpUtil()
	{
		InputStream in= DbcpUtil.class.getClassLoader().getResourceAsStream("com/lhs/jdbc/dbcp/connection.properties");
		Properties property=new Properties();
		
		try {
			property.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		bds=new BasicDataSource();
		
		bds.setDriverClassName(property.getProperty("DriverName"));
		bds.setUrl(property.getProperty("Url"));
		bds.setUsername(property.getProperty("UserName"));
		bds.setPassword(property.getProperty("Password"));
		
		bds.setInitialSize(50);
		bds.setMaxActive(100);
		bds.setMaxIdle(20);
		bds.setMinIdle(10);
		
		try {
			if(in!=null)
				in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dscf=new DataSourceConnectionFactory(bds);
	}
	
	public static synchronized DbcpUtil getInstance()
	{
		if(dbcpUtil==null)
			dbcpUtil=new DbcpUtil();
		return dbcpUtil;
	}
	
	public Connection getConnection()
	{
		Connection conn=null;
		
		try {
			conn=dscf.createConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;		
	}
}
