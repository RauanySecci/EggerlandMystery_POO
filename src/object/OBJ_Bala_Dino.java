package object;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import entity.Tiro;
import main.Painel;


public class OBJ_Bala_Dino extends Tiro{
	Painel gp;
	public BufferedImage image, direita, esquerda, cima, baixo;

	public OBJ_Bala_Dino(Painel gp) {
		super(gp);
		name = "Bala_Dino";
		speed = 5;
		getImage();
	}
	
	public void getImage() {
		baixo = setup("/objects/bala_dino_baixo");
		cima = setup("/objects/bala_dino_cima");
		direita = setup("/objects/bala_dino_direita");
		esquerda = setup("/objects/bala_dino_esquerda");
			
		
	}
	 public void setDirection(String direction) {
	        this.direction = direction;
	    }

public void draw(Graphics2D g2) {
	
	
		if (this.direction == "up") {
			image = cima;
		}else if(this.direction == "down") {
			image = baixo;
		}
		else if (this.direction == "left") { 
			image = esquerda;
		}else if(this.direction == "right") {
			image = direita;
		}
		
		g2.drawImage(image, x, y, null);
		
		
	}
}
