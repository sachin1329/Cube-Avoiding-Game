package com.tutorial.main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Health extends GameObject{
	
	private float alpha = 1;
	private float life;
	private String message;
	private Color color;
	
	private Handler handler;
	
	public Health(String message, float x, float y, ID id, Color color, float life, Handler handler) {
		super(x, y, id);
		this.life = life;
		this.handler = handler;
		this.message = message;
		this.color = color;
		
	}

	public void tick() {
		if(alpha > life) {
			alpha -= (life - 0.001f);
		}else handler.removeObject(this);
		
	}

	public void render(Graphics g) {
		Graphics g2d = (Graphics2D) g;
		((Graphics2D) g2d).setComposite(makeTransparent(alpha));
		
		g.setFont(new Font("arial" , 1, 25));
		g.setColor(color);
		g.drawString(message, (int)x, (int)y);
		
		((Graphics2D) g2d).setComposite(makeTransparent(1));
		
	}
	
	private AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha));
	}

}
