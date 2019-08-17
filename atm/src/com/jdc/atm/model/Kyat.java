package com.jdc.atm.model;

public enum Kyat {

	_10000, _5000, _1000, _500, _100;
	
	public int getValue() {
		return Integer.parseInt(name().substring(1));
	}
	
	@Override
	public String toString() {
		return String.valueOf(getValue());
	}
}
