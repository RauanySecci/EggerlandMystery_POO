package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;


public class UI {
	
	Painel gp;
	Graphics2D g2;
	Font GameOver;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public int commandNum = 0;
	public int titleScreenState = 0; //0: the first screen, 1:the second screen
	
	public UI(Painel gp) {
		this.gp = gp;
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/gameovercre1.ttf");			
			GameOver = Font.createFont(Font.TRUETYPE_FONT, is);
		}catch(FontFormatException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		g2.setFont(GameOver);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		
		// TITLE STATE
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		
		// PLAY STATE
		if(gp.gameState == gp.playState) {
			
		}
		
		// PAUSE STATE
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		
		// GAME OVER STATE
		if(gp.gameState == gp.GameOverState) {
			drawGameOverScreen();
		}
	}

	
	public void drawTitleScreen() {
		int x1 = gp.screenWidth/2 - (gp.tileSize*15/2)/2;
		int y1 = gp.tileSize * 1;
		
		int x2 = gp.screenWidth/2 - (gp.tileSize*3)/2;
		int y2 = gp.tileSize * 9;
	
		BufferedImage image1 = null, image2 = null, image3 = null;
		try {
			image1 = ImageIO.read(getClass().getResourceAsStream("/images/inicio.png"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/images/start.png"));
			image3 = ImageIO.read(getClass().getResourceAsStream("/images/coracao.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		g2.drawImage(image1, x1, y1, gp.tileSize * 15/2, gp.tileSize * 15/2, null);
		g2.drawImage(image2, x2, y2, gp.tileSize * 3, gp.tileSize * 3, null);
		
		
		// MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,18F));
		String text = "NEW GAME";
		int x = getXforCenteredText(text)+10;
		int y = gp.tileSize * 1040/100;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			x += -25;
			y += -25;
			g2.drawImage(image3, x, y, gp.tileSize *1/2, gp.tileSize *1/2, null);
		}
		
		text = "LOAD GAME";
		x = getXforCenteredText(text)+10;
		y = gp.tileSize * 1040/100 + gp.tileSize*1/2;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			x += -25;
			y += -25;
			g2.drawImage(image3, x, y, gp.tileSize *1/2, gp.tileSize *1/2, null);
		}
		
		text = "QUIT";
		x = getXforCenteredText(text);
		y = gp.tileSize * 1040/100 + gp.tileSize*1;
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			x += -25;
			y += -25;
			g2.drawImage(image3, x, y, gp.tileSize *1/2, gp.tileSize *1/2, null);
		}
		
		
	}
	
	public int getXforCenteredText(String text) {
		
		int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - lenght/2;
		return x;
	}
	
	// Desenha o menu lateral
	public void drawMenu(Graphics2D g2) { 
		this.g2 =g2;
		if(gp.map<4) {
			BufferedImage image1 = null, image2 = null;
			
			try {
				image1 = ImageIO.read(getClass().getResourceAsStream("/player/parado.png"));
				image2 = ImageIO.read(getClass().getResourceAsStream("/objects/bala1.png"));
			}catch(IOException e) {
				e.printStackTrace();
			}
			// DESENHA AS IMAGENS
			g2.drawImage(image1, gp.tileSize*14, gp.tileSize*4, gp.tileSize, gp.tileSize, null);
			g2.drawImage(image2, gp.tileSize*14, gp.tileSize*5, gp.tileSize, gp.tileSize, null);
			
			// VIDA
			int vida = gp.player.life + gp.player.maisVida;
			String text = ""+vida;
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,28F));
			g2.setColor(Color.white);
			g2.drawString(text, gp.tileSize*153/10, gp.tileSize*47/10);
			
			// TIROS
			if(gp.map == 0) {
				text = ""+gp.player.nCoins*2;
				g2.drawString(text, gp.tileSize*153/10, gp.tileSize*57/10);
			}else {
				text = ""+gp.player.nCoins*0;
				g2.drawString(text, gp.tileSize*153/10, gp.tileSize*57/10);
			}
			
			// NIVEL
			
			switch(gp.map) {
			case 0:
				text = "NÍVEL";
				g2.drawString(text, gp.tileSize*13, gp.tileSize*115/10);
				text = "1";
				g2.drawString(text, gp.tileSize*153/10, gp.tileSize*115/10);
				break;
			case 1:
				text = "NÍVEL";
				g2.drawString(text, gp.tileSize*13, gp.tileSize*115/10);
				text = "2";
				g2.drawString(text, gp.tileSize*153/10, gp.tileSize*115/10);
				break;
			case 2:	
				text = "NÍVEL";
				g2.drawString(text, gp.tileSize*13, gp.tileSize*115/10);
				text = "3";
				g2.drawString(text, gp.tileSize*153/10, gp.tileSize*115/10);
				break;
			case 3:	
				text = "NÍVEL";
				g2.drawString(text, gp.tileSize*13, gp.tileSize*115/10);
				text = "4";
				g2.drawString(text, gp.tileSize*153/10, gp.tileSize*115/10);
				break;
			}
			
			// Power
			text = "POWER";
			g2.drawString(text, gp.tileSize*13, gp.tileSize*15/2);
			
			// TOP
			text = "TOP";
			g2.setColor(Color.blue);
			g2.drawString(text, gp.tileSize*13, gp.tileSize*3/2);
			
			gp.player.points = gp.player.nCoins + gp.player.passNivel;
			text = "" + gp.player.points;
			g2.drawString(text, gp.tileSize*148/10, gp.tileSize*3/2);
			
			// 1 UP
			text = "1 UP";
			g2.drawString(text, gp.tileSize*13, gp.tileSize*5/2);
			
			text = "" + gp.player.points;
			g2.drawString(text, gp.tileSize*148/10, gp.tileSize*5/2);
			
		}else {
			String text = "Rauany Martinez Secci - 13721217";
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,28F));
			g2.setColor(Color.white);
			g2.drawString(text, gp.tileSize*4, gp.tileSize*6);
		    text = "Karine Cerqueira Nascimento - 13718404";
		    g2.drawString(text, gp.tileSize*3, gp.tileSize*7);
			
		}
	}
	
	public void drawGameOverScreen() {
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/images/coracao.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		g2.setColor(new Color(0, 0, 0, 150));

	    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
	
	    int x;
	    int y;
	    String text;
	    
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD,100F));
	    
	    text = "Game Over";
	    
	    // Main
	    g2.setColor(Color.white);
	    x = getXforCenteredText(text);
	    y = gp.tileSize * 4;
	    g2.drawString(text, x, y);
	    
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
	    text = "Play Again";
	    x = getXforCenteredText(text);
	    y += gp.tileSize * 4;
	    g2.drawString(text, x, y);
		if(commandNum == 0) {
			x += -35;
			y += -35;
			g2.drawImage(image, x, y, gp.tileSize *75/100, gp.tileSize *75/100, null);
		}
	    
	    // QUIT
	    text = "Quit";
	    x = getXforCenteredText(text);
	    y = gp.tileSize * 9;
	    g2.drawString(text, x, y);
		if(commandNum == 1) {
			x += -35;
			y += -35;
			g2.drawImage(image, x, y, gp.tileSize*75/100, gp.tileSize*75/100, null);
		}
	}
	
