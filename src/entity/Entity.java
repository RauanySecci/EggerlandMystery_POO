package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Painel;
import main.UtilityTool;


public class Entity {
    public int x, y;
    public int speed;
    
    public BufferedImage up1, up2, down1,down2, left1, left2, right1, right2;
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public boolean invincible = false;
    public int invincibleCounter = 0;
	public BufferedImage image, image2;
	public String name;
	public boolean collision = false;
    public int maxLife;
    public int life;
    public int attack;
    public int maxMana;
    public int mana;
    public Tiro tiro;
    public int useCost;
    public boolean alive;
    
    Painel gp;
	public boolean canShoot = true;
    
    public Entity(Painel gp) {
    	this.gp = gp;
    }
    
    public void setAction() {
    	
    }
    
    public void update() {
    	setAction();
    	
    	collisionOn = false;
    	gp.cChecker.checkTile(this);
    	gp.cChecker.checkObject(this, false);
    	gp.cChecker.checkPlayer(this);
    	
    	
    	switch(gp.map){
        case 0:
        	gp.cChecker.checkEntity(this, gp.monster[0]);
        	break;
        case 1:
        	gp.cChecker.checkEntity(this, gp.monster[1]);
        	break;
        case 2:
        	gp.cChecker.checkEntity(this, gp.monster[2]);
        	break;
        case 3:
        	gp.cChecker.checkEntity(this, gp.monster[3]);
        	break;
        }
    	
    	//se a colisao é falsa, personagem pode mover
        if(collisionOn == false) {
        	switch(direction) {
        	case "up":
        		y -= speed;
        		break;
        	case "down":
        		y += speed;
        		break;
        	case "left":
        		x -= speed;
        		break;
        	case "right":
        		x += speed;
        		break;
        	}
        }
        
        spriteCounter++;
        if (spriteCounter> 12) {
        	if(spriteNum ==1) {
        		spriteNum = 2;
        	}
        	else if(spriteNum == 2) {
        		spriteNum = 1;
        	}
        	spriteCounter = 0;
        }
    }
    
    public void setMonsterDirection(String direction) {
        this.direction = direction;
    }
    
    public void draw(Graphics2D g2) {
    	 BufferedImage image = null;
        switch (direction) {
        case "up":
        	if(spriteNum==1) {
        		image = up1;
        	}
        	if(spriteNum==2) {
        		image = up2;
        	}
            break;
        case "down":
        	if(spriteNum==1) {
        		image = down1;
        	}
        	if(spriteNum==2) {
        		image = down2;
        	}
            break;
        case "left":
        	if(spriteNum==1) {
        		image = left1;
        	}
        	if(spriteNum==2) {
        		image = left2;
        	}
            break;
        case "right":
        	if(spriteNum==1) {
        		image = right1;
        	}
        	if(spriteNum==2) {
        		image = right2;
        	}
            break;
        default:
        	image = up1;
        	break;
        }
    	g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
    
    public BufferedImage setup(String imageName) {
    	
    	UtilityTool uTool = new UtilityTool();
    	BufferedImage image = null;
    	
    	try {
    		image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
    		image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    	return image;
    }
}

