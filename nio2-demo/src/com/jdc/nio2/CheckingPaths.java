package com.jdc.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckingPaths {

	public static void main(String[] args) throws IOException {
		
		// exit or not
		Path home = Paths.get("home");
		Path data = Paths.get("data");
		
		Path some = Paths.get("some");
		Path data2 = data.toAbsolutePath();
		System.out.println(data2);
		
		System.out.println("exit home > " + Files.exists(home));
		System.out.println("exit data > " + Files.exists(data));
		System.out.println("exit some > " + Files.exists(some));
		
		System.out.println("readable home > " + Files.isReadable(home));
		System.out.println("writable home > " + Files.isWritable(home));
		System.out.println("executable home > " + Files.isExecutable(home));
		
		System.out.println(Files.isSameFile(data, data2));
		
	}
}
