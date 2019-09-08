package com.jdc.pos.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;

import com.jdc.pos.model.ItemModel;
import com.jdc.pos.model.SaleModel;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class SaleReport implements Initializable {

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

		category.valueProperty().addListener((a, b, c) -> loadData());
		from.valueProperty().addListener((a, b, c) -> loadData());
		to.valueProperty().addListener((a, b, c) -> loadData());

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

		if (search) {

			SummaryDataService summary = new SummaryDataService();

			summary.setOnSucceeded(event -> {
				
				pieChart.getData().clear();
				
				Map<String, Integer> data = summary.getValue();
				data.entrySet().stream().map(d -> new PieChart.Data(d.getKey(), d.getValue().doubleValue()))
						.forEach(pd -> pieChart.getData().add(pd));
			});

			summary.start();

			DailyDataService daily = new DailyDataService();
			
			daily.setOnSucceeded(event -> {
				
				barChart.getData().clear();
				
				Map<String, Map<String, Integer>> data = daily.getValue();
				data.entrySet().forEach(e -> {
					Series<String, Integer> series = new Series<>();
					series.setName(e.getKey());

					e.getValue().entrySet().stream().map(d -> new XYChart.Data<>(d.getKey(), d.getValue()))
							.forEach(series.getData()::add);

					barChart.getData().add(series);

				});				
			});
			
			daily.start();

		}

	}

	private class SummaryDataService extends Service<Map<String, Integer>> {

		@Override
		protected Task<Map<String, Integer>> createTask() {

			return new Task<Map<String, Integer>>() {

				@Override
				protected Map<String, Integer> call() throws Exception {
					return SaleModel.getInstance().getPieData(category.getValue(), from.getValue(), to.getValue());
				}
			};
		}

	}
	
	private class DailyDataService extends Service<Map<String, Map<String, Integer>>> {

		@Override
		protected Task<Map<String, Map<String, Integer>>> createTask() {

			return new Task<Map<String,Map<String,Integer>>>() {

				@Override
				protected Map<String, Map<String, Integer>> call() throws Exception {
					return SaleModel.getInstance().getDailySales(category.getValue(),
							from.getValue(), to.getValue());
				}
			};
		}
		
	}

}
