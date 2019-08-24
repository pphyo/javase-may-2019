package com.jdc.observer;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class StudentEdit {

	@FXML
	private TextField name;
	@FXML
	private TextField phone;
	@FXML
	private TextField email;
	
	private SaveObserver observer;
	
	public void subscribe(SaveObserver obs) {
		observer = obs;
	}
	
	public void save() {
		
		Student s = new Student();
		s.setName(name.getText());
		s.setPhone(phone.getText());
		s.setEmail(email.getText());
		
		StudentModel model = StudentModel.getInstance();
		model.add(s);
		
		observer.notifySave();
		
		name.getScene().getWindow().hide();
	}
	
}
