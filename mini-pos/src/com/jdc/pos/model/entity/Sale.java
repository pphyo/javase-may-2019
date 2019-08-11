package com.jdc.pos.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Sale {

	private int id;
	private LocalDate saleDate;
	private LocalTime saleTime;
	private List<SaleDetail> details;
	
	public Sale() {
		details = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(LocalDate saleDate) {
		this.saleDate = saleDate;
	}

	public LocalTime getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(LocalTime saleTime) {
		this.saleTime = saleTime;
	}

	public List<SaleDetail> getDetails() {
		return details;
	}

	public void setDetails(List<SaleDetail> details) {
		this.details = details;
	}

}
