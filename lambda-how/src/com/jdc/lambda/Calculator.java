package com.jdc.lambda;

public class Calculator {
	
	public static void main(String[] args) {
		showResult(10, 5, (a,b) -> a + b);
		showResult(10, 5, (a,b) -> a - b);
		showResult(10, 5, (a,b) -> a * b);
		showResult(10, 5, (a,b) -> a / b);
		showResult(10, 5, Calculator::min);
		
		Calculator calc = new Calculator();
		showResult(10, 5, calc::max);
		
	}
	
	int max(int a, int b) {
		return a > b ? a : b;
	}
	
	public static void showResult(int a, int b, Calculation operater) {
		System.out.println(operater.calculate(a, b));
	}

	@FunctionalInterface
	public static interface Calculation {
		int calculate(int a, int b);
	}
	
	static int min(int a, int b) {
		return a > b ? b : a;
	}
}
