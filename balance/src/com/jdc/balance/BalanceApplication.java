package com.jdc.balance;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BalanceApplication extends Application{

	@Override
	public void start(Stage stage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("BalanceList.fxml"));
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	@Override
	public void stop() throws Exception {
		BalanceRepo.Instance.save();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
