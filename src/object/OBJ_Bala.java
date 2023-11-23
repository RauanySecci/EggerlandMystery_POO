package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import entity.Tiro;
import main.Painel;


public class OBJ_Bala extends Tiro{
	Painel gp;
	public BufferedImage image, vertical, horizontal;

	public OBJ_Bala(Painel gp) {
		super(gp);
		
		name = "Bala";
		speed = 5;
		getImage();
		
	}
	
	public void getImage() {
	    vertical = setup("/objects/bala1");
		horizontal = setup("/objects/bala2");
	}
	
	public void draw(Graphics2D g2) {
		
		
		if (this.direction == "up" || this.direction == "down") {
			image = vertical;
		}
		else if (this.direction == "left" || this.direction == "right") {
			image = horizontal;
		}
		
		g2.drawImage(image, x, y, null);
		
		
	}


}
