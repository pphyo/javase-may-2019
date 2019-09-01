package com.jdc.pos.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.jdc.pos.model.ItemModel;
import com.jdc.pos.model.SaleModel;
import com.jdc.pos.model.entity.SaleDetail;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SaleHistory implements Initializable{

    @FXML
    private ComboBox<String> category;

    @FXML
    private TextField item;

    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;

    @FXML
    private TableView<SaleDetail> table;
    
    private boolean waitSearch;

    @FXML
    void clear(ActionEvent event) {
    	
    	waitSearch = true;
    	
    	table.getItems().clear();
    	category.setValue(null);
    	item.clear();
    	dateFrom.setValue(null);
    	dateTo.setValue(null);
    	
    	waitSearch = false;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		category.getItems().addAll(ItemModel.getInstance().getCategories());
		
		category.valueProperty().addListener((a,b,c) -> search());
		item.textProperty().addListener((a,b,c) -> search());
		dateFrom.valueProperty().addListener((a,b,c) -> search());
		dateTo.valueProperty().addListener((a,b,c) -> search());
		
		dateFrom.setValue(LocalDate.now());
	}
	
	private void search() {
		
		if(!waitSearch) {
			
			try {
				table.getItems().clear();
				List<SaleDetail> list = SaleModel.getInstance()
						.search(category.getValue(), item.getText(), dateFrom.getValue(), dateTo.getValue());
				table.getItems().addAll(list);
			} catch (Exception e) {
				e.printStackTrace();
				Dialog<String> dialog = new Dialog<String>();
				dialog.setTitle("POS Error");
				dialog.setContentText(e.getMessage());
				dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
				dialog.show();
			}
		}
	}

}
