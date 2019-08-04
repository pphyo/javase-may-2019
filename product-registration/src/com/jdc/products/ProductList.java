package com.jdc.products;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.ResourceBundle;

import com.jdc.products.model.Category;
import com.jdc.products.model.Product;
import com.jdc.products.model.ProductModel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ProductList implements Initializable{
	
	@FXML
	private ComboBox<Category> category;
	@FXML
	private TextField name;
	@FXML
	private TableView<Product> table;

	
	public void upload() {
		
		try {
			FileChooser fc = new FileChooser();
			fc.setTitle("Select File");
			fc.getExtensionFilters().add(new ExtensionFilter("CSV File", "*.csv", "*.txt"));
			fc.setInitialDirectory(new File(System.getProperty("user.home"), "Desktop"));
			
			File file = fc.showOpenDialog(name.getScene().getWindow());
			
			if(null != file) {
				Files.lines(file.toPath())
					.skip(1)
					.map(line -> line.split("\t"))
					.filter(array -> array.length == 5)
					.map(Product::new)
					.forEach(p -> ProductModel.getModel().save(p));
				
				search();
			}
			
		} catch (Exception e) {
			Dialog<String> dialog = new Dialog<String>();
			dialog.setTitle("Error");
			dialog.setContentText("Please select correct file.");
			dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
			dialog.show();
		}

	}
	
	
	public void search() {
		table.getItems().clear();
		List<Product> list = ProductModel.getModel().search(category.getValue(), name.getText());
		table.getItems().addAll(list);
	}
	
	public void addNew() {
		ProductEdit.show(this::search, null);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		category.getItems().addAll(Category.values());
		
		MenuItem delete = new MenuItem("Delete");
		delete.setOnAction(e -> {
			Product p = table.getSelectionModel().getSelectedItem();
			ProductModel.getModel().delete(p);
			search();
		});
		
		MenuItem edit = new MenuItem("Edit");
		edit.setOnAction(e -> {
			Product p = table.getSelectionModel().getSelectedItem();
			ProductEdit.show(this::search, p);
		});
		
		ContextMenu contextMenu = new ContextMenu(edit, delete);
		table.setContextMenu(contextMenu);
		
		search();
	}

}
