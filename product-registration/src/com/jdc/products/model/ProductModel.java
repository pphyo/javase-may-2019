package com.jdc.products.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductModel {

	private static final String FILE = "products.obj";

	private static ProductModel model;

	private List<Product> list;

	public static ProductModel getModel() {

		if (model == null) {
			model = new ProductModel();
		}
		return model;
	}

	private ProductModel() {
		list = readFromFile();

		if (null == list) {
			list = new ArrayList<Product>();
		}
	}

	@SuppressWarnings("unchecked")
	private List<Product> readFromFile() {
		
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
			return (List<Product>) in.readObject();
		} catch (Exception e) {
			System.out.println("First Time");
		}
		
		return null;
	}

	public void save(Product p) {
		if(!list.contains(p)) {
			
			
			
			list.add(p);
		}
	}

	public void saveToFile() {

		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
			out.writeObject(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Product> search(Category c, String name) {

		Predicate<Product> filter = t -> true;

		if (null != c) {
			filter = filter.and(t -> t.getCategory() == c);
		}

		if (!name.isEmpty()) {
			filter = filter.and(t -> t.getName().toLowerCase().startsWith(name.toLowerCase()));
		}

		return list.stream().filter(filter).collect(Collectors.toList());
	}

	public void delete(Product p) {
		list.remove(p);
	}
}
