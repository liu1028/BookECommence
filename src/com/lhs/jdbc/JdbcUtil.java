package com.lhs.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lhs.exception.DaoException;
import com.lhs.jdbc.dbcp.DbcpUtil;

public class JdbcUtil {
	
	
	private  Connection conn=null;
	private  Statement stmt=null;
	private  ResultSet rs=null;
	private  PreparedStatement pstmt=null;
	
	
	private  DbcpUtil dbcpUtil=null;
	public void setDbcpUtil(DbcpUtil d)
	{
		dbcpUtil=d;
	}
	
		
	public  void CreateConn()
	{
		try {
			conn=dbcpUtil.getConnection();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public  void DisposeRs()
	{
		if(rs!=null)
		{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				rs=null;
			}
		}
		if(stmt!=null)
		{
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				stmt=null;
			}
		}
		if(cstmt!=null)
		{
			try {
				cstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				cstmt=null;
			}
		}

		if(pstmt!=null)
		{
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				pstmt=null;
			}
		}
		if(conn!=null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				conn=null;
			}
		}
	}
	
	public  Integer executeUpdate(String sql,Object[]objects)
	{
		if(conn!=null)
		{
			try {
				pstmt=conn.prepareStatement(sql);
				
				if(objects!=null)
				{
					for(int i=0;i<objects.length;i++)
					{
						pstmt.setObject(i+1,objects[i]);
					}
				}
				
				int f=pstmt.executeUpdate();
				
				return f;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DaoException("sql执行更新失败！");
			}
		}
		
		return null;		
	}
	
	public ArrayList<Object[]> executeQuery(String sql,Object[] objects)
	{
		if(conn!=null)
		{
			try {
				pstmt=conn.prepareStatement(sql);
				
				if(objects!=null)
				{
					for(int i=0;i<objects.length;i++)
					{
						pstmt.setObject(i+1,objects[i]);
					}
				}
				
				rs=pstmt.executeQuery();
				
				int len=rs.getMetaData().getColumnCount();
				
				ArrayList<Object[]> al=new ArrayList<Object[]>();
				Object[] os=null;
				while(rs.next())
				{
					os=new Object[len];
					for(int i=0;i<len;i++)
					{
						os[i]=rs.getObject(i+1);
					}
					al.add(os);
				}
				
				return al;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DaoException("执行sql查询失败！");
			}
		}
		
		return null;
	}

	public ResultSet executeQuery(String sql,Object[] objects,Boolean IsGetRS)
	{
		if(conn!=null)
		{
			try {
				pstmt=conn.prepareStatement(sql);
				
				if(objects!=null)
				{
					for(int i=0;i<objects.length;i++)
					{
						pstmt.setObject(i+1,objects[i]);
					}
				}
				
				rs=pstmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DaoException("执行sql查询失败！");
			}
		}
		
		return rs;		
	}

	
	public Integer executeSingleQuery(String sql,Object[]objects)
	{
		Integer cnt=null;
		
		if(conn!=null)
		{
			try {
				pstmt=conn.prepareStatement(sql);
				
				if(objects!=null)
				{
					for(int i=0;i<objects.length;i++)
					{
						pstmt.setObject(i+1,objects[i]);
					}
				}
				
				rs=pstmt.executeQuery();
				rs.next();
				
				Object num=rs.getObject(1);
				if(num instanceof Long)
					cnt=((Long)num).intValue();
				else
					cnt=(Integer)num;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DaoException("执行单值函数失败！");
			}
		}
		
		return cnt;
	}
	
	
	private  CallableStatement  cstmt=null;
	
	// 调用存储过程【无返回值】
	// 【不需要考虑事务】
	// 调用形式：{call 存储过程名(?,?,?,?)}
	public  void callPro1(String sql, String[] parameters) {
		
		if(conn!=null)
		{
			try {
				cstmt =conn.prepareCall(sql);
				// 给？赋值
				if (parameters != null) {
					for (int i = 0; i < parameters.length; i++) {
						cstmt.setObject(i + 1, parameters[i]);
					}
				}
	
				cstmt.execute();
	
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			} 
		}
	}

	// 调用存储过程【有返回值】
	// 【不需要考虑事务】
	// 调用形式：{call 存储过程名(?,?,?,?)}
	// 适用于Oracle,其他数据库待测试
	public CallableStatement callPro2(String sql, Object[] inparameters,
			Object[] outparameters) {
		
		if(conn!=null)
		{
			try {
				cstmt = conn.prepareCall(sql);
				// 给输入参数赋值
				if (inparameters != null) {
					for (int i = 0; i < inparameters.length; i++) {
						cstmt.setObject(i + 1, inparameters[i]);
					}
				}
	
				// 给输出参数赋值
				if (outparameters != null) {
					for (int i = 0; i < outparameters.length; i++) {
						cstmt.setObject(inparameters.length + i + 1, outparameters[i]);
					}
				}
	
				cstmt.execute();
	
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			} 
		}
		
		return cstmt;

	}
}
