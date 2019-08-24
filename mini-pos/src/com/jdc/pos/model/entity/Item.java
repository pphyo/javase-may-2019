package com.jdc.pos.model.entity;

import java.io.Serializable;

public class Item implements Serializable{

	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
	private String category;
	private int price;
	
	public Item() {
	}
	
	public Item(String line) {
		String [] array = line.split("\t");
		id = Integer.parseInt(array[0]);
		name = array[1];
		category = array[2];
		price = Integer.parseInt(array[3]);
	}
	
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
