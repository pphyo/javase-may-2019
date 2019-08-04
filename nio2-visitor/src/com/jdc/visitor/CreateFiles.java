package com.jdc.visitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class CreateFiles {

	public static void main(String[] args) throws IOException {
		
		// home
		Path home = Paths.get("home"); 
		Files.createDirectory(home);
		
		// document
		Path document = home.resolve("document");
		Files.createDirectory(document);
		
		// document/doc1.txt
		Path doc1 = document.resolve("doc1.txt");
		Files.createFile(doc1);
		
		// document/doc2.txt
		Path doc2 = doc1.resolveSibling("doc2.txt");
		Files.write(doc2, Arrays.asList("Hello Java"), StandardOpenOption.CREATE_NEW);
		
		
		// static
		Path statics = home.resolve("static");
		Files.createDirectory(statics);

		// static/st.txt
		Path staticsTxt = statics.resolve("st.txt");
		Files.createFile(staticsTxt);
		
		// static/sub
		Path staticSub = statics.resolve("sub");
		Files.createDirectory(staticSub);
		
		// static/sub/sub.txt
		Path subText = staticSub.resolve("sub.txt");
		Files.write(subText, Arrays.asList("Hello Java"), StandardOpenOption.CREATE_NEW);
		
	}
}
