package com.jdc.lambda;

import java.util.ArrayList;
import java.util.List;

public class ForEachDemo {
	
	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<>();
		
		for(int i=1; i <= 10; i ++) {
			list.add(i);
		}
		
		list.forEach(a -> System.out.println(a * 2));
	}

}
