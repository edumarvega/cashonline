package com.broker.cashonline.pagination;

import java.io.Serializable;

public class Page implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1642056254699579069L;
	
	private int page;
	private int size;
	private long total;
	
	
	
	public Page() {
	
	}
		
	public Page(int page, int size, long total) {
		this.page = page;
		this.size = size;
		this.total = total;
	}


	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + page;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Page other = (Page) obj;
		if (page != other.page)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Page [page=" + page + ", size=" + size + ", total=" + total + "]";
	}
			
}
