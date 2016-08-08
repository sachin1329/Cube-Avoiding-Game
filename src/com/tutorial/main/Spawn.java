package com.tutorial.main;

import java.awt.Color;
import java.util.Random;

public class Spawn {
	
	public int scoreKeep = 0;
	public int enemytick = 100;
	
	private Handler handler;
	private HUD hud;
	private Random r = new Random();	
	
	public Spawn(Handler handler, HUD hud) {
		
		this.handler = handler;
		this.hud = hud;
		
		
	}
	
	
	
	public void tick(){ 
		
		BasicEnemy enemy = new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50),7, 7, Color.red, ID.BasicEnemy, handler);
		BasicEnemy fastEnemy = new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), 4, 16, Color.CYAN, ID.BasicEnemy, handler);
		SmartEnemy smartEnemy = new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT), 1, Color.green, ID.SmartEnemy, handler);
		SmartEnemy smartFastEnemy = new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 2, Color.ORANGE, ID.SmartEnemy, handler);
		ExplodingEnemy explode = new ExplodingEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50),7, 7, Color.PINK, ID.BasicEnemy, handler);
		
		scoreKeep++;
		
		if(scoreKeep >= 250) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			if(hud.getLevel() == 10) {
				hud.setLevel(1);
				hud.setWave(hud.getWave() + 1);
			}
			
			if(hud.getWave() == 1  && hud.getLevel() == 2) {
				handler.addObject(enemy);
				handler.addObject(explode);
				
			}else if( hud.getWave() == 1  && hud.getLevel() == 3) {
				explode.explode();
				
				/*
				handler.addObject(enemy);	
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50),5, 5, Color.red, ID.BasicEnemy, handler));*/
			}else if( hud.getWave() == 1  && hud.getLevel() == 4) {
				handler.addObject(fastEnemy);
			}else if(hud.getWave() == 1  && hud.getLevel() == 5) {
				handler.addObject(fastEnemy);
			}else if( hud.getWave() == 1  && hud.getLevel() == 6) {
				handler.addObject(smartEnemy);
			}else if(hud.getWave() == 1  && hud.getLevel() == 7) {
				handler.addObject(fastEnemy);
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), 4, 16, Color.cyan, ID.FastEnemy, handler));
				handler.addObject(enemy);
				handler.removeObject(smartFastEnemy);
			}else if( hud.getWave() == 2 && hud.getLevel() == 1) {
				 int yPos = 20;
				 handler.clearEnemies();
				 
				for(int i = 0; i < 16; i++ ) {
					if(i != 5) {
					handler.addObject(new BasicEnemy(0, yPos,7 , 0, Color.MAGENTA, ID.StraightEnemy, handler));
					yPos+=40;
					}
				}	 
			}else if( hud.getWave() == 2  && hud.getLevel() == 2) {
				handler.addObject(enemy);	
			}else if( hud.getWave() == 2  && hud.getLevel() == 3) {
					handler.addObject(smartEnemy);	
			}else if(hud.getWave() == 2 && hud.getLevel() == 4) {
				handler.addObject(enemy);	
			}else if(hud.getWave() == 2 && hud.getLevel() == 5) {
				handler.addObject(smartFastEnemy);	
			}else if(hud.getWave() == 2 && hud.getLevel() == 6) {
				handler.addObject(fastEnemy);	
			}
			
			}
	
		}
		
	
	
	public int getScoreKeep() {
		return scoreKeep;
	}
	public void setScoreKeep(int scoreKeep) {
		this.scoreKeep = scoreKeep;
	}
	
}