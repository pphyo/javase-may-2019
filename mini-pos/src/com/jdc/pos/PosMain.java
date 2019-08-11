package com.jdc.pos;

import com.jdc.pos.controller.PosHome;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PosMain extends Application{

	@Override
	public void start(Stage stage) throws Exception {

		Parent root = FXMLLoader.load(PosHome.class.getResource("PosHome.fxml"));
		stage.setScene(new Scene(root));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
