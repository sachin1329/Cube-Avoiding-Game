package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{
	
	private Handler handler;
	private GameObject player;
	private int speedFactor;
	private Color color;
	
	public SmartEnemy(int x, int y, int speedFactor, Color color, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.speedFactor = speedFactor;
		this.color = color;
		
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
		}
		
		
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		float diffX = x-player.getX();
		float diffY = y-player.getY();
		float distance = (float) Math.sqrt((x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()) );
					
		velX = speedFactor * ((-2/distance) * diffX);
		velY = speedFactor * ((-2/distance) * diffY);
		
		
		//if(y <= 0 || y >= Game.HEIGHT-36) velY *= -1;
		//if(x <= 0 || x >= Game.WIDTH-16) velX *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail, color , 16, 16, 0.02f, handler));
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}

}
