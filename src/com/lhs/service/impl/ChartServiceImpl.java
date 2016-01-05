package com.lhs.service.impl;

import java.util.List;

import com.lhs.dao.BookDao;
import com.lhs.dao.ChartDao;
import com.lhs.entity.BookChart;
import com.lhs.service.ChartService;

public class ChartServiceImpl implements ChartService {

	private ChartDao chartdao=null;
	public void setChartDao(ChartDao c){
		chartdao=c;
	}
	
	public ChartServiceImpl() {
		
	}

	@Override
	public Integer AddChart(BookChart bookchart, String username) {
		return chartdao.AddChart(bookchart, username);
	}

	@Override
	public Integer DeleteChart(String bguid, String username) {
		return chartdao.DeleteChart(bguid, username);
	}

	@Override
	public Integer UpdateChart(BookChart bookchart, String username) {
		return chartdao.UpdateChart(bookchart, username);
	}

	@Override
	public List<BookChart> GetCharts(String username) {
		return chartdao.GetCharts(username);
	}

}
