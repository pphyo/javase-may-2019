package com.jdc.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PosView {

    @FXML
    private ComboBox<?> category;

    @FXML
    private TextField item;

    @FXML
    private TableView<?> items;

    @FXML
    private Label headTotal;

    @FXML
    private TableView<?> orders;

    @FXML
    private Label subTotal;

    @FXML
    private Label tax;

    @FXML
    private Label total;

    @FXML
    void clear(MouseEvent event) {

    }

    @FXML
    void clearSearch(ActionEvent event) {

    }

    @FXML
    void delete(MouseEvent event) {

    }

    @FXML
    void paid(MouseEvent event) {

    }

}
