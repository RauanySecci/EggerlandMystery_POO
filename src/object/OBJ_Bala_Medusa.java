package object;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import entity.Tiro;
import main.Painel;


public class OBJ_Bala_Medusa extends Tiro{
	Painel gp;
	public BufferedImage image, vertical, horizontal;

	public OBJ_Bala_Medusa(Painel gp) {
		super(gp);
		
		name = "Bala_Medusa";
		getImage();
		
	}
	
	public void getImage() {
			vertical = setup("/objects/bala_medusa1");
			horizontal = setup("/objects/bala_medusa2");
			
		
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