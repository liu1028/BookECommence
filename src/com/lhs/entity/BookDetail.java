package com.lhs.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class BookDetail implements Serializable {

        private String guid;
        private String bookname;
        private String publisher;
        private String pubdate;
        private String author;
        private String summary;
        private String catalog;
        private String imagePath;
        private Integer access;
        private Double price;
        
        public BookDetail(){}
        
        public BookDetail(String _guid,String _bookname, String _publisher, String _pubdate, String _author, String _summary, String _catalog, String _imagePath, Integer _access,double _price)
        {
            guid = _guid;
            bookname = _bookname;
            publisher = _publisher;
            pubdate = _pubdate;
            author = _author;
            summary = _summary;
            catalog = _catalog;
            imagePath = _imagePath;
            access = _access;
            price=_price;
            
           /* try {
            	String picname=imagePath.substring(imagePath.lastIndexOf("/")+1);
				imagePath=URLEncoder.encode(picname,"UTF-8");
            	
				imagePath=picname;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}*/
        }

        public Double getPrice(){
        	return price;
        }
        
        public void setPrice(Double p){
        	price=p;
        }
        
		public String getGuid() {
			return guid;
		}

		public void setGuid(String guid) {
			this.guid = guid;
		}

		public String getBookname() {
			return bookname;
		}

		public void setBookname(String bookname) {
			this.bookname = bookname;
		}

		public String getPublisher() {
			return publisher;
		}

		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}

		public String getPubdate() {
			return pubdate;
		}

		public void setPubdate(String pubdate) {
			this.pubdate = pubdate;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getSummary() {
			return summary;
		}

		public void setSummary(String summary) {
			this.summary = summary;
		}

		public String getCatalog() {
			return catalog;
		}

		public void setCatalog(String catalog) {
			this.catalog = catalog;
		}

		public String getImagePath() {
			return imagePath;
		}

		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}

		public Integer getAccess() {
			return access;
		}

		public void setAccess(Integer access) {
			this.access = access;
		}

		@Override
		public String toString() {
			return "BookDetail [guid=" + guid + ", bookname=" + bookname
					+ ", publisher=" + publisher + ", pubdate=" + pubdate
					+ ", author=" + author + ", summary=" + summary
					+ ", catalog=" + catalog + ", imagePath=" + imagePath
					+ ", access=" + access + ", price=" + price + "]";
		}
    
        

}
