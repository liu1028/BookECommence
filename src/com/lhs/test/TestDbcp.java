package com.lhs.test;


import java.util.ArrayList;

import org.junit.Test;

import com.lhs.jdbc.JdbcUtil;

public class TestDbcp {

	@Test
	public void test() {
		JdbcUtil jdbcUtil=new JdbcUtil();
		jdbcUtil.CreateConn();
		
		String sql="select * from user where 1=?";
		Object[] obj={1};
		ArrayList<Object[]> objs= jdbcUtil.executeQuery(sql, obj);
		jdbcUtil.DisposeRs();
		
		for(Object[] obs:objs)
		{
			for(Object o:obs)
			{
				System.out.println((String)o.toString()+"....."+(String)o.toString());
				
			}
		}
		
	}

}
