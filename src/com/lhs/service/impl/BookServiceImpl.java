package com.lhs.service.impl;

import java.util.List;

import com.lhs.dao.BookDao;
import com.lhs.entity.BookDetail;
import com.lhs.service.BookService;

public class BookServiceImpl implements BookService {
	
	private BookDao bookd=null;
	
	public void setBookDao(BookDao b){
		bookd=b;
	}

	public BookServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public BookDetail GetBook(String id) {
		return bookd.GetBook(id);
	}

	@Override
	public List<BookDetail> GetBooks(String category, Integer curpage,
			Integer pageSize) {
		return bookd.GetBooks(category, curpage, pageSize);
	}

	@Override
	public List<BookDetail> GetFilterBooks(BookDetail searchModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer DeleteBook(String id) {
		return bookd.DeleteBook(id);
	}

	@Override
	public Integer AddBook(BookDetail book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer UpdateBook(BookDetail book) {
		// TODO Auto-generated method stub
		return null;
	}

}
