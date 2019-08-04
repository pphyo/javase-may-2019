package com.jdc.products;

import com.jdc.products.model.ProductModel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProductMain extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("ProductList.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
	
	@Override
	public void stop() throws Exception {
		ProductModel.getModel().saveToFile();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
