package com.lhs.dao.impl;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.lhs.dao.BookDao;
import com.lhs.entity.BookDetail;
import com.lhs.jdbc.JdbcUtil;

public class BookImpl implements BookDao {

	private JdbcUtil jdbcUtil=null;
	public void setJdbcUtil(JdbcUtil j)
	{
		jdbcUtil=j;
	}


	public BookImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public BookDetail GetBook(String id) {
		
		BookDetail bookd=null;
		
		String sql="select a.*,b.naccess from t_book a,t_access b where a.guid=? and a.guid=b.bguid ";
		Object [] objs={id};
		
		try{
			jdbcUtil.CreateConn();
			ResultSet rs=jdbcUtil.executeQuery(sql, objs,true);
												  
			while(rs.next()){
				String guid=rs.getString("guid");
				String bookname=rs.getString("bookname");
				String publisher=rs.getString("publisher");
				String author=rs.getString("author");
				String pubdate=new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("pubdate"));
				String summary=rs.getString("summary");
				String catalog=rs.getString("catalog");
				String imagePath=rs.getString("imagepath");
				Double price=rs.getDouble("price");
				Integer access=rs.getInt("naccess");
				
				//添加销量的sql语句。。。。
				bookd=new BookDetail(guid, bookname, publisher, pubdate, author, summary, catalog, imagePath, access, price);			
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{		
			jdbcUtil.DisposeRs();
		}
		
		
		return bookd;
	}

	//pageNum从第一页开始
	@Override
	public List<BookDetail> GetBooks(String category, Integer curpage,Integer pageSize) {
		
		List<BookDetail> list=null;
		
		if(pageSize==null || curpage==null || category==null)
			return null;
		
		Integer start=pageSize*(curpage-1);
		Integer end=pageSize;
		
		String sql="select * from t_book a,td_category b,t_book_category c,t_access d "
				+ "where b.name=? and a.guid=c.bguid and c.cguid=b.guid and a.guid=d.bguid order by d.naccess desc,a.bookname asc limit ?,? ";
		
		Object[] objs={category,start,end};
		
		try{
			jdbcUtil.CreateConn();
			
			ResultSet rs=jdbcUtil.executeQuery(sql, objs,true);
			
			list=new ArrayList<BookDetail>();
			while(rs.next()){
				String guid=rs.getString("guid");
				String bookname=rs.getString("bookname");
				String publisher=rs.getString("publisher");
				String author=rs.getString("author");
				String pubdate=new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("pubdate"));
				String summary=rs.getString("summary");
				String catalog=rs.getString("catalog");
				String imagePath=rs.getString("imagePath");
				Double price=rs.getDouble("price");
				Integer access=rs.getInt("naccess");
				
				BookDetail bookd=new BookDetail(guid, bookname, publisher, pubdate, author, summary, catalog, imagePath, access, price);
				list.add(bookd);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			jdbcUtil.DisposeRs();
		}
		
		return list;
	}

	@Override
	public List<BookDetail> GetFilterBooks(BookDetail searchModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer DeleteBook(String id) {
		
		String sql="delete from t_book where guid=?";
		Object[] objs={id};
		
		Integer num=null;
		
		try{
			jdbcUtil.CreateConn();
			num=jdbcUtil.executeUpdate(sql, objs);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			jdbcUtil.DisposeRs();
		}
		
		
		return num;
	}

	@Override
	public Integer AddBook(BookDetail book) {

		String guid=UUID.randomUUID().toString();
 	
//		sql="insert into t_book (guid,bookname,publisher,author,pubdate,summary,"
	//			+ "catalog,ctime,imagepath,price)";
		
		return null;
	}

	@Override
	public Integer UpdateBook(BookDetail book) {
		// TODO Auto-generated method stub
		return null;
	}

}
