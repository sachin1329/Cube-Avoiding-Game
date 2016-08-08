package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Random;

import com.tutorial.main.Game.STATE;


public class Menu extends MouseAdapter {
	
	private Game game;
	private Handler handler;
	private HUD hud;
	private Spawn spawn = new Spawn(handler, hud);
	private Random r = new Random();
	private boolean notDisabled = false;
	private int healthTick = 300;
	private Tracker tracker = new Tracker();
	
	public Menu(Handler handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Menu) {
			
		
		
		//Play Button
		if(mouseOver(mx, my, Game.WIDTH/2 - 35, 150, 150, 64)){
			Game.gameState = STATE.Game;
			hud.setLevel(1);
			hud.setScore(0);
			hud.setWave(1);
			
			handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
			handler.clearEnemies();
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-75), r.nextInt(Game.HEIGHT-75), 7, 7, Color.red, ID.BasicEnemy, handler));		
		}
		
		//Help Button
		if(mouseOver(mx,my, Game.WIDTH/2 - 35, 250, 150, 64)) {
			Game.gameState = STATE.Help;
			
		}
		
		
			//Quit Button
		if(mouseOver(mx,my, Game.WIDTH/2 - 35, 350, 150, 64)) {
			System.exit(1);
		}
		
		if(mouseOver(mx, my, Game.WIDTH/2 - 35, 450, 150, 64)) {
			Game.gameState = STATE.Store;
			System.out.println("Store");
		}
		
	}
			//Back button
				if(Game.gameState == STATE.Help) {
					if(mouseOver(mx, my, Game.WIDTH/2 - 60, 350, 150, 64)) {
						Game.gameState = STATE.Menu;
						return;
					}
				}
			//Try Again Button
				if(Game.gameState == STATE.End) {
					if(mouseOver(mx, my, Game.WIDTH/2 - 35, 250, 150, 64)) { 
						Game.gameState = STATE.Game;
						hud.setLevel(1);
						hud.setScore(0);
						hud.setWave(1);
						
						handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
						handler.clearEnemies();
						handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), 7, 7, Color.red, ID.BasicEnemy, handler));
						spawn.setScoreKeep(0);

						
					}
				}
			//Menu from Game Over Button
				if(Game.gameState == STATE.End) {
					if(mouseOver(mx, my, Game.WIDTH/2 - 35, 350, 150, 64 )){
						Game.gameState = STATE.Menu;
					}
				}
			}
			
				//Store back Game
				
	
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
		if(Game.gameState == STATE.Menu){
		
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("WAVE", Game.WIDTH/2 - 25, 100);
	
		g.setFont(fnt2);
		g.drawRect(Game.WIDTH/2 - 35, 150, 150, 64);
		g.drawString("Play", Game.WIDTH /2, 190);
		
		g.drawRect(Game.WIDTH/2 - 35, 250, 150, 64);
		g.drawString("Help", Game.WIDTH /2, 290);
		
		g.drawRect(Game.WIDTH/2 - 35, 350, 150, 64);
		g.drawString("Quit", Game.WIDTH /2, 390);
		
		g.drawRect(Game.WIDTH/2 - 35, 450, 150, 64);
		g.drawString("Store", Game.WIDTH/2, 490);
		
		
		
		/*g.setFont(fnt3);
		g.setColor(Color.white);
		g.drawRect(540, 410, 80, 30);
		g.drawString("Store", 555, 430);*/
		
		}else if(Game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", Game.WIDTH / 2 - 25, 100);
			
			g.setFont(fnt3);
			g.drawString("Wave is a game where you are a white block dodging the enemies.", 250,150);
			g.drawString("Each level gets progressively harder.", 350, 175);
			
			g.setFont(fnt3);
			g.drawString("Use WASD to move the player around to dodge enemies", 250, 200);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(Game.WIDTH/2 - 60, 350, 150, 64);
			g.drawString("Back", Game.WIDTH/2 - 25, 390);
			
		}else if(Game.gameState == STATE.End) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game over", Game.WIDTH/2 - 50, 100);
			
			g.setFont(fnt3);
			g.drawString("You died at the score of: " + hud.getScore(), Game.WIDTH/2 - 35, 175);
			g.drawString("and at a Wave of: " + hud.getWave() + "." + hud.getLevel(), Game.WIDTH/2 - 35, 200);
				
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(Game.WIDTH/2 -35, 250, 150, 64);
			g.drawString("Try Again", Game.WIDTH/2 - 20,  290);
			
			g.drawRect(Game.WIDTH/2 -35, 350, 150, 64);
			g.drawString("Menu", Game.WIDTH/2, 390);
			
		/*	g.setFont(fnt3);
			g.setColor(Color.white);
			g.drawRect(540, 410, 80, 30);
			g.drawString("Store", 555, 430);*/
			
		}
		
	
}

		
}
