package com.jdc.visitor;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class VisitDemo {
	
	public static void main(String[] args) throws IOException {
		
		Path home = Paths.get("home");
		
		class HelloVisitor extends SimpleFileVisitor<Path> {
			
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

				System.out.println(file);
				
				List<String> lines = Files.readAllLines(file);
				if(lines.contains("Hello Java")) {
					System.out.println("Contains Hello Java");
					return FileVisitResult.CONTINUE;
				} else {
					System.out.println("Does not Contains Hello Java");
				}
				
				return FileVisitResult.CONTINUE;
			}
		}
		
		Files.walkFileTree(home, new HelloVisitor());
	}

}
