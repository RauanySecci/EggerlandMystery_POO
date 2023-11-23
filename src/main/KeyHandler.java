package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	Painel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, shotKeyPressed;
    
    public KeyHandler(Painel gp) {
    	this.gp = gp;
    }

    public void keyTyped(KeyEvent ke) {
    }

 
    public void keyPressed(KeyEvent ke) {
        int code = ke.getKeyCode();
        
        // TITLE STATE
        if(gp.gameState == gp.titleState) {
        	if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W){
        		gp.ui.commandNum--;
        		if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = 2;
        		}
        	}
            if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S){
        		gp.ui.commandNum++;
        		if(gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE){
            	if(gp.ui.commandNum == 0) {
            		gp.gameState = gp.playState;
            	}
            	if(gp.ui.commandNum == 1) {
            		gp.saveLoad.load();
            		gp.gameState = gp.playState;
            	}
            	if(gp.ui.commandNum == 2) {
            		System.exit(0);
            	}
            }
        }
        
        //Play State
        else if(gp.gameState == gp.playState) {
        	
	        if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W){
	            upPressed = true;
	            
	        }
	        if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S){
	            downPressed = true;
	        }
	        if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A){
	            leftPressed = true;
	        }
	        if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D){
	            rightPressed = true;
	        }
	        
	        if( code == KeyEvent.VK_P){
	        	gp.gameState = gp.pauseState;
	        }
	        
	        if(code == KeyEvent.VK_SPACE){
	            shotKeyPressed = true;
	        }
        }
        
        else if(code == KeyEvent.VK_R) {
			switch(gp.map) {
			case 0: 
				gp.tileM.loadMap("/maps/map01.txt", 0); 
				break;
			case 1: 
				gp.tileM.loadMap("/maps/map02.txt", 1); 
				break;
			case 2: 
				gp.tileM.loadMap("/maps/map03.txt", 2); 
				break;
			case 3: 
				gp.tileM.loadMap("/maps/map04.txt", 3); 
				break;
			case 4: 
				gp.tileM.loadMap("/maps/map05.txt", 4); 
				break;
			}
		}
        
        // PAUSE STATE
        else if(gp.gameState == gp.pauseState) {
        	if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W){
        		gp.ui.commandNum--;
        		if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = 2;
        		}
        	}
            if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S){
        		gp.ui.commandNum++;
        		if(gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE){
            	if(gp.ui.commandNum == 0) {
            		gp.gameState = gp.playState;
            		
            	}
            	else if(gp.ui.commandNum == 1) {
            		gp.saveLoad.save();
            	}
            	
            	else if(gp.ui.commandNum == 2) {
            		System.exit(0);
            	}
            }
        }
        
        // GAME OVER STATE
        else if(gp.gameState == gp.GameOverState) {
        	if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W){
        		gp.ui.commandNum--;
        		if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
        		}
        	}
            if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S){
        		gp.ui.commandNum++;
        		if(gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE){
            	if(gp.ui.commandNum == 0) {
            		gp.player.x = gp.player.y = 55;
            		gp.player.life = 5;
    	        	gp.map = 0;
            		gp.player.direction = "down";
            		gp.player.points = 0;
            		gp.player.nCoins = 0;
            		gp.player.passNivel = 0;
            		gp.player.maisVida = 0;
            		gp.gameState = gp.playState;
            		
            	}
            	else if(gp.ui.commandNum == 1) {
            		System.exit(0);
            	}
            }
        }
        
    }
        

    public void keyReleased(KeyEvent ke) {
        int code = ke.getKeyCode();
        if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_SPACE){
            shotKeyPressed = false;
        }
    }
    
}

