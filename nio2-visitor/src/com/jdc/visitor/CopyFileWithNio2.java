package com.jdc.visitor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;

import com.jdc.visitor.CopyFileDemo.Copyer;

public class CopyFileWithNio2 implements Copyer<Path>{

	@Override
	public long copy(Path from, Path to) {
		
		LocalTime start = LocalTime.now();
		
		try {
			Files.copy(from, to);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		LocalTime end = LocalTime.now();
		return diff(start, end);
	}

}
