package com.jdc.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SaleHistory {

    @FXML
    private ComboBox<?> category;

    @FXML
    private TextField item;

    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;

    @FXML
    private TableView<?> table;

    @FXML
    void clear(ActionEvent event) {

    }

}
