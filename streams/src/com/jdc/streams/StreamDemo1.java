package com.jdc.streams;

import java.util.List;
import java.util.stream.Collectors;

public class StreamDemo1 {

	public static void main(String[] args) {
		
		List<Student> list = DataProvider.getData()
				.stream()
				.map(s -> s.split(","))
				.filter(a -> a.length == 5)
				.map(Student::new)
				.sorted((a,b) -> a.getAge() - b.getAge())
				.collect(Collectors.toList());
		
		System.out.println(list);
	}
}
