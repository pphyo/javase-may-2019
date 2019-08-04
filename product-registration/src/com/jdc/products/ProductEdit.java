package com.jdc.products;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.products.model.Category;
import com.jdc.products.model.Product;
import com.jdc.products.model.ProductModel;
import com.jdc.products.model.Size;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProductEdit implements Initializable{
	
	@FXML
	private TextField name;
	@FXML
	private ComboBox<Category> category;
	@FXML
	private ComboBox<Size> size;
	@FXML
	private TextField price;
	@FXML
	private TextArea remark;
	@FXML
	private Label message;
	@FXML
	private Label title;
	
	private Reloader reloader;
	
	private Product product;
	
	public void setProduct(Product product) {
		this.product = product;
		
		if(null != product) {
			name.setText(product.getName());
			category.setValue(product.getCategory());
			size.setValue(product.getSize());
			price.setText(String.valueOf(product.getPrice()));
			remark.setText(product.getRemark());
			title.setText("Edit Product");
		} else {
			title.setText("Add New Product");
		}
	}

	public void save() {
		
		try {
			// create product with inputs from view
			if(null == product) {
				product = new Product();
			}
			
			product.setName(name.getText());
			if(product.getName().isEmpty()) {
				throw new RuntimeException("Please enter Product name.");
			}
			
			product.setCategory(category.getValue());
			if(null == product.getCategory()) {
				throw new RuntimeException("Please select Category");
			}
			
			product.setSize(size.getValue());
			if(null == product.getSize()) {
				throw new RuntimeException("Please select size.");
			}
			
			product.setPrice(Integer.parseInt(price.getText()));
			
			product.setRemark(remark.getText());
			
			// save by model
			ProductModel.getModel().save(product);
			
			// reload list view
			reloader.reload();
			
			// close window
			close();
			
		} catch (NumberFormatException e) {
			message.setText("Please enter price with digit.");
		} catch (Exception e) {
			message.setText(e.getMessage());
		}
		
	}
	
	public void close() {
		message.getScene().getWindow().hide();
	}

	public static void show(Reloader reloader, Product p) {

		try {
			Stage stage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(ProductEdit.class.getResource("ProductEdit.fxml"));
			Parent root = fxmlLoader.load();
			
			ProductEdit controller = fxmlLoader.getController();
			controller.reloader = reloader;
			controller.setProduct(p);
			
			Scene scene = new Scene(root);
			
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.UNDECORATED);
			
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		category.getItems().addAll(Category.values());
		size.getItems().addAll(Size.values());
	}
	
	public static interface Reloader {
		void reload();
	}
}
