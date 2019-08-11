package com.jdc.pos.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.SVGPath;

public class PosHome implements Initializable{
	
	@FXML
	private SVGPath icon;
	@FXML
	private StackPane content;
	
	public void upload() {
		
	}
	
	public void switchView() {
		
		content.getChildren().clear();
		Parent view = null;
		
		if(icon.getId().equals(Icon.History.name())) {
			icon.setId(Icon.Pos.name());
			icon.setContent(Icon.Pos.content);
			view = getView("SaleHistory");
		} else {
			icon.setId(Icon.History.name());
			icon.setContent(Icon.History.content);
			view = getView("PosView");
		}
		
		content.getChildren().add(view);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		icon.setId(Icon.History.name());
		icon.setContent(Icon.History.content);
		content.getChildren().add(getView("PosView"));
	}
	
	private Parent getView(String viewName) {
		try {
			return FXMLLoader.load(getClass().getResource(viewName.concat(".fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private enum Icon {
		
		Pos("M19 11c0-0.547-0.453-1-1-1h-2v-2c0-0.547-0.453-1-1-1s-1 0.453-1 1v2h-2c-0.547 0-1 0.453-1 1s0.453 1 1 1h2v2c0 0.547 0.453 1 1 1s1-0.453 1-1v-2h2c0.547 0 1-0.453 1-1zM10 24c0 1.109-0.891 2-2 2s-2-0.891-2-2 0.891-2 2-2 2 0.891 2 2zM24 24c0 1.109-0.891 2-2 2s-2-0.891-2-2 0.891-2 2-2 2 0.891 2 2zM26 7v8c0 0.5-0.375 0.938-0.891 1l-16.312 1.906c0.063 0.344 0.203 0.734 0.203 1.094s-0.219 0.688-0.375 1h14.375c0.547 0 1 0.453 1 1s-0.453 1-1 1h-16c-0.547 0-1-0.453-1-1 0-0.484 0.734-1.687 0.953-2.141l-2.766-12.859h-3.187c-0.547 0-1-0.453-1-1s0.453-1 1-1h4c1.062 0 1.078 1.25 1.234 2h18.766c0.547 0 1 0.453 1 1z"), 
		History("M6 18.5v1c0 0.266-0.234 0.5-0.5 0.5h-1c-0.266 0-0.5-0.234-0.5-0.5v-1c0-0.266 0.234-0.5 0.5-0.5h1c0.266 0 0.5 0.234 0.5 0.5zM6 14.5v1c0 0.266-0.234 0.5-0.5 0.5h-1c-0.266 0-0.5-0.234-0.5-0.5v-1c0-0.266 0.234-0.5 0.5-0.5h1c0.266 0 0.5 0.234 0.5 0.5zM6 10.5v1c0 0.266-0.234 0.5-0.5 0.5h-1c-0.266 0-0.5-0.234-0.5-0.5v-1c0-0.266 0.234-0.5 0.5-0.5h1c0.266 0 0.5 0.234 0.5 0.5zM24 18.5v1c0 0.266-0.234 0.5-0.5 0.5h-15c-0.266 0-0.5-0.234-0.5-0.5v-1c0-0.266 0.234-0.5 0.5-0.5h15c0.266 0 0.5 0.234 0.5 0.5zM24 14.5v1c0 0.266-0.234 0.5-0.5 0.5h-15c-0.266 0-0.5-0.234-0.5-0.5v-1c0-0.266 0.234-0.5 0.5-0.5h15c0.266 0 0.5 0.234 0.5 0.5zM24 10.5v1c0 0.266-0.234 0.5-0.5 0.5h-15c-0.266 0-0.5-0.234-0.5-0.5v-1c0-0.266 0.234-0.5 0.5-0.5h15c0.266 0 0.5 0.234 0.5 0.5zM26 21.5v-13c0-0.266-0.234-0.5-0.5-0.5h-23c-0.266 0-0.5 0.234-0.5 0.5v13c0 0.266 0.234 0.5 0.5 0.5h23c0.266 0 0.5-0.234 0.5-0.5zM28 4.5v17c0 1.375-1.125 2.5-2.5 2.5h-23c-1.375 0-2.5-1.125-2.5-2.5v-17c0-1.375 1.125-2.5 2.5-2.5h23c1.375 0 2.5 1.125 2.5 2.5z");
		
		private String content;

		private Icon(String content) {
			this.content = content;
		}
		
	}


}
