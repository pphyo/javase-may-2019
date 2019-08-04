package com.jdc.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortDemo {

	public static void main(String[] args) {
		
		List<Student> list = getStudents();
		
		System.out.println(list);
		
		Collections.sort(list);
		
		System.out.println(list);
		
		Comparator<Student> idAsec = new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				return o1.getId() - o2.getId();
			}
		};
		
		Collections.sort(list, idAsec);
		
		System.out.println(list);
	}

	private static List<Student> getStudents() {
		
		List<Student> list = new ArrayList<>();
		
		list.add(new  Student(3, "Aung Aung", 21));
		list.add(new  Student(2, "Ni Aung", 18));
		list.add(new  Student(1, "Maung Aung", 19));
		list.add(new  Student(5, "Aye Aung", 18));
		list.add(new  Student(4, "Myo Aung", 18));
		
		return list;
	}
}
