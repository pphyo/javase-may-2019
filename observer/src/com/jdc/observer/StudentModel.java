package com.jdc.observer;

import java.util.ArrayList;
import java.util.List;

public class StudentModel {
	
	private static StudentModel instance;
	
	private List<Student> students;
	
	private StudentModel() {
		students = new ArrayList<>();
	}

	public static StudentModel getInstance() {
		
		if(null == instance) {
			instance = new StudentModel();
		}
		return instance;
	}

	public void add(Student s) {
		students.add(s);
	}

	public List<Student> getAll() {
		return students;
	}

	public void delete(Student s) {
		students.remove(s);
	}
	
}
