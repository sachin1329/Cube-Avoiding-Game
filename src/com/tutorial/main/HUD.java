package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.tutorial.main.Game.STATE;

public class HUD {

		private float greenValue = 255;
		public static int Coins = 0;
		
		private int score = 0;
		private int wave = 1;
		private int level = 1;
		private static Tracker tracker = new Tracker();
		public static int background = tracker.health * 2;
		public static float HEALTH = tracker.health;
		public static int healthLevel= (int) HEALTH;

		
	
		public void tick() {
			
			HEALTH = Game.clamp(healthLevel, 0, 100);
			greenValue = Game.clamp(greenValue, 0, 255);
			
			greenValue = HEALTH*2;
			
			score++;
			

		}
		public void render(Graphics g) {
			g.setFont(new Font("arial", 1, 20));
			g.setColor(Color.red);
			g.fillRect(15, 15, background, 32);
			g.setColor(Color.green);
			g.fillRect(15, 15, healthLevel*2, 32);
			g.setColor(Color.white);
			g.drawRect(15, 15, background, 32);
			g.drawString("Score: " + score,15,70);
			g.drawString("Wave: " + level + "." + wave, 15, 100);
			g.drawString("HighScore: " + tracker.highScore, 15, 130);
				
				
		}
		
		public void setScore(int score) {
			this.score = score;
		}
		public int getScore() {
			return score;
		}
		public int getLevel() {
			return wave;
		}
		public void setLevel(int wave) {
			this.wave = wave;
		}
		public int getWave() {
			return level;
		}
		public void setWave(int level) {
			this.level = level;
		}
		
		public void setHighScore(int highScore) {
			tracker.highScore = highScore;
		}
		
}
