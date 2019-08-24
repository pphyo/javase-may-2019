package com.jdc.observer;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StudentList implements Initializable{
	
	@FXML
	private TableView<Student> table;
	
	public void add() {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentEdit.fxml"));
			Parent root = loader.load();
			StudentEdit edit = loader.getController();
			edit.subscribe(this::load);
			
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void load() {
		table.getItems().clear();
		table.getItems().addAll(StudentModel.getInstance().getAll());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		MenuItem delete = new MenuItem("Delete");
		MenuItem edit = new MenuItem("Edit");
		
		table.setContextMenu(new ContextMenu(edit, delete));
		
		delete.setOnAction(e -> {
			Student s = table.getSelectionModel().getSelectedItem();
			StudentModel.getInstance().delete(s);
			load();
		});
	}

}
