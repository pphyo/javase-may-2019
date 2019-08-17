package com.jdc.atm.model;

public class CashDTO {

	private Kyat cash;
	private int count;

	public CashDTO() {
	}

	public CashDTO(Kyat cash, int count) {
		super();
		this.cash = cash;
		this.count = count;
	}

	public Kyat getCash() {
		return cash;
	}

	public void setCash(Kyat cash) {
		this.cash = cash;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotal() {
		return cash.getValue() * count;
	}
}
