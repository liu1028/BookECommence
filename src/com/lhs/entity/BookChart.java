package com.lhs.entity;

import java.io.Serializable;

public class BookChart extends BookDetail implements Serializable{

	private Integer num;
	private Double t_price;
	
	public BookChart() {}

	public BookChart(String _guid, String _bookname, String _publisher,
			String _pubdate, String _author, String _summary, String _catalog,
			String _imagePath, Integer _access, Double _price,Integer _num,Double _t_price) {
		super(_guid, _bookname, _publisher, _pubdate, _author, _summary,
				_catalog, _imagePath, _access, _price);
		
		num=_num;
		t_price=_t_price;
	}
	
	public BookChart(BookDetail b,Integer _num,Double _t_price){
		super(b.getGuid(),b.getBookname(),b.getPublisher(),b.getPubdate(),b.getAuthor(),b.getSummary()
				,b.getCatalog(),b.getImagePath(),b.getAccess(),b.getPrice());
		
		num=_num;
		t_price=_t_price;

	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Double getT_price() {
		return t_price;
	}

	public void setT_price(Double t_price) {
		this.t_price = t_price;
	}
	
	

}
