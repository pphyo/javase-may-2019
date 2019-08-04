package com.jdc.visitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalTime;

import com.jdc.visitor.CopyFileDemo.Copyer;

public class CopyFileWithIO implements Copyer<File>{

	@Override
	public long copy(File from, File to) {
		LocalTime start = LocalTime.now();
		
		try(FileInputStream in = new FileInputStream(from);
				FileOutputStream out = new FileOutputStream(to)) {
			
			int b = 0;
			
			while((b = in.read()) != -1) {
				out.write(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		LocalTime end = LocalTime.now();
		
		return diff(start, end);
	}

}
