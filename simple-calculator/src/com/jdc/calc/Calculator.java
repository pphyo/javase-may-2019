package com.jdc.calc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Calculator {
	
	@FXML
	private TextField input1;
	@FXML
	private TextField input2;
	@FXML
	private Label output;
	
	public void calculate(ActionEvent e) {
		
		try {
			
			Double d1 = Double.valueOf(input1.getText());
			Double d2 = Double.valueOf(input2.getText());
			
			Button btn = (Button) e.getSource();
			
			Double d3 = calculate(d1, d2, btn.getText());
			
			output.setText(d3.toString());
			
		} catch (Exception ex) {
			output.setText("Please enter digit only!");
		}
		
	}

	private Double calculate(Double d1, Double d2, String ope) {
		
		Double d3 = null;
		
		switch (ope) {
		case "+":
			d3 = d1 + d2;
			break;
		case "-":
			d3 = d1 - d2;
			break;
		case "*":
			d3 = d1 * d2;
			break;
		case "/":
			d3 = d1 / d2;
			break;

		default:
			break;
		}
		
		return d3;
	}


}
