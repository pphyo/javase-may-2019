package com.jdc.atm;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jdc.atm.model.AtmMachine;
import com.jdc.atm.model.CashDTO;
import com.jdc.atm.model.Kyat;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class AtmSetting implements Initializable{
	
    @FXML
    private JFXTextField _10000;

    @FXML
    private JFXTextField _5000;

    @FXML
    private JFXTextField _1000;

    @FXML
    private JFXTextField _500;

    @FXML
    private JFXTextField _100;
    
    public void save() {
    	List<CashDTO> list = new ArrayList<>();
    	list.add(new CashDTO(Kyat._10000, getCount(_10000)));
    	list.add(new CashDTO(Kyat._5000, getCount(_5000)));
    	list.add(new CashDTO(Kyat._1000, getCount(_1000)));
    	list.add(new CashDTO(Kyat._500, getCount(_500)));
    	list.add(new CashDTO(Kyat._100, getCount(_100)));
    	AtmMachine.ATM.setChain(list);
    }

	private int getCount(JFXTextField textField) {

		try {
			return Integer.parseInt(textField.getText());
		} catch (NumberFormatException e) {
			// Nothing to do
		}
		
		return 0;
	}


	public static Parent getView() throws IOException {
		return FXMLLoader.load(AtmSetting.class.getResource("AtmSetting.fxml"));
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		List<CashDTO> list = AtmMachine.ATM.getCurrentCash();
		
		for(CashDTO dto : list) {
			switch (dto.getCash()) {
			case _10000:
				_10000.setText(String.valueOf(dto.getCount()));
				break;
			case _5000:
				_5000.setText(String.valueOf(dto.getCount()));
				break;
			case _1000:
				_1000.setText(String.valueOf(dto.getCount()));
				break;
			case _500:
				_500.setText(String.valueOf(dto.getCount()));
				break;
			default:
				_100.setText(String.valueOf(dto.getCount()));
				break;
			}
		}
	}

}
