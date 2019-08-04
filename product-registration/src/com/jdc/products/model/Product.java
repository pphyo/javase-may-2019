package com.jdc.products.model;

import java.io.Serializable;

public class Product implements Serializable{

	private static final long serialVersionUID = 1L;

	private String name;
	private Category category;
	private Size size;
	private int price;
	private String remark;
	
	public Product() {
	}
	
	public Product(String ... data) {
		super();
		this.name = data[0].trim();
		this.category = Category.valueOf(data[1].trim());
		this.size = Size.valueOf(data[2].trim());
		this.price = Integer.parseInt(data[3].trim());
		this.remark = data[4].trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
