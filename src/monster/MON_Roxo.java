package monster;

import entity.Entity;
import main.Painel;
import object.OBJ_Bala_Dino;

public class MON_Roxo extends Entity {
	Painel gp;
	int index;
	private String monsterDirection;
	

	public MON_Roxo(Painel gp, int index) {
		super(gp);
		this.gp = gp;
		this.index = index;
		this.canShoot = true;

		name = "Roxo";

		solidArea.x = 3;
		solidArea.y = 1;
		solidArea.width = 52;
		solidArea.height = 52;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		monsterDirection = "down";

		getImage();
	}

	public void getImage() {
		if(index == 1) {
			up1 = setup("/monster/mon_roxo1_2");
			up2 = setup("/monster/mon_roxo1_2");
			down1 = setup("/monster/mon_roxo1");
			down2 = setup("/monster/mon_roxo1");
		}
		if(index == 2) {
			up1 = setup("/monster/mon_roxo2_2");
			up2 = setup("/monster/mon_roxo2_2");
			down1 = setup("/monster/mon_roxo2");
			down2 = setup("/monster/mon_roxo2");
		}
		if(index == 3) {
			up1 = setup("/monster/mon_roxo3");
			up2 = setup("/monster/mon_roxo3");
			down1 = setup("/monster/mon_roxo3");
			down2 = setup("/monster/mon_roxo3");
		}
		if(index == 4) {
			up1 = setup("/monster/mon_roxo4_2");
			up2 = setup("/monster/mon_roxo4_2");
			down1 = setup("/monster/mon_roxo4");
			down2 = setup("/monster/mon_roxo4");
		}
	}

	public void setMonsterDirection(String direction) {
		this.monsterDirection = direction;
	}

	public void setAction() {

		switch(gp.map) {

		case 0:
			break;
		case 1:
			break;
		case 2:
			
			if(gp.player.nCoins == 1) {
				direction = "up";
				if (this == gp.monster[gp.map][0] && canShoot) {
					if(gp.player.y  > 163 && gp.player.y < 215) {
						actionLockCounter++;
						this.monsterDirection = "right";
	
		                // Atira a cada 10 iterações
		                if (actionLockCounter == 5) {
		                    // Cria um novo tiro
		                    OBJ_Bala_Dino tiro = new OBJ_Bala_Dino(this.gp);
		                    tiro.set(this.x, this.y, monsterDirection, this);
		                    
		                    gp.tiro.add(tiro);
		                    actionLockCounter = 0;
		                    canShoot = false;
		                
		                }
		                
					}
				}
				if (this == gp.monster[gp.map][2] && canShoot) {
					if(gp.player.x  > 159 && gp.player.x < 219) {
						actionLockCounter++;
						this.monsterDirection = "up";
	
		                // Atira a cada 10 iterações
		                if (actionLockCounter == 5) {
		                    // Cria um novo tiro
		                    OBJ_Bala_Dino tiro = new OBJ_Bala_Dino(this.gp);
		                    tiro.set(this.x, this.y, monsterDirection, this);
		                    gp.tiro.add(tiro);
		                    actionLockCounter = 0;
		                    canShoot = false;
		                    
		                }	
					}
				}

				if (this == gp.monster[gp.map][1] && canShoot) {
					if(gp.player.x  > 383 && gp.player.x < 431) {
						actionLockCounter++;
						this.monsterDirection = "down";
	
		                // Atira a cada 10 iterações
		                if (actionLockCounter == 5) {
		                    // Cria um novo tiro
		                    OBJ_Bala_Dino tiro = new OBJ_Bala_Dino(this.gp);
		                    tiro.set(this.x, this.y, monsterDirection, this);
		                    
		                    gp.tiro.add(tiro);
		                    actionLockCounter = 0;
		                    canShoot = false;
		                }	
					}
				}
				
				if (this == gp.monster[gp.map][3] && canShoot) {
					if(gp.player.y  > 379 && gp.player.y < 431) {
						actionLockCounter++;
						this.monsterDirection = "left";
	
		                // Atira a cada 10 iterações
		                if (actionLockCounter == 5) {
		                    // Cria um novo tiro
		                    OBJ_Bala_Dino tiro = new OBJ_Bala_Dino(this.gp);
		                    tiro.set(this.x, this.y, monsterDirection, this);
		                    
		                    gp.tiro.add(tiro);
	
		                    actionLockCounter = 0;
		                    canShoot = false;
		                }
		               
					}
				}
			}
			break;
		case 3:
			break;
		}
	}
}






