package com.jdc.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamDemo3 {

	public static void main(String[] args) {
		
		Map<String, List<Student>> studentsWithGrade
			= DataProvider.getData().stream()
			.map(a -> a.split(","))
			.filter(a -> a.length == 5)
			.map(Student::new)
			.collect(Collectors.groupingBy(Student::getGrade));
		
		System.out.println(studentsWithGrade);
	}
}
