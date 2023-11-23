package entity;

import main.AssetSetter;
import main.KeyHandler;

import main.Painel;
import object.OBJ_Bala;
import object.OBJ_DoorOpen;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Player extends Entity {
    KeyHandler keyH;
    public int nCoins = 0;
    public int auxNCoins = 0;
    public int passNivel = 0;
    public int maisVida = 0;
    public int points = 0;
    
    public boolean canShoot;
    public int shotDelay;
    long timeShot;

    private Timer actionDeadTimer;
    private int imageChangeDelay = 100; // Intervalo de tempo para alterar a direção (em milissegundos)
    private int imageChangeDuration = 1000; // Duração total da mudança de imagem (em milissegundos)
    private long startTime; // Hora de início da mudança de imagem
    public AssetSetter aSetter = new AssetSetter(gp);

    public Player(Painel gp, KeyHandler keyH) {
    	super(gp);

        this.keyH = keyH;
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 8;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 43;
        solidArea.height = 43;
        
        
        setDefaultValues();
        getPlayerImages();
    }

    public void setDefaultValues() {
        x = 55;
        y = 55;
        speed = 5;
        direction = "down";
        canShoot = false;
        shotDelay = 800;
        
        // PLAYER STATUS
        life = 5;
    }

    public void getPlayerImages() {

        up1 = setup("/player/tras1");
        up2 = setup("/player/tras3");
        down1 = setup("/player/frente1");
        down2 = setup("/player/frente3");
        left1 = setup("/player/lado1");
        left2 = setup("/player/lado2");
        right1 = setup("/player/outrolado1");
        right2 = setup("/player/outrolado2");
    }

    public void update() {
    	
    	if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
    	
        if (keyH.upPressed) {
            direction = "up";
            
        } else if (keyH.downPressed) {
            direction = "down";
            
        } else if (keyH.leftPressed) {
            direction = "left";
            
        } else if (keyH.rightPressed) {
            direction = "right";
            
        }
        
        //checa a colisao do personagem
        collisionOn = false;
        gp.cChecker.checkTile(this);
        
        // Checa a colisão com os objetos
        int objIndex = gp.cChecker.checkObject(this, true);
        pegarObj(objIndex);
        
        // Checa a colisão com os monstros
        switch(gp.map){
        case 0:
        	int monsterIndex = gp.cChecker.checkEntity(this, gp.monster[0]);
        	//contactMonster(monsterIndex);
        	break;
        case 1:
        	monsterIndex = gp.cChecker.checkEntity(this, gp.monster[1]);
        	contactMonster(monsterIndex);
        	break;
        case 2:
        	monsterIndex = gp.cChecker.checkEntity(this, gp.monster[2]);
        	contactMonster(monsterIndex);
        	break;
        case 3:
        	monsterIndex = gp.cChecker.checkEntity(this, gp.monster[3]);
        	contactMonster(monsterIndex);
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
        switch(gp.map) {
		
		case 0:
			if(auxNCoins == 6) {
				gp.obj[gp.map][6] = null;
				gp.obj[gp.map][6] = new OBJ_DoorOpen(gp);
				gp.obj[gp.map][6].x = 9 * gp.tileSize;
				gp.obj[gp.map][6].y = 6 * gp.tileSize;
				
			}
			break;
		case 1:
			if(nCoins == 5) {
				gp.obj[gp.map][11] = null;
				gp.obj[gp.map][11] = new OBJ_DoorOpen(gp);
				gp.obj[gp.map][11].x = 9 * gp.tileSize;
				gp.obj[gp.map][11].y = 9 * gp.tileSize;
				
			}
			break;
		case 2:
			if(nCoins == 1) {
				gp.obj[gp.map][1] = null;
				gp.obj[gp.map][1] = new OBJ_DoorOpen(gp);
				gp.obj[gp.map][1].x = 10 * gp.tileSize;
				gp.obj[gp.map][1].y = 10 * gp.tileSize;
				
			}
			break;
		case 3:
			if(nCoins == 3) {
				gp.obj[gp.map][3] = null;
				gp.obj[gp.map][3] = new OBJ_DoorOpen(gp);
				gp.obj[gp.map][3].x = 6 * gp.tileSize;
				gp.obj[gp.map][3].y = 10 * gp.tileSize;
				
			}
			break;
		}
        
    	}
    	if(invincible == true) {
    		invincibleCounter++;
    		if(invincibleCounter > 60) {
    			invincible = false;
    			invincibleCounter = 0;
    		}
    	}
    	
    	 if(gp.map ==0) {
    	    	if (gp.keyH.shotKeyPressed && canShoot && nCoins > 0) {
    	    	    //System.out.println("Shot key pressed. Coins before: " + nCoins);
    	    	    
    	    	    canShoot = false;
    	    	    nCoins--;
    	    	    timeShot = System.currentTimeMillis();

    	    	    //System.out.println("Coins after shot: " + nCoins);
    	    	    tiro = new OBJ_Bala(this.gp);
    				tiro.set(this.x, this.y, this.direction, this);
    				gp.tiro.add(tiro);
    	    	}
    	    

    		
    		if (!canShoot) {
    			if (System.currentTimeMillis() - timeShot > shotDelay) {
    				canShoot = true;
    			}
    			
    		}
    	    }
    	
    	if(life <= 0) {
    		gp.gameState = gp.GameOverState;
    	}
    	
    }
    
    public void pegarObj(int i){
    	if(i != 999) {
    		String objectName = gp.obj[gp.map][i].name;
    		
    		switch(objectName) {
    		case "Coin":
    			nCoins++;
    			auxNCoins++;
    			gp.obj[gp.map][i] = null;
    			break;
    		case "DoorOpen":
    			if(gp.map<4) {
    				if(gp.map == 0) {
    					passNivel = passNivel + 50 + nCoins;
    					x = 485;
    					y = 160;
    				}if(gp.map == 1) {
    					passNivel = passNivel + 60 + nCoins;
    					x = 110;
    					y = 100;
    				}if(gp.map == 2) {
    					passNivel = passNivel + 70 + nCoins;
    					x = 55*6;
    					y = 55*3;
    				}if(gp.map == 3) {
    					passNivel = passNivel + 80 + nCoins;
    				}
	    			life += 1;
	    			gp.map ++;
	    			nCoins = 0;
    			}else {
    				System.out.println("ACABOU");
    				
    			}
    			break;
    		case "Box":
    			// Antes de mover a caixa, verifique a colisão com os tiles
    			int futureX = gp.obj[gp.map][i].x;
    			int futureY = gp.obj[gp.map][i].y;

    			switch (direction) {
    			    case "up":
    			        futureY = gp.obj[gp.map][i].y - gp.tileSize / 3;
    			        break;
    			    case "down":
    			        futureY = gp.obj[gp.map][i].y + gp.tileSize / 3;
    			        break;
    			    case "left":
    			        futureX = gp.obj[gp.map][i].x - gp.tileSize / 3;
    			        break;
    			    case "right":
    			        futureX = gp.obj[gp.map][i].x + gp.tileSize / 3;
    			        break;
    			}

    			// Verifique se a caixa pode ser movida para a posição futura
    			if (!gp.cChecker.checkBoxCollision(gp.map, i, futureX, futureY)) {
    			    gp.obj[gp.map][i].x = futureX;
    			    gp.obj[gp.map][i].y = futureY;
    			}
    			break;
    		
    	}
    	}
    }
    
    public void contactMonster(int i) {
    	
    	if(i != 999) {
    		if(invincible == false) {
    			life -= 1;
    			invincible = true;
    			nCoins = 0;
			    getImageDead();
			    setActionDead();
			    
    		}
    	}
    }
    
    public void getImageDead() {
		up1 = setup("/player/morto2");
		up2 = setup("/player/morto3");
		down1 = setup("/player/morto1");
		down2 = setup("/player/morto1");
		left1 = setup("/player/morto4");
		left2 = setup("/player/morto2");
		right1 = setup("/player/morto3");
		right2 = setup("/player/morto4");
	}
	
    public void setActionDead() {
        startTime = System.currentTimeMillis(); // Registra o tempo de início
        auxNCoins = 0;
        
        actionDeadTimer = new Timer(imageChangeDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                int i = random.nextInt(1000) + 1;

                if (i <= 250) {
                    direction = "up";
                } else if (i <= 500) {
                    direction = "right";
                } else if (i <= 750) {
                    direction = "left";
                } else {
                    direction = "down";
                }

                // Verifica se a duração total foi atingida
                if (System.currentTimeMillis() - startTime >= imageChangeDuration) {
                    actionDeadTimer.stop(); // Para o timer quando a duração for atingida
                    getPlayerImages();
                    direction = "down";
                    switch (gp.map) {
    			    case 0:
    			    	x = 55;
    			    	y = 55;
    			    	aSetter.setObject();
    			    	aSetter.setMonster();
    			        break;
    			    case 1:
    			    	x = 485;
    					y = 160;
    					aSetter.setObject();
    			    	aSetter.setMonster();
    			        break;
    			    case 2:
    			    	x = 110;
    					y = 100;
    	    			nCoins = 0;
    					aSetter.setObject();
    			    	aSetter.setMonster();
    			    	
    			        break;
    			    case 3:
    			    	x = 55*6;
    					y = 55*3;
    					nCoins = 0;
    					aSetter.setObject();
    			    	aSetter.setMonster();
    			        break;
    			}
                }
            }
        });
        actionDeadTimer.start(); // Inicia o temporizador
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
        }

        g2.drawImage(image, x, y, null);
        
    }
}


