package monster;

import java.util.Random;
import entity.Entity;
import main.Painel;

public class MON_Cobra extends Entity {

	public MON_Cobra(Painel gp) {
		super(gp);
		
		name = "Cobra";
		life = 1;
		
		solidArea.x = 3;
		solidArea.y = 1;
		solidArea.width = 52;
		solidArea.height = 52;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/monster/snake1");
		up2 = setup("/monster/snake1");
		down1 = setup("/monster/snake2");
		down2 = setup("/monster/snake2");
		left1 = setup("/monster/snake3");
		left2 = setup("/monster/snake3");
		right1 = setup("/monster/snake4");
		right2 = setup("/monster/snake4");
	}
	
	public void setAction() {
		actionLockCounter ++;
		
		if(actionLockCounter == 100) {
			Random random = new Random();
			int i = random.nextInt(1000)+1;
			
			if(i <= 250) {
				direction = "up";
			}
			if(i > 250 && i <= 500) {
				direction = "down";
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
	
	
}
