package com.jdc.pos.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;

import com.jdc.pos.model.ItemModel;
import com.jdc.pos.model.SaleModel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class SaleReport implements Initializable{

	@FXML
	private ComboBox<String> category;
	@FXML
	private DatePicker from;
	@FXML
	private DatePicker to;
	
	@FXML
	private BarChart<String, Integer> barChart;
	@FXML
	private PieChart pieChart;
	
	private boolean search;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		category.getItems().addAll(ItemModel.getInstance().getCategories());
		
		to.setValue(LocalDate.now());
		from.setValue(to.getValue().minusMonths(1));
		
		category.valueProperty().addListener((a,b,c) -> loadData());
		from.valueProperty().addListener((a,b,c) -> loadData());
		to.valueProperty().addListener((a,b,c) -> loadData());
		
		to.setValue(LocalDate.now());
		from.setValue(to.getValue().minusMonths(1));
		
		search = true;
		
		loadData();
	}
	
	public void clear() {
		search = false;
		category.setValue(null);
		to.setValue(LocalDate.now());
		from.setValue(to.getValue().minusMonths(1));
		search = true;
		
		loadData();
	}

	private void loadData() {
		
		if(search) {
			
			loadPieChartData();
			
			loadBarChartData();
			
			loadSammaryData();
		}

	}

	private void loadSammaryData() {
		// TODO Auto-generated method stub
		
	}

	private void loadPieChartData() {

		pieChart.getData().clear();

		Map<String, Integer> data = SaleModel.getInstance().getPieData(category.getValue(), from.getValue(), to.getValue());
	
		data.entrySet().stream()
			.map(d ->  new PieChart.Data(d.getKey(), d.getValue().doubleValue()))
			.forEach(pd -> pieChart.getData().add(pd));
		
	}

	private void loadBarChartData() {
		// TODO Auto-generated method stub
		
	}
	
}
