package monster;


import java.util.Random;
import entity.Entity;
import main.Painel;

public class MON_Caveira extends Entity {
	Painel gp;

	public MON_Caveira(Painel gp) {
		super(gp);
		this.gp = gp;
		
		name = "Caveira";
		speed = 0;
		
		solidArea.x = 3;
		solidArea.y = 1;
		solidArea.width = 52;
		solidArea.height = 52;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	

	
	public void getImage() {
		up1 = setup("/monster/caveira3");
		up2 = setup("/monster/caveira2");
		down1 = setup("/monster/caveira1");
		down2 = setup("/monster/caveira1");
		left1 = setup("/monster/caveira3");
		left2 = setup("/monster/caveira2");
		right1 = setup("/monster/caveira3");
		right2 = setup("/monster/caveira2");
	}
	
	public void setAction() {
		
		switch(gp.map) {
		
		case 0:
			break;
		case 1:
			if(gp.player.nCoins == 5) {
				actionLockCounter ++;
				speed = 2;
				if(actionLockCounter == 50) {
					Random random = new Random();
					int i = random.nextInt(1000)+1;
					if(i <= 250) {
						direction = "up";
					}
					if(i > 250 && i <= 500) {
						direction = "right";
					}
					if(i > 500 && i <= 750) {
						direction = "left";
					}
					if(i > 750 && i <= 1000) {
						direction = "right";
					}
					
					actionLockCounter = 0;
					
				}
				
			}
			break;
		case 2:
			break;
		case 3:
			break;
		}
        
	}
	
}












