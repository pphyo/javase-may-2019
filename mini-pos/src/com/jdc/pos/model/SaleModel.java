package com.jdc.pos.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.jdc.pos.model.entity.Sale;
import com.jdc.pos.model.entity.SaleDetail;

public class SaleModel {

	private static final String FILE = "sale.obj";

	private static SaleModel instance;
	
	private List<Sale> list;
	private int id;
	
	@SuppressWarnings("unchecked")
	private SaleModel() {
		
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
			list = (List<Sale>) in.readObject();
			
			if(list.size() > 0) {
				id = list.get(list.size() - 1).getId();
			}
		} catch (Exception e) {
			list = new ArrayList<>();
		}
	}
	
	public static SaleModel getInstance() {
		
		if(null == instance) {
			instance = new SaleModel();
		}
		
		return instance;
	}
	
	public void save() {
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
			out.writeObject(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void create(Sale sale) {
		sale.setId(++ id);
		sale.setSaleDate(LocalDate.now());
		sale.setSaleTime(LocalTime.now());
		
		list.add(sale);
	}

	public List<SaleDetail> search(String category, String item, LocalDate from, LocalDate to) {
		
		if(null != from && null != to && from.compareTo(to) > 0) {
			throw new RuntimeException("Please set Correct Date Values.");
		}
		
		Predicate<SaleDetail> pred = s -> true;
		
		if(null != category) {
			pred = pred.and(s -> s.getCategory().equals(category));
		}
		
		if(null != item && !item.isEmpty()) {
			pred = pred.and(s -> s.getItem().toLowerCase().startsWith(item.toLowerCase()));
		}
		
		if(null != from) {
			pred = pred.and(s -> s.getSale().getSaleDate().compareTo(from) >= 0);
		}
		
		if(null != to) {
			pred = pred.and(s -> s.getSale().getSaleDate().compareTo(to) <= 0);
		}

		return list.stream().flatMap(s -> s.getDetails().stream())
				.filter(pred).collect(Collectors.toList());
	}

	public Map<String, Integer> getPieData(String category, LocalDate from, LocalDate to) {
		
		Map<String, List<SaleDetail>> group = null;
		
		Predicate<Sale> filter = a -> true;
		
		if(null != from) {
			filter = filter.and(a -> a.getSaleDate().compareTo(from) >= 0);
		}
		
		if(null != to) {
			filter = filter.and(a -> a.getSaleDate().compareTo(to) <= 0);
		}
		
		if(null == category) {
			group = list.stream()
					.filter(filter)
					.flatMap(s -> s.getDetails().stream())
					.collect(Collectors.groupingBy(SaleDetail::getCategory));
		} else {
			group = list.stream()
					.filter(filter)
					.flatMap(s -> s.getDetails().stream())
					.filter(s -> s.getCategory().equals(category))
					.collect(Collectors.groupingBy(SaleDetail::getItem));
		}
		
		Map<String, Integer> result = new TreeMap<>();
		
		for(Entry<String, List<SaleDetail>> e : group.entrySet()) {
			result.put(e.getKey(), e.getValue().stream().mapToInt(a -> a.getTotal()).sum());
		}
		
		return result;
	}

	public Map<String, Map<String, Integer>> getDailySales(String category, LocalDate from, LocalDate to) {
		
		LocalDate target = from;
		
		while(target.compareTo(to) <= 0) {
			
			target = target.plusDays(1);
		}
		
		return new HashMap<String, Map<String,Integer>>();
	}

	
}
