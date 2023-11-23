package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

import data.SaveLoad;
import entity.Entity;
import entity.Player;
import entity.Tiro;
import tile.TileManeger;

public class Painel extends JPanel implements Runnable{
    private static final long serialVersionUID = 1L;
	final int originalTileSize = 18;
    final int scale = 3;
    public final int tileSize = originalTileSize*scale;
    public final int maxScreenCol = 17;
    public final int maxScreenRow = 13;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    
    public final int m = 50;
	public int map = 0;
	SaveLoad saveLoad = new SaveLoad(this);
    
    int FPS = 60;
    
    public TileManeger tileM = new TileManeger(this);
    public KeyHandler keyH = new KeyHandler(this);
    public UI ui = new UI(this);
    Thread gameThread;
    public ChecaColisao cChecker = new ChecaColisao(this);
    public AssetSetter aSetter = new AssetSetter(this);
    
    // Entity and Object
    public Player player = new Player(this, keyH);
    public Entity obj[][] = new Entity[m][50];
    public Entity monster[][] = new Entity [m][50];
    public ArrayList<Tiro> tiro = new ArrayList<>();
    
    // Game state
    public int gameState;
    public final int playState = 1;
    public final int titleState = 0;
    public final int pauseState = 2;
    public final int GameOverState = 5; 
   
    
    public Painel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
    }
    
    public void setupGame() {
    	aSetter.setObject();
    	aSetter.setMonster();
    	gameState = titleState;
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start(); //chama run
    }
    
    
    public void run() {
        
        double drawInterval = 1000000000/FPS; //0.016 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        
        while(gameThread != null){
                        
            update();
            repaint(); //chamando o metodo  paintComponent
            double remainingTime = nextDrawTime - System.nanoTime();
            try {
                remainingTime = remainingTime/1000000;
                
                if(remainingTime<0){
                    remainingTime = 0; //nao deixar ele sair da janela
                }
                Thread.sleep((long) remainingTime);
                
                nextDrawTime += drawInterval;
                     
            } catch (InterruptedException ex) {
                Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public void update(){
    	if(gameState == playState) {
    		player.update();
    	}
    	
    	for(int i = 0; i < monster.length; i ++) {
    		if(monster[map][i] != null) {
    			monster[map][i].update();
    		}
    	}
    	
    	for (int i = 0; i < tiro.size(); i++) {
    	    if (tiro.get(i) != null ) {
    	    	if(tiro.get(i).timer <= 150) { // depois de 2.5 segundos o tiro some
					tiro.get(i).update();
				}
    	    } else {
    	    	tiro.remove(i);
    	    }
    	}
    	

    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        // TITLE SCREEN
        if(gameState == titleState) {
        	ui.draw(g2);
        }
        // OTHERS
        else {
        	// PAUSE
        	if(gameState == pauseState) {
        		ui.draw(g2);
        	}
        	
        	// GAME OVER
        	else if(gameState == GameOverState) {
        		ui.draw(g2);
        	}
        	else {
        		  // TILE
                tileM.draw(g2);
                
                //OBJECT
                for(int i =0; i < obj.length; i++) {
                	if(obj[map][i] != null){
                		obj[map][i].draw(g2);
                	}
                }
                
                // MONSTER
                for(int i =0; i < monster.length; i++) {
                	if(monster[map][i] != null){
                		monster[map][i].draw(g2);
                	}
                }
                
                // TIROS
                for(int i =0; i < tiro.size(); i++) {
                	if(tiro.get(i) != null){
                		tiro.get(i).draw(g2);
                	}
                }
                
                //MENU
                ui.drawMenu(g2);
               
                // PLAYER
                player.draw(g2);
                g2.dispose();

        	}
        	            
        }
     
    }
    
}
