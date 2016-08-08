package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.Random;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1550691097823471818L;

	public static int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	public static final double SCALE = 1.5;
	
	private Thread thread;
	private boolean running = false;
	public static boolean pause = false;
	public int frames;
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Tracker tr;
	private Store store;
	private int FPS;
	
	public enum STATE {
		Menu,
		Game,
		Help,
		End,
		Store
	}
	
	public static STATE gameState = STATE.Menu;
	
	public Game() {
		WIDTH = (int)(WIDTH * SCALE);
		HEIGHT = (int)(HEIGHT * SCALE);

		handler = new Handler();
		hud =new HUD();
		menu = new Menu(handler, hud);
		tr = new Tracker();
		store = new Store();
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		this.addMouseListener(store);
		
		new Window(WIDTH,HEIGHT, "WAVE GAME", this);
		
		spawner = new Spawn(handler, hud);
		r=new Random();

		
		if(gameState == STATE.Game){
			tr.update();
			handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50),5,5, Color.red, ID.BasicEnemy, handler));		
		}else {
			for(int i= 0; i< 20; i++) {
				handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
			}
	}
		
}
	
	
	 
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running= true;
	}
	
	public synchronized void stop() {
		try{
			thread.join();
			running= false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime= System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta --;
			}
			if(running)
				render();
			
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				FPS = frames;
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		
		if(pause == false) {
		handler.tick();
		if(gameState == STATE.Game) {
			
			hud.tick();
			spawner.tick();

			if(HUD.HEALTH <= 0) {
				HUD.healthLevel = tr.health;
				gameState = STATE.End;
				handler.clearEnemies();
				for(int i= 0; i < 15; i++) {
					handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
				}
				
				if(hud.getScore() > tr.highScore) {
					tr.highScore = hud.getScore();
					hud.setHighScore(hud.getScore());
					tr.update();
				}
				
			}
			
		}else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Store)
			menu.tick();
			
		}	
	
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs== null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g= bs.getDrawGraphics();
		
		
		g.setColor(Color.black);
		g.fillRect(0,0,WIDTH, HEIGHT);
		g.setColor(Color.white);
		g.setFont(new Font("arial", 12, 20));
		g.drawString(FPS + "", WIDTH - 65, 50);
		g.setFont(new Font("arial", 12, 25));
	
		
		handler.render(g);
		
		if(gameState == STATE.Game) {
			hud.render(g);
		}else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
			menu.render(g);
		}else if(gameState == STATE.Store) {
			store.render(g);
		}
		

		if(pause == true) {
			g.setColor(Color.white);
			g.drawString("Pause", 300, 100);
		}
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, float max) {
		if(var >= max) 
			return var = max;
		else if( var <= min) 
			return var = min;
		else
			return var;
	}
		
	public static void main(String args[]) {
		 new Game();
		
	}
	
}
