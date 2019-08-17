package com.jdc.atm;

import java.io.IOException;
import java.util.List;

import com.jdc.atm.model.AtmMachine;
import com.jdc.atm.model.CashDTO;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;

public class AtmView {

    @FXML
    private JFXTextField amount;

    @FXML
    private TableView<CashDTO> table;

    @FXML
    void withdraw(ActionEvent event) {
    	
    	try {
    		
    		int withdrawAmount = getWithdrawAmount();
    		
    		List<CashDTO> list = AtmMachine.ATM.withDraw(withdrawAmount);
    		
    		amount.clear();
    		
    		table.getItems().clear();
    		table.getItems().addAll(list);
			
		} catch (Exception e) {
			
			Dialog<String> dialog = new  Dialog<String>();
			dialog.setTitle("ATM ERROR");
			dialog.setContentText(e.getMessage());
			dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
			dialog.show();
		}

    }

	private int getWithdrawAmount() {
		try {
			return Integer.parseInt(amount.getText());
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Please enter digit to amount.");
		}
	}

	public static Parent getView() throws IOException {
		Parent root = FXMLLoader.load(AtmSetting.class.getResource("AtmView.fxml"));
		root.getStylesheets().add(AtmView.class.getResource("/css/controls/jfx-tree-table-view.css").toExternalForm());
		return root;
	}

}
