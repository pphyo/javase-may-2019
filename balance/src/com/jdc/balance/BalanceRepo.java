package com.jdc.balance;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum BalanceRepo {
	
	Instance;
	
	private List<Balance> list;
	private static final String fileName = "balances.txt";
	
	private BalanceRepo() {
		try {
			list = new ArrayList<>();
			Path path = Paths.get(fileName);
			List<String> lines = Files.readAllLines(path);
			
			for(String line  : lines) {
				list.add(new Balance(line));
			}
		} catch (Exception e) {
			System.out.println("First time");
		}
	}
	
	public List<Balance> getList() {
		return list;
	}
	
	public void add(Balance b) {
		list.add(b);
	}
	
	public void save() {
		try {
			List<String> lines = list.stream().map(b -> b.toString())
					.collect(Collectors.toList());
			Path path = Paths.get(fileName);
			
			Files.write(path, lines, StandardOpenOption.CREATE_NEW);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
