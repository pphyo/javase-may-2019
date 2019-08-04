package com.jdc.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CopyDemo {

	public static void main(String[] args) throws IOException {
		Path copy = Paths.get("copy");
		Files.createDirectories(copy);
		
		Path data = Paths.get("data");
		Path home = Paths.get("home");
		
		Path copyData = copy.resolve(data);
		Files.copy(data, copyData);
		
		Path copyHome = copy.resolve(home);
		Files.copy(home, copyHome);
	}
}
