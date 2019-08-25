package com.jdc.pos.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.jdc.pos.model.entity.Item;

public class ItemModel {
	
	private static final String FILE = "items.obj";

	private static ItemModel instance;
	
	private List<Item> items;
	
	@SuppressWarnings("unchecked")
	private ItemModel() {
		
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
			items = (List<Item>) in.readObject();
		} catch (Exception e) {
			items = new ArrayList<>();
		}

	}

	public static ItemModel getInstance() {
		
		if(null == instance) {
			instance = new ItemModel();
		}
		
		return instance;
	}

	public void add(Item item) {
		items.add(item);
	}

	public List<String> getCategories() {
		return items.stream()
				.map(item -> item.getCategory())
				.distinct()
				.sorted()
				.collect(Collectors.toList());
	}

	public List<Item> search(String category, String item) {
		
		Predicate<Item> pred = a -> true;
		
		if(null != category) {
			pred = pred.and(a -> a.getCategory().equals(category));
		}
		
		if(null != item  && !item.isEmpty()) {
			pred = pred.and(a -> a.getName().toLowerCase()
					.startsWith(item.toLowerCase()));
		}
		
		return items.stream().filter(pred).collect(Collectors.toList());
	}

	public void save() {

		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))){
			out.writeObject(items);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
