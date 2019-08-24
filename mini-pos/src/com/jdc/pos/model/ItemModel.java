package com.jdc.pos.model;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
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

}
