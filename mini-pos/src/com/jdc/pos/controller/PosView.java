package com.jdc.pos.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jdc.pos.model.ItemModel;
import com.jdc.pos.model.entity.Item;
import com.jdc.pos.model.entity.SaleDetail;

import javafx.collections.ListChangeListener.Change;
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

		// add categories
		category.getItems().addAll(ItemModel.getInstance()
				.getCategories());
		
		// add listener for search
		category.valueProperty().addListener((a,b,c) -> search());
		item.textProperty().addListener((a,b,c) -> search());
		
		// add click listener to table
		items.setOnMouseClicked(event -> {
			
			if(event.getClickCount() == 2) {
				
				// get selected item
				Item item = items.getSelectionModel().getSelectedItem();
				
				// search sale details from cart
				SaleDetail detail = orders.getItems().stream()
						.filter(d -> d.getItem().equals(item.getName()) 
								&& d.getCategory().equals(item.getCategory()))
						.findFirst().orElse(null);
				
				if(null == detail) {
					// if no -> create sale details and add to cart
					detail = new SaleDetail();
					detail.setCategory(item.getCategory());
					detail.setItem(item.getName());
					detail.setPrice(item.getPrice());
					detail.setCount(1);
					
					orders.getItems().add(detail);
				} else {
					// if yes -> plus one to sale details
					detail.setCount(detail.getCount() + 1);
					List<SaleDetail> list = new ArrayList<>(orders.getItems());
					orders.getItems().clear();
					orders.getItems().addAll(list);
				}			
			}
		});
		
		// set total values
		headTotal.textProperty().bind(total.textProperty());
		
		orders.getItems().addListener((Change<?> e) -> {
			Integer value = orders.getItems().stream().mapToInt(od -> od.getTotal()).sum();
			subTotal.setText(value.toString());
			
			Double taxValue = value * 0.05;
			tax.setText(String.valueOf(taxValue.intValue()));
			
			Integer totalValue = value + taxValue.intValue();
			total.setText(totalValue.toString());
		});
				
		search();
	}

	private void search() {

		items.getItems().clear();
		items.getItems()
			.addAll(ItemModel.getInstance()
					.search(category.getValue(), item.getText()));
	}

}
