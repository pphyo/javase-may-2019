package com.jdc.pos.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class SaleDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String category;
	private String item;
	private int price;
	private int count;

	private Sale sale;
	
	public LocalDate getSaleDate() {
		return sale.getSaleDate();
	}

	public int getTotal() {
		return price * count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

}
