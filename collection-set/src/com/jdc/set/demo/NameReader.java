package com.jdc.set.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class NameReader {
	
	public static void main(String[] args) {
		
		// read file
		try(BufferedReader br = new BufferedReader(new FileReader("names.txt"))) {
			
			Set<String> rawSet = new HashSet<>();
			String line = null;
			
			while(null != (line = br.readLine())) {
				rawSet.add(line);
			}
			
			System.out.println(rawSet);
			
			Set<String> sortSet = new TreeSet<String>(rawSet);
			System.out.println(sortSet);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
