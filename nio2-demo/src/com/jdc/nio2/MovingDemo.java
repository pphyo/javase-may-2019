package com.jdc.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MovingDemo {

	public static void main(String[] args) throws IOException {
		
		Path copy = Paths.get("copy");
		Path move = Paths.get("move");
		
		Files.move(copy, move);
	}
}
