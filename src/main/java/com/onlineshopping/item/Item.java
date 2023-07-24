package com.onlineshopping.item;

public class Item {
	private int id;
	private String name ;
	private String categoryid;
	private String limitperorder;
	private Double price;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public String getLimitperorder() {
		return limitperorder;
	}
	public void setLimitperorder(String limitperorder) {
		this.limitperorder = limitperorder;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
}