public void drawPauseScreen() {
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/images/coracao.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		g2.setColor(new Color(0, 0, 0, 150));

	    g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
	
	    int x;
	    int y;
	    String text;
	    
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD,100F));
	    
	    text = "Game Paused";
	    
	    // Main
	    g2.setColor(Color.white);
	    x = getXforCenteredText(text);
	    y = gp.tileSize * 4;
	    g2.drawString(text, x, y);
	    
	    g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
	    text = "Continue";
	    x = getXforCenteredText(text);
	    y += gp.tileSize * 4;
	    g2.drawString(text, x, y);
		if(commandNum == 0) {
			x += -35;
			y += -35;
			g2.drawImage(image, x, y, gp.tileSize *75/100, gp.tileSize *75/100, null);
		}
	    
		// SAVE
	    text = "Save game";
	    x = getXforCenteredText(text);
	    y = gp.tileSize * 9;
	    g2.drawString(text, x, y);
		if(commandNum == 1) {
			x += -35;
			y += -35;
			g2.drawImage(image, x, y, gp.tileSize*75/100, gp.tileSize*75/100, null);
		}
		
	    // QUIT
	    text = "Quit";
	    x = getXforCenteredText(text);
	    y = gp.tileSize * 10;
	    g2.drawString(text, x, y);
		if(commandNum == 2) {
			x += -35;
			y += -35;
			g2.drawImage(image, x, y, gp.tileSize*75/100, gp.tileSize*75/100, null);
		}
	}

	

}








