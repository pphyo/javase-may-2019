package com.jdc.streams;

import java.util.Optional;

public class OptionalDemo {
	
	public static void main(String[] args) {
		printLength(Optional.of("Hello"));
		printLength(Optional.empty());
	}

	static void printLength(Optional<String> str) {
		String def = str.orElse("Default");
		System.out.println(def.length());
	}
}
