package com.jdc.streams;

public class StreamDemo2 {

	public static void main(String[] args) {
		
		double average = DataProvider.getData()
				.stream()
				.map(s -> s.split(","))
				.filter(a -> a.length == 5)
				.map(a -> new Student(a))
				.mapToInt(a -> a.getAge())
				.average().orElse(0.0);
		
		System.out.println(average);
	}
}
