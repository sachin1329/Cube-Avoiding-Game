package com.tutorial.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Tracker {
	
	public String playerName;
	public String playerColor;
	public int XP;
	public int level;
	public int health;
	public int speed;
	public int size;
	public int highScore;
	
	public String name = "playerInfo.txt";
	private File file = new File(name);
	private Scanner input;
	private String words = "";
	private String comma = ",";
	private String tracker;
	private int counter = 0;
	public FileWriter fileWriter;
		
	public Tracker() {
		try{
			input = new Scanner(file);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		playerReader();
	}
	
	public void playerReader() {
		while(input.hasNextLine()) {
			words += input.nextLine(); 
		}
		StringTokenizer st = new StringTokenizer(words, comma);
		
		while(st.hasMoreElements()) {
			tracker = (String)st.nextElement();
			
			if(counter > 7)
				counter = 0;
			
			counter++;
			
			switch(counter) { 
			
			case 1:
				if(tracker.length() > 10) {
					playerName = tracker.substring(0, 10);
				}else playerName = tracker;
				break;
			case 2:
				playerColor = tracker;				
				break;
			case 3:
				int xpInt = Integer.parseInt(tracker);
				XP = xpInt;
				break;
			case 4:
				int levelInt = Integer.parseInt(tracker);
				level = levelInt;
				break;
			case 5:
				int healthInt = Integer.parseInt(tracker);
				health = healthInt;
				break;
			case 6:
				int speedInt = Integer.parseInt(tracker);
				speed = speedInt;
				break;
			case 7:
				int sizeInt = Integer.parseInt(tracker);
				size = sizeInt;
				break;
			case 8:
				int highScoreInt = Integer.parseInt(tracker);
				highScore = highScoreInt;
			case 9:
				break;
			
			}
			
		}
	
	}
	
	public void update() {
		PrintWriter writer;
		
		try{
			writer = new PrintWriter(file);
			writer.print("");
			writer.close();
			
			try{
				fileWriter = new FileWriter(name, true);
				fileWriter.append(playerName + "," + playerColor + "," + XP + "," + level + "," + health + "," + speed + "," + size + "," + highScore);
				fileWriter.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public int getSpeed() {
		return speed;
	}

}
