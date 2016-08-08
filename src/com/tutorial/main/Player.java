package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.lang.reflect.Field;
import java.util.Random;

public class Player extends GameObject{

	Random r = new Random();
	Handler handler;
	private int coinCntr = 1000;
	private HUD hud;
	private Tracker tracker = new Tracker();
	private Color color;
	
	int timer = 1000;
	
	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		
		 try{
			 Field field = Class.forName("java.awt.Color").getField(tracker.playerColor);
			 color = (Color)field.get(null);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
	}
	
	public Rectangle getBounds() {	
		return new Rectangle((int)x, (int)y,tracker.size, tracker.size);
	}
	
public void tick() {
	
	x+= velX;
	y+= velY;
	
	x = Game.clamp(x, 0, Game.WIDTH-tracker.size);
	y = Game.clamp(y, 0, Game.HEIGHT-tracker.size -20);
	
	handler.addObject(new Trail(x, y, ID.Trail,color, tracker.size, tracker.size, 0.08f, handler));
	
	timer--;
	
	collision();
	

}

public void collision() {
	for(int i = 0; i < handler.object.size(); i++) {
		
		GameObject tempObject = handler.object.get(i);
		
		if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.StraightEnemy) {
			
			// collision code
			if(getBounds().intersects(tempObject.getBounds())) {
				HUD.healthLevel -=2;
				
			}
		}
		
		
	}
}

public void render(Graphics g) {	
	g.setColor(color);
	//draw Player
	g.fillRect((int)x,(int)y, tracker.size, tracker.size);
	
	//determine color of text
	if(tracker.playerColor.equals("white"))
		g.setColor(Color.lightGray);
	else g.setColor(Color.white);
	g.drawString(tracker.playerName, (int)x, (int)y - 4);
	
	
	for(int i = 0; i < handler.object.size(); i++) {
		
		GameObject tempObject = handler.object.get(i);
		
		if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.StraightEnemy) {
			
			// collision code
			
			if(getBounds().intersects(tempObject.getBounds())) {
				handler.addObject(new Health("-2", x, y - 10, ID.Health, new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)), 0.02f, handler));
				
			}
		}
		
		
	}
	
	
	}
}