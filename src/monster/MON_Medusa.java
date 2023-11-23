package monster;

import entity.Entity;
import main.Painel;
import object.OBJ_Bala_Medusa;

public class MON_Medusa extends Entity {

	Painel gp;
	int index;

	public MON_Medusa(Painel gp) {
		super(gp);
		this.gp = gp;
		this.canShoot = true;

		name = "Medusa";
		
		solidArea.x = 3;
		solidArea.y = 1;
		solidArea.width = 52;
		solidArea.height = 52;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;

		getImage();
	}

	public void getImage() {
		up1 = setup("/monster/medusa2");
		up2 = setup("/monster/medusa2");
		down1 = setup("/monster/medusa1");
		down2 = setup("/monster/medusa1");
	}

	public void setAction() {
	    switch(gp.map) {
	        case 0:
	            break;
	        case 1:
	            break;
	        case 2:
	            break;
	        case 3:
	        	if(canShoot) {
	        		if (this == gp.monster[gp.map][0]) {
						if(gp.player.x  > 165 && gp.player.x < 265) {
							direction = "up";
							actionLockCounter++;
		
			                // Atira a cada 10 iterações
			                if (actionLockCounter == 5) {
			                    // Cria um novo tiro
			                	OBJ_Bala_Medusa tiro = new OBJ_Bala_Medusa(this.gp);
			                    tiro.set(this.x, this.y, "up", this);
			                    
			                    gp.tiro.add(tiro);
			                    actionLockCounter = 0;
			                    canShoot = false;
			                    direction = "down";
			                }
						}
		        		if(gp.player.y  > 325 && gp.player.y < 380) {
			        		direction = "up";
							actionLockCounter++;
		
			                // Atira a cada 10 iterações
			                if (actionLockCounter == 5) {
			                    // Cria um novo tiro
			                		double angle = Math.atan2(gp.player.y - this.y, gp.player.x - this.x);
				                    String shotDirection = getShotDirection(angle);

				                    OBJ_Bala_Medusa tiro = new OBJ_Bala_Medusa(this.gp);
				                    tiro.set(this.x, this.y, shotDirection, this);
				                    gp.tiro.add(tiro);

				                    actionLockCounter = 0;
				                    canShoot = false;
				                    direction = "down";
			                }
						}
					}
	        		
	        		if (this == gp.monster[gp.map][1]) {
						if(gp.player.x  > 375 && gp.player.x < 400 || gp.player.x  > 410 && gp.player.x < 430) {
			        		direction = "up";
							actionLockCounter++;
		
			                // Atira a cada 10 iterações
			                if (actionLockCounter == 5) {
			                    // Cria um novo tiro
			                	OBJ_Bala_Medusa tiro = new OBJ_Bala_Medusa(this.gp);
			                    tiro.set(this.x, this.y, "up", this);
			                    
			                    gp.tiro.add(tiro);
			                    actionLockCounter = 0;
			                    canShoot = false;
			                    direction = "down";
			                }
						}
						
					}
	        	}
	        
	            break;
	    }
	    
	}

	// Método para obter a direção do tiro com base no ângulo
	private String getShotDirection(double angle) {
	    double degrees = Math.toDegrees(angle);

	    if (degrees > -45 && degrees <= 45) {
	        return "right";
	    } else if (degrees > 45 && degrees <= 135) {
	        return "down";
	    } else if (degrees > 135 || degrees <= -135) {
	        return "left";
	    } else {
	        return "up";
	    }
	}
}