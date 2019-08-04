package com.jdc.robot;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class BotController implements Initializable{

    @FXML
    private ListView<String> output;

    @FXML
    private TextField input;
    
    private MrBot bot;
    
    public void talk() {
    	
    	if(!input.getText().isEmpty()) {
    		
    		String result = bot.talk(input.getText());
    		output.getItems().add(String.format("You > %s", input.getText()));
    		output.getItems().add(String.format("Bot > %s", result));
    		output.getItems().add("");
    		
    		input.clear();
        	input.setPromptText(bot.getTalkType());
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	bot = MrBot.getBot();
    	input.setPromptText(bot.getTalkType());
	}

}
