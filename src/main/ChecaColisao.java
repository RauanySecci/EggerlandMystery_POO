package main;

import entity.Entity;
import entity.Tiro;
import tile.TileManeger;

public class ChecaColisao {
	Painel gp;
	
	public ChecaColisao(Painel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		//fazendo o personagem não ser inteiro sólido, ou seja parte dele irá ser transponivel
		int pixel_esquerda = entity.x + entity.solidArea.x;
		int pixel_direita = entity.x + entity.solidArea.x + entity.solidArea.width;
		int pixel_topo = entity.y + entity.solidArea.y;
		int pixel_baixo = entity.y + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = pixel_esquerda/gp.tileSize;
		int entityRightCol = pixel_direita/gp.tileSize;
		int entityTopRow = pixel_topo/gp.tileSize;
		int entityBottomRow = pixel_baixo/gp.tileSize;
		
		
		int tileNum1, tileNum2;
		
		
		switch(entity.direction) {
		case "up":
			try {
			entityTopRow = (pixel_topo - entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[gp.map][entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[gp.map][entityRightCol][entityTopRow];

            // Check for collision with tiles
            if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                entity.collisionOn = true;
            }

            // Check for collision with boxes
            int boxIndex = checkObject(entity, false);
            if (boxIndex != 999) {
                entity.collisionOn = true;
            }
            break;
			}catch(ArrayIndexOutOfBoundsException e) {
				
			}
		case "down":
			try {
			entityBottomRow = (pixel_baixo + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.map][entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[gp.map][entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
				
			}
			
			// Check for collision with boxes
			int boxIndex = checkObject(entity, false);
            if (boxIndex != 999) {
                entity.collisionOn = true;
            }
			break;
			}catch(ArrayIndexOutOfBoundsException e) {
				
			}
		case "left":
			try {
			entityLeftCol = (pixel_esquerda - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.map][entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.map][entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
				
			}
			
			// Check for collision with boxes
			int boxIndex = checkObject(entity, false);
            if (boxIndex != 999) {
                entity.collisionOn = true;
            }
			
			break;
			}catch(ArrayIndexOutOfBoundsException e) {
				
			}
		case "right":
			try {
			entityRightCol = (pixel_direita + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.map][entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.map][entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
				
			}
			
			// Check for collision with boxes
			int boxIndex = checkObject(entity, false);
            if (boxIndex != 999) {
                entity.collisionOn = true;
            }
			break;
			}catch(ArrayIndexOutOfBoundsException e) {
				
			}
		}
		
		
	}
	public int checkObject(Entity entity, boolean player) {
		
		int index = 999;
		
		for(int i=0; i< gp.obj.length; i++) {
			if(gp.obj[gp.map][i] != null) {
				// Get entity's solid area position
				entity.solidArea.x = entity.x + entity.solidArea.x;
				entity.solidArea.y = entity.y + entity.solidArea.y;
				// Get the object's solid area position 
				gp.obj[gp.map][i].solidArea.x = gp.obj[gp.map][i].x + gp.obj[gp.map][i].solidArea.x;
				gp.obj[gp.map][i].solidArea.y = gp.obj[gp.map][i].y + gp.obj[gp.map][i].solidArea.y;
				
				switch(entity.direction) {
				case "up":entity.solidArea.y -= entity.speed;break;
				case "down":entity.solidArea.y += entity.speed;break;
				case "left":entity.solidArea.x -= entity.speed;break;
				case "right":entity.solidArea.x += entity.speed;
					break;
				}

				if(entity.solidArea.intersects(gp.obj[gp.map][i].solidArea)){
					if(gp.obj[gp.map][i].collision == true) {
						entity.collisionOn = true;
					}
					if(player == true) {
						index = i;
					}
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[gp.map][i].solidArea.x = gp.obj[gp.map][i].solidAreaDefaultX;
				gp.obj[gp.map][i].solidArea.y = gp.obj[gp.map][i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
	
	public boolean checkBoxCollision(int map, int boxIndex, int futureX, int futureY) {
	    int boxLeftCol = futureX / gp.tileSize;
	    int boxRightCol = (futureX + gp.obj[map][boxIndex].solidArea.width) / gp.tileSize;
	    int boxTopRow = futureY / gp.tileSize;
	    int boxBottomRow = (futureY + gp.obj[map][boxIndex].solidArea.height) / gp.tileSize;

	    // Verifique colisão com os tiles na posição futura da caixa
	    for (int col = boxLeftCol; col <= boxRightCol; col++) {
	        for (int row = boxTopRow; row <= boxBottomRow; row++) {
	            if (gp.tileM.tile[gp.tileM.mapTileNum[map][col][row]].collision) {
	                return true; // Há colisão
	            }
	        }
	    }
	    for (int i = 0; i < gp.monster[map].length; i++) {
	        Entity monster = gp.monster[map][i];
	        if (monster != null) {
	            int monsterX = monster.x;
	            int monsterY = monster.y;
	            int monsterWidth = monster.solidArea.width;
	            int monsterHeight = monster.solidArea.height;

	            if (futureX < monsterX + monsterWidth &&
	                futureX + gp.obj[map][boxIndex].solidArea.width > monsterX &&
	                futureY < monsterY + monsterHeight &&
	                futureY + gp.obj[map][boxIndex].solidArea.height > monsterY) {
	                return true; // Há colisão com um monstro
	            }
	        }
	    }
	    
	 // Verifique colisão com as caixas
	    for (int i = 0; i < gp.obj[map].length; i++) {
	        Entity box = gp.obj[map][i];
	        if (box != null && i != boxIndex) {
	            // Verificar se a entidade é uma porta (Door ou DoorOpen)
	            if (box.name.equals("Door") || box.name.equals("DoorOpen") || box.name.equals("Coin")) {
	                continue; // Ignorar a verificação de colisão com portas
	            }
	            int boxX = box.x;
	            int boxY = box.y;
	            int boxWidth = box.solidArea.width;
	            int boxHeight = box.solidArea.height;

	            if (futureX < boxX + boxWidth &&
	                futureX + gp.obj[map][boxIndex].solidArea.width > boxX &&
	                futureY < boxY + boxHeight &&
	                futureY + gp.obj[map][boxIndex].solidArea.height > boxY) {
	                return true; // Há colisão com uma caixa
	            }
	        }
	    }

	    return false; // Não há colisão
	}
	
	// Monster Collision
		public int checkEntity(Entity entity, Entity[] target) {
			int index = 999;
			
			for(int i=0; i< target.length; i++) {
				if(target[i] != null) {
					
					// Get entity's solid area position
					entity.solidArea.x = entity.x + entity.solidArea.x;
					entity.solidArea.y = entity.y + entity.solidArea.y;
					// Get the object's solid area position 
					target[i].solidArea.x = target[i].x + target[i].solidArea.x;
					target[i].solidArea.y = target[i].y + target[i].solidArea.y;
					
					switch(entity.direction) {
					case "up":entity.solidArea.y -= entity.speed;break;
					case "down":entity.solidArea.y += entity.speed;break;
					case "left":entity.solidArea.x -= entity.speed;break;
					case "right":entity.solidArea.x += entity.speed;break;
					}
					
					if(entity.solidArea.intersects(target[i].solidArea)){
						if(target[i] != entity) {
							entity.collisionOn = true;
							index = i;
						}
					}
					
					if(entity.name == "Door" || entity.name == "DoorOpen") {
						entity.collisionOn = false;
					}
					
					entity.solidArea.x = entity.solidAreaDefaultX;
					entity.solidArea.y = entity.solidAreaDefaultY;
					target[i].solidArea.x = target[i].solidAreaDefaultX;
					target[i].solidArea.y = target[i].solidAreaDefaultY;
				}
			}
			
			return index;
		}
		
		public void checkPlayer(Entity entity){
			
			// Get entity's solid area position
			entity.solidArea.x = entity.x + entity.solidArea.x;
			entity.solidArea.y = entity.y + entity.solidArea.y;
			// Get the object's solid area position 
			gp.player.solidArea.x = gp.player.x + gp.player.solidArea.x;
			gp.player.solidArea.y = gp.player.y + gp.player.solidArea.y;

			switch(entity.direction) {
			case "up":
				entity.solidArea.y -= entity.speed;
				if(entity.solidArea.intersects(gp.player.solidArea) ){
					entity.collisionOn = true;
				}

				break;
			case "down":
				entity.solidArea.y += entity.speed;
				if(entity.solidArea.intersects(gp.player.solidArea)){
					entity.collisionOn = true;
				}
				break;
			case "left":
				entity.solidArea.x -= entity.speed;
				if(entity.solidArea.intersects(gp.player.solidArea)){
					entity.collisionOn = true;
				}
				break;
			case "right":
				entity.solidArea.x += entity.speed;
				if(entity.solidArea.intersects(gp.player.solidArea)){
					entity.collisionOn = true;
				}
				break;
			}
			entity.solidArea.x = entity.solidAreaDefaultX;
			entity.solidArea.y = entity.solidAreaDefaultY;
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
	}
		
		public boolean checkTileCollision(Tiro tiro, TileManeger tileManager) {
		    int tileCol = (tiro.x + tiro.solidArea.x) / gp.tileSize;
		    int tileRow = (tiro.y + tiro.solidArea.y) / gp.tileSize;
		    
		    int rightTileCol = (tiro.x + tiro.solidArea.x + tiro.solidArea.width) / gp.tileSize;
		    int bottomTileRow = (tiro.y + tiro.solidArea.y + tiro.solidArea.height) / gp.tileSize;

		    // Verificações para evitar índices fora dos limites
		    if (tileCol < 0 || tileRow < 0 || rightTileCol >= gp.maxScreenCol || bottomTileRow >= gp.maxScreenRow) {
		        return true; // Fora dos limites do mapa, consideramos como colisão
		    }

		    // Verifica a colisão para a área sólida do tiro
		    boolean collision = false;
		    for (int i = tileCol; i <= rightTileCol; i++) {
		        for (int j = tileRow; j <= bottomTileRow; j++) {
		            int tileNum = tileManager.mapTileNum[gp.map][i][j];
		            if (tileManager.tile[tileNum].collision) {
		                collision = true;
		                break;
		            }
		        }
		    }

		    return collision;
		}

		public boolean checkPlayerCollision(Entity monster, Entity player) {
		    
		    // Get monster or projectile's solid area position
		    monster.solidArea.x = monster.x + monster.solidArea.x;
		    monster.solidArea.y = monster.y + monster.solidArea.y;

	        // Get the player's solid area position
	        player.solidArea.x = player.x + player.solidArea.width;
	        player.solidArea.y = player.y + player.solidArea.height;
	        
	        if (monster.solidArea.intersects(player.solidArea)) { 
	        	monster.collisionOn = true;
	            return true;
	        }
	        
	        return false;
		}


}



