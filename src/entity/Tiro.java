package entity;

import main.Painel;

public class Tiro extends Entity{
	Entity user;
	public int timer;

	public Tiro(Painel gp) {
		super(gp);
	}

	public void set(int x, int y, String direction, Entity user) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.user = user;
		this.speed = 10;
		
	}
	

	public void update() {
			if (this.collisionOn) {
				gp.tiro.remove(this);

			}

			if (user == gp.player) {

				// Checa colisão com monstros
				int monsterIndex = gp.cChecker.checkEntity(this, gp.monster[gp.map]);
				if(monsterIndex != 999) {
					gp.monster[gp.map][monsterIndex] = null;
					gp.tiro.remove(this);

				}

				// Checa colisão com objetos
				int objIndex = gp.cChecker.checkObject(this, true);
				if (objIndex != 999) {
					gp.tiro.remove(this);
				}
				boolean hasTileCollision = gp.cChecker.checkTileCollision(this, gp.tileM);
				if (hasTileCollision) {
					gp.tiro.remove(this);
				}



				switch(direction) {
				case "up": y-=speed;break;
				case "down": y+=speed;break;
				case "left": x-=speed;break;
				case "right": x+=speed;break;
				}
				timer++;

			}else {
				int objIndex = gp.cChecker.checkObject(this, true);
				if (objIndex != 999) {
					gp.tiro.remove(this);
					for(int i = 0; i < gp.monster[gp.map].length; i++) {
							if(gp.monster[gp.map][i] != null) {
								if(gp.monster[gp.map][i].name == "Medusa") {
									gp.monster[gp.map][i].canShoot = true;
								}
							}
					}
										
				}
								

				boolean hasTileCollision = gp.cChecker.checkTileCollision(this, gp.tileM);
				if (hasTileCollision) {
					gp.tiro.remove(this);
					for(int i = 0; i < gp.monster[gp.map].length; i++) {
						if(gp.monster[gp.map][i] != null) {
							if(gp.monster[gp.map][i].name == "Roxo") {
								gp.monster[gp.map][i].canShoot = true;
								
							}
							if(gp.monster[gp.map][i].name == "Medusa") {
								gp.monster[gp.map][i].canShoot = true;
								
							}
						}
					}
		            
				}
				
				if (gp.cChecker.checkPlayerCollision(this, gp.player)) {
						gp.tiro.remove(this);
						gp.player.life -= 1;
						gp.player.getImageDead();
						gp.player.setActionDead();
						
				}

				switch(direction) {
				case "up": y-=speed;break;
				case "down": y+=speed;break;
				case "left": x-=speed;break;
				case "right": x+=speed;break;
				}
				timer++;
			}
		}

	}


