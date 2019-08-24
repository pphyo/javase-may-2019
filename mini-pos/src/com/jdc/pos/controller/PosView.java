package com.jdc.pos.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jdc.pos.model.ItemModel;
import com.jdc.pos.model.entity.Item;
import com.jdc.pos.model.entity.SaleDetail;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PosView implements Initializable{

    @FXML
    private ComboBox<String> category;

    @FXML
    private TextField item;

    @FXML
    private TableView<Item> items;

    @FXML
    private Label headTotal;

    @FXML
    private TableView<SaleDetail> orders;

    @FXML
    private Label subTotal;

    @FXML
    private Label tax;

    @FXML
    private Label total;

    @FXML
    void clear(MouseEvent event) {
    	orders.getItems().clear();
    }

    @FXML
    void clearSearch(ActionEvent event) {
    	category.setValue(null);
    	item.clear();
    }

    @FXML
    void delete(MouseEvent event) {
    	SaleDetail order = orders.getSelectionModel().getSelectedItem();
    	if(null != order) {
        	orders.getItems().remove(order);
    	}
    }

    @FXML
    void paid(MouseEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		List<String> categoryList = ItemModel.getInstance().getCategories();
		category.getItems().addAll(categoryList);
		
		search();
	}

	private void search() {
		// TODO Auto-generated method stub
		
	}

}
