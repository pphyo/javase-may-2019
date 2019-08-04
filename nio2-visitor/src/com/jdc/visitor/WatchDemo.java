package com.jdc.visitor;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.*;

public class WatchDemo {

	public static void main(String[] args) throws IOException {
		
		System.out.println("Start Watching!");
		Path path  = Paths.get("home", "static", "sub");
		WatchService watcher = FileSystems.getDefault().newWatchService();

		path.register(watcher, 
				ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		
		while (true) {

			WatchKey key = null;
			
			try {
				key = watcher.take();
			} catch (Exception e) {
				return;
			}
			
			if(null == key) {
				continue;
			}
			
			for(WatchEvent<?> event : key.pollEvents()) {
				
				WatchEvent.Kind<?> kind = event.kind();
				
				if(kind == OVERFLOW) {
					continue;
				}
				
				if(kind == ENTRY_DELETE) {
					Path context = (Path) event.context();
					System.out.printf("%s has been deleted!%n", context.getFileName());
				}
				
				if(kind == ENTRY_MODIFY) {
					Path context = (Path) event.context();
					System.out.printf("%s has been modified at %s!%n", context.getFileName(), 
							LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
					
					if(!Files.isDirectory(context) && !Files.isSymbolicLink(context)) {
						List<String> lines = Files.readAllLines(path.resolve(context));
						
						for(String line : lines) {
							System.out.println(line);
						}
					}
				}
				
				if(kind == ENTRY_CREATE) {
					Path context = (Path)event.context();
					System.out.printf("%s has been created at %s!%n", context.getFileName(), 
							LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
				}
			}
			
			if(!key.reset()) {
				System.out.printf("%s can't watch anymoresd at %s!%n", path.getFileName(), 
						LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
				break;
			}
		}
		
	}
}
