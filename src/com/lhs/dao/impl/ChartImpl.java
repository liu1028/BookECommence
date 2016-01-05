package com.lhs.dao.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.lhs.dao.ChartDao;
import com.lhs.entity.BookChart;
import com.lhs.jdbc.JdbcUtil;

public class ChartImpl implements ChartDao {

	private JdbcUtil jdbcUtil=null;
	public void setJdbcUtil(JdbcUtil j){
		jdbcUtil=j;
	}
	
	public ChartImpl() {}

	@Override
	public Integer AddChart(BookChart bookchart,String username) {
		Integer result=-1;
		
		String sql="{call AddChart(?,?,?,?,?)}";
		Object[] in={bookchart.getGuid(),username,bookchart.getNum(),bookchart.getT_price()};
		Object[] out={result};
		
		try{
			jdbcUtil.CreateConn();
			CallableStatement cstmt= jdbcUtil.callPro2(sql, in, out);
			
			result=cstmt.getInt(5);
			
		}catch(Exception e){
			
		}finally{
			jdbcUtil.DisposeRs();
		}
		
		return result;
	}

	@Override
	public Integer DeleteChart(String bguid, String username) {
		Integer flag=null;
		
		String sql="delete from t_chart where bguid=? and uguid=(select guid from t_user where username=? )";
		Object[] objs={bguid,username};
		
		try{
			jdbcUtil.CreateConn();
			flag=jdbcUtil.executeUpdate(sql, objs);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			jdbcUtil.DisposeRs();
		}
				
		return flag;		
	}

	@Override
	public Integer UpdateChart(BookChart bookchart,String username) {
		
		Integer flag=null;
		
		String sql="update t_chart set num=?,price=? where  bguid=? and uguid=(select guid from t_user where username=? )";
		Object[] objs={bookchart.getNum(),bookchart.getPrice(),bookchart.getGuid(),username};
		
		try{
			jdbcUtil.CreateConn();
			flag=jdbcUtil.executeUpdate(sql, objs);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			jdbcUtil.DisposeRs();
		}
				
		return flag;		
	}

	@Override
	public List<BookChart> GetCharts(String username) {
		
		List<BookChart> list=null;
		
		String sql="select a.guid,a.bookname,a.imagepath,a.pubdate,a.author,b.num,b.price from"
				+ " t_user a,t_chart b where b.uguid=(select guid from t_user where username=? ) and b.bguid=a.guid and b.isbuyed='0'";
		Object[] objs={username};
		
		try{
			jdbcUtil.CreateConn();
			
			ResultSet rs=jdbcUtil.executeQuery(sql, objs, true);
			
			list=new ArrayList<BookChart>();
			while(rs.next()){
				String guid=rs.getString("guid");
				String bookname=rs.getString("bookname");
				String author=rs.getString("author");
				String pubdate=new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("pubdate"));
				String imagePath=rs.getString("imagepath");
				Double price=rs.getDouble("price");
				Integer num =rs.getInt("num");
				
				BookChart bookc=new BookChart(guid, bookname, "", pubdate, author, "", "", imagePath,0,0.00, num, price);			
				list.add(bookc);
			}
			
		}catch(Exception e){
			
		}finally{
			jdbcUtil.DisposeRs();
		}
				
		return list;
	}
	

}
