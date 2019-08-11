package com.jdc.trans;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class TransitionDemo implements Initializable{
	
	@FXML
	private Rectangle node;
	@FXML
	private ToggleGroup checks;

	private Transition trans;
	
	private static final double time = 500;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		node.setOnMouseClicked(event -> {
			if(null != trans && trans.getStatus() == Status.RUNNING) {
				trans.stop();
			}
		});
		
		checks.selectedToggleProperty().addListener((a,b,c) -> {

			if(null != trans && trans.getStatus() == Status.RUNNING) {
				trans.stop();
			}
			
			RadioButton selected = (RadioButton) checks.getSelectedToggle();
			if(selected.getText().equals("Sequential")) {
				trans = getSequentialAnimation();
			} else {
				trans = getParallelAnimation();
			}
			
			trans.setAutoReverse(false);
			trans.setCycleCount(Animation.INDEFINITE);
			
			trans.play();
		});
	}


	private Transition getParallelAnimation() {
		return new ParallelTransition(rotate(), scale(), translate(), fade());
	}


	private Transition getSequentialAnimation() {
		return new SequentialTransition(rotate(), scale(), translate(), fade());
	}
	
	private Transition rotate() {
		
		RotateTransition trans = new RotateTransition(Duration.millis(time), node);
		trans.setFromAngle(0);
		trans.setToAngle(360);
		trans.setAutoReverse(true);
		trans.setCycleCount(1);
		return trans;
	}
	
	private Transition scale() {
		ScaleTransition trans = new ScaleTransition(Duration.millis(time), node);
		trans.setFromX(0.5);
		trans.setToX(1.5);
		trans.setFromY(0.5);
		trans.setToY(1.5);
		trans.setAutoReverse(true);
		trans.setCycleCount(1);
		return trans;
	}
	
	private Transition translate() {
		TranslateTransition trans = new TranslateTransition(Duration.millis(time), node);
		trans.setFromX(-200);
		trans.setToX(200);
		trans.setAutoReverse(true);
		trans.setCycleCount(1);
		return trans;
	}
	
	private Transition fade() {
		FadeTransition trans = new FadeTransition(Duration.millis(time), node);
		trans.setFromValue(1);
		trans.setToValue(0.3);
		trans.setAutoReverse(true);
		trans.setCycleCount(1);
		return trans;
	}
}
