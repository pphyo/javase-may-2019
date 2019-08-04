package com.jdc.set.demo;

import java.io.File;
import java.util.Map;
import java.util.Properties;
import java.util.function.BiConsumer;

public class FileTest {

	public static void main(String[] args) {
		
		BiConsumer<String, String> cons = (a, b) -> System.out.format("%s -> %s%n", a, b);
		
		Map<String, String> map  = System.getenv();
		map.entrySet().forEach(e -> cons.accept(e.getKey(), e.getValue()));
		
		Properties prop = System.getProperties();
		
		prop.stringPropertyNames().forEach(k -> cons.accept(k, prop.getProperty(k)));
		
		File desktop = new File(System.getProperty("user.home"), "Desktop");
		System.out.println(desktop.exists());
	}

}
