package com.itay_peleg.game.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Save {
	
	public static void save(int level) {
		try(PrintWriter out = new PrintWriter("config.data" )  ){
		    out.println(level);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static int load() {
		try {
			@SuppressWarnings("resource")
			BufferedReader in = new BufferedReader(new FileReader(new File("config.data")));
			return Integer.parseInt(in.readLine());
		} catch (IOException e) {
			save(0);
			e.printStackTrace();
		}
		return 0;
	}
	
}
