package com.jdc.robot;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class MrBot {
	
	private static final String FILE = "memory.obj";

	private static MrBot bot;
	
	public static MrBot getBot() {
		
		if(null == bot) {
			bot = new MrBot();
		}
		
		return bot;
	}
	
	private Map<String, String> dictionary;
	
	private String last;
	
	public String getTalkType() {
		return last == null ? "Asking" : "Teaching";
	}
	
	private MrBot() {
		
		dictionary = readMemory();
		
		if(null == dictionary) {
			dictionary = new HashMap<String, String>();
			dictionary.put("name", "Mr Bot");
			dictionary.put("your name", "Mr Bot");
			dictionary.put("what is your name", "Mr Bot");
		}
	}

	@SuppressWarnings("unchecked")
	private Map<String, String> readMemory() {
		
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
			return (Map<String, String>) in.readObject();
		} catch (Exception e) {
			System.out.println("First Time");
		}
		
		return null;
	}

	public String talk(String text) {
		
		if(null != last) {
			dictionary.put(last.toLowerCase().trim(), text);
			last = null;
			return "Thank you very much.";
		}
		
		String result = dictionary.get(text.toLowerCase().trim());
		
		if(null == result) {
			last = text;
			return "Please teach me.";
		}
		
		return result;
	}
	
	public void saveMemory() {
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
			out.writeObject(dictionary);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
