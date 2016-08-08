package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{
	
	private Handler handler;
	private Color color;

	public BasicEnemy(int x, int y, int velX, int velY, Color color, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		this.color = color;
		this.velX= velX;
		this.velY= velY;
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT-36) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH-16) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, color, 16, 16, 0.02f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x,(int) y, 16, 16);
		
	}

}
