package com.jdc.nio2;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class PathCreation {

	public static void main(String[] args) throws IOException {
		
		Path home = FileSystems.getDefault().getPath("home");
		Path data = Paths.get("data");
		
		System.out.println(home.compareTo(data));
		
		Files.createDirectory(home);
		Files.write(data, Arrays.asList("Hello Java"), 
				StandardOpenOption.CREATE_NEW);
		
		Path resolve = home.resolve(data);
		
		Files.createFile(resolve);
		
		System.out.println(resolve);
		System.out.println(data);
		
		Path sibling = resolve.resolveSibling("sibling");
		
		System.out.println(sibling);
		System.out.println(data.compareTo(resolve));
	}
}
