package com.jdc.balance;

import java.time.LocalDate;

public class Balance {

	private LocalDate date;
	private Type type;
	private int amount;
	private String reason;

	public Balance() {
	}

	public Balance(String line) {
		String[] array = line.split(",");
		date = LocalDate.parse(array[0]);
		type = Type.valueOf(array[1]);
		amount = Integer.parseInt(array[2]);
		reason = array[3];
	}

	public Balance(LocalDate date, Type type, int amount, String reason) {
		super();
		this.date = date;
		this.type = type;
		this.amount = amount;
		this.reason = reason;
	}
	
	@Override
	public String toString() {
		return String.format("%s,%s,%d,%s", date.toString(), type.name(), amount, reason);
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public enum Type {
		Income, Expense
	}
}
