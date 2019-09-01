package com.jdc.pos;

import java.io.File;

import com.jdc.pos.controller.PosHome;
import com.jdc.pos.model.ItemModel;
import com.jdc.pos.model.SaleModel;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PosMain extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		
		try {
			File trace = new File(".trace");
			
			if(trace.exists()) {
				throw new RuntimeException();
			}
			
			trace.createNewFile();
			trace.deleteOnExit();

			Parent root = FXMLLoader.load(PosHome.class.getResource("PosHome.fxml"));
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Already Used Application");
			Platform.exit();
		}
	}
	
	

	@Override
	public void stop() throws Exception {
		
		ItemModel.getInstance().save();
		SaleModel.getInstance().save();
		
		super.stop();
	}



	public static void main(String[] args) {
		launch(args);
	}
}
