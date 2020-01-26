package mainApp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Mylogger {
	private String logfile;
	
	public Mylogger(String filepath) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Date date = new Date();
		String formattedDate = dateFormat.format(date);
				
		File log = new File(filepath + formattedDate + "_log.txt");
		initFilePath(filepath + formattedDate + "_log.txt");
	}
	
	public void write(int Type, String message) {
		switch(Type)
		{
			case 0:
				writelog("info",message);
				break;
			case 1:
				writelog("debug",message);
				break;
			case 2:
				writelog("warning",message);
				break;
			case 3:
				writelog("error",message);
				break;
			case 4:
				writelog("fatal",message);
				break;
		}
	}
	
	public void write(int Type, String message, int level) {
		switch(Type)
		{
			case 0:
				writelog("info", message, level);
				break;
			case 1:
				writelog("debug", message, level);
				break;
			case 2:
				writelog("warning", message, level);
				break;
			case 3:
				writelog("error", message, level);
				break;
			case 4:
				writelog("fatal", message, level);
				break;
		}
	}

	public void write(int Type, String message, int level, Date date) {
		switch(Type)
		{
			case 0:
				writelog("info", message, level, date);
				break;
			case 1:
				writelog("debug", message, level, date);
				break;
			case 2:
				writelog("warning", message, level, date);
				break;
			case 3:
				writelog("error", message, level, date);
				break;
			case 4:
				writelog("fatal", message, level, date);
				break;
		}
	}
	
	private void initFilePath(String filepath) {
		this.logfile = filepath;
	}
	
	private void writelog(String type, String message, int level, Date date) {
		try {
			FileWriter fr = new FileWriter(this.logfile, true);
			fr.write(type + " - " + level + " : " + message + "\n");
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writelog(String type, String message, int level) {
		try {
			FileWriter fr = new FileWriter(this.logfile, true);
			fr.write(type + " - " + level + " : " + message + "\n");
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writelog(String type, String message) {
		try {
			FileWriter fr = new FileWriter(this.logfile, true);
			fr.write(type + " : " + message + "\n");
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static class Type{
		public final int info = 0;
		
		public final int debug = 1;
		
		public final int warning = 2;
		
		public final int error = 3;
		
		public final int fatal = 4;
	}
}
