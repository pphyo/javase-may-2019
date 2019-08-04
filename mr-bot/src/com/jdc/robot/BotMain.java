package com.jdc.robot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BotMain extends Application{

	@Override
	public void start(Stage stage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("BotController.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	@Override
	public void stop() throws Exception {
		MrBot.getBot().saveMemory();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
