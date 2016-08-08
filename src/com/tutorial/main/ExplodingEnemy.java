package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;

public class ExplodingEnemy extends GameObject{
	
	private Handler handler;
	public int health = 5;
	private Color color;

	public ExplodingEnemy(int x, int y, int velX, int velY, Color color, ID id, Handler handler) {
		super(x, y, id);
		this.velX = velX;
		this.velY = velY;
		this.color = color;
		this.handler = handler;
	
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT-36) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH-16) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, 0.02f, handler));	
		
	}
	
	public void remove() {
		handler.removeObject(this);
	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int)x,(int) y, 16, 16);
	}
	
	public void explode() {
		System.out.println("hello");
		handler.addObject(this);
		handler.addObject(this);
		
	}

}
