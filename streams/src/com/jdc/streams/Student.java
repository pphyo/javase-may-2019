package com.jdc.streams;

public class Student {

	private int id;
	private String name;
	private int age;
	private String phone;
	private String grade;

	public Student(String[] array) {
		super();
		this.id = Integer.parseInt(array[0].trim());
		this.name = array[1].trim();
		this.age = Integer.parseInt(array[2].trim());
		this.phone = array[3].trim();
		this.grade = array[4].trim();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", phone=" + phone + ", grade=" + grade + "]";
	}

}
