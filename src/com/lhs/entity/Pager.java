package com.lhs.entity;

import java.io.Serializable;
import java.util.List;

public class Pager<T> implements Serializable {
	
	private int pageSize;  //表示单页有多少记录
	
	private int pageNum; //表示当前页
	
	private int totalPages; //表示总页数
	
	private int totalSize; //表示总记录
	
	private List<T> records;

	
	public Pager() {
		super();
	}

	public Pager(int pageSize, int pageNum, int totalPages, int totalSize,
			List<T> records) {
		super();
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.totalPages = totalPages;
		this.totalSize = totalSize;
		this.records = records;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}
	
	

}
