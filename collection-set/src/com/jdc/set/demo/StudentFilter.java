package com.jdc.set.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class StudentFilter {
	
	
	public static void main(String[] args) {
		
		Set<Student> students = readStudents();
		
		System.out.println(students);
		
		removeWithIterator(students);

		System.out.println(students);
		
		Set<Student> students2 = readStudents();
		
		students2.removeIf(t -> t.getAge() < 18);
		
		System.out.println(students2);
	}

	private static void removeWithIterator(Set<Student> students) {

		Iterator<Student> itr = students.iterator();
		
		while(itr.hasNext()) {
			Student s = itr.next();
			
			if(s.getAge() < 18) {
				itr.remove();
			}
		}
	}

	private static Set<Student> readStudents() {
		
		Set<Student> set = new HashSet<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader("students.txt"))) {
			
			String line = null;
			
			while(null != (line = br.readLine())) {
				set.add(new Student(line));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return set;
	}

	static class Student {

		private String name;
		private int age;
		
		Student(String line) {
			String[] array = line.split(",");
			name = array[0];
			age = Integer.parseInt(array[1].trim());
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return "Student [name=" + name + ", age=" + age + "]";
		}

	}

}
