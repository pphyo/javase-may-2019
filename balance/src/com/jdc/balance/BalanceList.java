package com.jdc.balance;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.balance.Balance.Type;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BalanceList implements Initializable{
	
	@FXML
	private ComboBox<Type> type;
	@FXML
	private DatePicker date;
	@FXML
	private TextField amount;
	@FXML
	private TextField reason;
	@FXML
	private TableView<Balance> table;
	
	public void add() {
		BalanceRepo.Instance.add(new Balance(date.getValue(), 
				type.getValue(), 
				Integer.parseInt(amount.getText()), 
				reason.getText()));
		
		type.setValue(null);
		date.setValue(null);
		amount.clear();
		reason.clear();
		
		table.getItems().clear();
		table.getItems().addAll(BalanceRepo.Instance.getList());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		type.getItems().addAll(Type.values());
		table.getItems().addAll(BalanceRepo.Instance.getList());
	}

}
