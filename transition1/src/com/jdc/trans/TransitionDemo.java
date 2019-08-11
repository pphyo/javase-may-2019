package com.jdc.trans;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class TransitionDemo implements Initializable{
	
	@FXML
	private Rectangle node;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		RotateTransition trans = new RotateTransition(Duration.millis(500), node);
		trans.setFromAngle(0);
		trans.setToAngle(360);
		trans.setCycleCount(Animation.INDEFINITE);
		trans.setAutoReverse(false);
		
		node.setOnMouseClicked(e -> {
			
			if(trans.getStatus() == Status.RUNNING) {
				trans.stop();
			} else {
				trans.play();
			}
		});
	}
	
}
