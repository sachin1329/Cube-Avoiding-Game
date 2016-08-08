package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.tutorial.main.Game.STATE;
 
 
public class KeyInput extends KeyAdapter{
 
        private Handler handler;
        private Game game;
        private boolean[] keyDown = new boolean[4];
        private Tracker tracker = new Tracker();
        private int speed;
       
        public KeyInput(Handler handler){
                
              	this.handler = handler;
                
                keyDown[0] = false;
                keyDown[1] = false;
                keyDown[2] = false;
                keyDown[3] = false;
        }
       
        public void keyPressed(KeyEvent e){
        	speed = tracker.getSpeed();
                int key = e.getKeyCode();
               System.out.println(tracker.getSpeed());
                for (int i = 0; i < handler.object.size(); i++){
                        GameObject tempObject = handler.object.get(i);
                       
                        if(tempObject.getId() == ID.Player){
                                //Key events for player 1
                             if(Game.pause == false) { 
                                if(key == KeyEvent.VK_W) {tempObject.setVelY(-speed); keyDown[0] = true;}
                                if(key == KeyEvent.VK_S) {tempObject.setVelY(+tracker.speed); keyDown[1] = true;}
                                if(key == KeyEvent.VK_A) {tempObject.setVelX(-tracker.speed); keyDown[2] = true;}
                                if(key == KeyEvent.VK_D) {tempObject.setVelX(+tracker.speed); keyDown[3] = true;}
                             }
                }
                }
                
                if(key == KeyEvent.VK_ESCAPE) System.exit(1);
                if(Game.gameState == STATE.Game) {
                	if(key == KeyEvent.VK_P){
                		if(Game.pause == false){
                		Game.pause = true;
                		}else{
                			Game.pause = false;
                		}
                	}
                	
                }
               
                
               
        }
        
        public void setSpeed(int speed) {
        	tracker.speed = speed;
        }
       
        public void keyReleased(KeyEvent e){
                int key = e.getKeyCode();
               
                for (int i = 0; i < handler.object.size(); i++){
                        GameObject tempObject = handler.object.get(i);
                       
                        if(tempObject.getId() == ID.Player){
                                //Key events for player 1
                               
                                if(key == KeyEvent.VK_W) keyDown[0] = false;
                                if(key == KeyEvent.VK_S) keyDown[1] = false;
                                if(key == KeyEvent.VK_A) keyDown[2] = false;
                                if(key == KeyEvent.VK_D) keyDown[3] = false;
                                
                                //Vertical movement
                                if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
                                //Horizontal movement
                                if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
                        }
                }
                
           
        }
       
}