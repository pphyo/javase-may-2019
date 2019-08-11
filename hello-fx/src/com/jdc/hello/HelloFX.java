package com.jdc.hello;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloFX {
	
	@FXML
	private TextField input;
	@FXML
	private Label output;
	
	public void greet() {
		
		// get input text
		String name = input.getText();
		
		// construct message
		String message = String.format("Hello %s!", name);
		
		// set output
		output.setText(message);
		
		// clear input
		input.clear();
	}
}
