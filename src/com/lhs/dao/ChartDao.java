package com.lhs.dao;

import java.util.List;

import com.lhs.entity.BookChart;

public interface ChartDao {

	Integer AddChart(BookChart bookchart,String username); //通过存储过程来进行判断是否已经存在
	
	Integer DeleteChart(String bguid,String username);
	
	Integer UpdateChart(BookChart bookchart,String username);
	
	List<BookChart>  GetCharts(String username);
}
