package com.jdc.visitor;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class CopyFileDemo {
	
	public static void main(String[] args) {
		
		String user = System.getProperty("user.home");
		File desktop = new File(user, "Desktop");

		File from = new File(desktop, "02.Say Hello.mp4");
		File to = new File(desktop, "out_io.mp4");
		
		Path pathFrom = from.toPath();
		Path pathTo = pathFrom.resolveSibling("out_nio.mp4");
		
		// copy with nio  2
		copy(pathFrom, pathTo, new CopyFileWithNio2());

		// copy with legacy io
		copy(from, to, new CopyFileWithIO());
	}
	
	static <T> void  copy(T from, T to, Copyer<T> copyer) {
		long time = copyer.copy(from, to);
		System.out.println(time);
	}

	static interface Copyer<T> {
		long copy(T from, T to);
		
		default long diff(LocalTime start, LocalTime end) {
			return ChronoUnit.MILLIS.between(start, end);
		}
	}
	
	
}
