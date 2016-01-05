package com.lhs.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.lhs.InjectionFrawork.Injection;
import com.lhs.dao.BookDao;
import com.lhs.entity.BookDetail;

public class testBookImpl {

	@Test
	public void testSetJdbcUtil() {
		fail("Not yet implemented");
	}

	@Test
	public void testBookImpl() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBook() {
		
		BookDao bookd=(BookDao)Injection.getInstance().GetClassObject(BookDao.class);
		
		BookDetail b=bookd.GetBook("8430c569-ab0a-4e55-bbe7-0dcf14deda01");
		
		System.out.println(b.toString());
	}

	@Test
	public void testGetBooks() {
		BookDao bookd=(BookDao)Injection.getInstance().GetClassObject(BookDao.class);
		
		List<BookDetail> bs=bookd.GetBooks("随笔",1,6);
		
		for(BookDetail b:bs)
			System.out.println(b.toString()+"\n\n");
	}

	@Test
	public void testGetFilterBooks() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateBook() {
		fail("Not yet implemented");
	}

}
