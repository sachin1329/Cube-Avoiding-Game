package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.Random;

import com.tutorial.main.Game.STATE;

public class Store extends MouseAdapter{

	Tracker tracker = new Tracker();
	Handler handler = new Handler();
	private Color color;
	
	public Store() {
		
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		//back button
		if(mouseOver(mx, my, Game.WIDTH/2 - 35, Game.HEIGHT - 100 , 90, 50)) {
			Game.gameState = STATE.Menu;
		}
				
		//blue
		if(mouseOver(mx, my, 400, 27, 35, 35)) {
			tracker.playerColor = "blue";
			tracker.update();
		}
		
		//red
		if(mouseOver(mx, my, 470, 27, 35, 35)) {
			tracker.playerColor = "red";
			tracker.update();
		}
		
		//green 
		if(mouseOver(mx, my,400, 97, 35, 35)) {
			tracker.playerColor = "green";
			tracker.update();
		}
		
		//yellow
		if(mouseOver(mx,my,470, 97, 35, 35)) {
			tracker.playerColor = "yellow";
			tracker.update();
			
		}
		
		//orange
		if(mouseOver(mx,my,400, 167, 35, 35)) {
			tracker.playerColor = "orange";
			tracker.update();
		}
		
		//gray
		if(mouseOver(mx, my, 470, 167, 35, 35)) {
			tracker.playerColor = "gray";
			tracker.update();
		}
		
		//white
		if(mouseOver(mx,my,400, 237, 35, 35)) {
			tracker.playerColor = "white";
			tracker.update();
		}
		
		//minus Size
		if(mouseOver(mx, my, 120, 233, 20, 20)) {
			tracker.size -= 5;
			tracker.update();
		}
		
		//plus speed
		if(mouseOver(mx, my, 120, 273, 20, 20)) {
			tracker.speed += 10;
			tracker.update();
		}
		
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		
		 try{
			 Field field = Class.forName("java.awt.Color").getField(tracker.playerColor);
			 color = (Color)field.get(null);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		
		
		 //back button
		 g.setColor(Color.white);
		 g.drawRect(Game.WIDTH/2 - 35, Game.HEIGHT - 100 , 90, 50);
		 g.setFont(new Font("arial", 1, 20));
		 g.drawString("Back", Game.WIDTH/2 - 15, Game.HEIGHT - 70);
		 g.setFont(new Font("arial", 1, 12));
		 
		//simulate player
		g.setColor(Color.BLACK);
		g.fillRect(1, 1, 549, 349);
		g.setColor(Color.white);
		g.drawRect(0, 0, 550, 350);
		g.setColor(color);
		g.fillRect(150, 250 - tracker.size - 30, tracker.size, tracker.size);
		
		 //size and speed
		 g.setColor(Color.white);
		 g.setFont(new Font("arial", 1, 20));
		 
		 //plus and minues size and speed 
		 g.drawRect(120, 233, 20, 20);
		 g.drawString("-", 126, 248);
		 g.drawString("Size: " + tracker.size, 150, 250);
		 g.drawRect(120, 273, 20, 20);
		 g.drawString("+", 125, 290);
		 g.drawString("Speed: " + tracker.speed , 150, 290);
		
		//color options
		//blue
		g.drawString("Blue", 400, 20);
		g.setColor(Color.blue);
		g.fillRect(400, 27, 35, 35);
		
		//red
		g.setColor(Color.WHITE);
		g.drawString("Red", 470, 20);
		g.setColor(Color.red);
		g.fillRect(470, 27, 35, 35);
		
		//green
		g.setColor(Color.white);
		g.drawString("Green", 400, 90);
		g.setColor(Color.green);
		g.fillRect(400, 97, 35, 35);
		
		//yellow
		g.setColor(Color.white);
		g.drawString("Yellow", 470, 90);
		g.setColor(Color.yellow);
		g.fillRect(470, 97, 35, 35);
		
		//orange
		g.setColor(Color.white);
		g.drawString("Orange", 400, 160);
		g.setColor(Color.orange);
		g.fillRect(400, 167, 35, 35);
		
		//gray
		g.setColor(Color.white);
		g.drawString("Gray", 470, 160);
		g.setColor(Color.gray);
		//purple
		//g.setColor(new Color(155, 0, 175));
		g.fillRect(470, 167, 35, 35);
		
		//white
		g.setColor(Color.WHITE);
		g.drawString("White", 400, 230);
		g.fillRect(400, 237, 35, 35);
		

	}
	

		
	}
