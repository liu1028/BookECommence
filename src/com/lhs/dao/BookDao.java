package com.lhs.dao;

import java.util.List;

import com.lhs.entity.BookDetail;

public interface BookDao {

	BookDetail GetBook(String id);
	List<BookDetail> GetBooks(String category,Integer curpage,Integer pageSize);
	List<BookDetail> GetFilterBooks(BookDetail searchModel);
	
	
	Integer DeleteBook(String id);
	
	Integer AddBook(BookDetail book);
	
	Integer UpdateBook(BookDetail book);
	
}
