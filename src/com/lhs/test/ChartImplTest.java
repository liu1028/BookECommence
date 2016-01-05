package com.lhs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lhs.InjectionFrawork.Injection;
import com.lhs.dao.ChartDao;
import com.lhs.entity.BookChart;

public class ChartImplTest {

	@Test
	public void testSetJdbcUtil() {
		fail("Not yet implemented");
	}

	@Test
	public void testChartImpl() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddChart() {
		ChartDao chartd=(ChartDao)Injection.getInstance().GetClassObject(ChartDao.class);
		
		BookChart b=new BookChart("02f228cd-935f-47b6-9e6e-357c795730a2", "草房子", "", "1994-07-01", "曹文轩", "", "", "", 2, 0.00, 2, 12.32);
	
		Integer r=chartd.AddChart(b, "lhs");
		System.out.println(r);
	}

	@Test
	public void testDeleteChart() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateChart() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCharts() {
		fail("Not yet implemented");
	}

}
