package main;

import monster.MON_Caveira;
import monster.MON_Cobra;
import monster.MON_Medusa;
import monster.MON_Roxo;
import object.OBJ_Box;
import object.OBJ_Coin;
import object.OBJ_Door;

public class AssetSetter {
	
	Painel gp;
	int m;
	
	public AssetSetter(Painel gp) {
		this.gp = gp;
	}

	public void setObject() { 
		m = 0;

		gp.obj[m][0] = new OBJ_Coin(gp);
		gp.obj[m][0].x = gp.tileSize * 3;
		gp.obj[m][0].y = gp.tileSize * 5;

		gp.obj[m][1] = new OBJ_Coin(gp);
		gp.obj[m][1].x = gp.tileSize * 3;
		gp.obj[m][1].y = gp.tileSize * 7;

		gp.obj[m][2] = new OBJ_Coin(gp);
		gp.obj[m][2].x = gp.tileSize * 7;
		gp.obj[m][2].y = gp.tileSize * 7;

		gp.obj[m][3] = new OBJ_Coin(gp);
		gp.obj[m][3].x = gp.tileSize * 11;
		gp.obj[m][3].y = gp.tileSize * 8;

		gp.obj[m][4] = new OBJ_Coin(gp);
		gp.obj[m][4].x = gp.tileSize * 11;
		gp.obj[m][4].y = gp.tileSize * 9;

		gp.obj[m][5] = new OBJ_Coin(gp);
		gp.obj[m][5].x = gp.tileSize * 11;
		gp.obj[m][5].y = gp.tileSize * 11;

		gp.obj[m][6] = new OBJ_Door(gp);
		gp.obj[m][6].x = gp.tileSize * 9;
		gp.obj[m][6].y = gp.tileSize * 6;

		gp.obj[m][7] = new OBJ_Box(gp);
		gp.obj[m][7].x = gp.tileSize * 3;
		gp.obj[m][7].y = gp.tileSize * 6;

		gp.obj[m][8] = new OBJ_Box(gp);
		gp.obj[m][8].x = gp.tileSize * 7;
		gp.obj[m][8].y = gp.tileSize * 6;

		m = 1;

		gp.obj[m][0] = new OBJ_Coin(gp);
		gp.obj[m][0].x = 3 * gp.tileSize;
		gp.obj[m][0].y = 1 * gp.tileSize;

		gp.obj[m][1] = new OBJ_Coin(gp);
		gp.obj[m][1].x = 7 * gp.tileSize;
		gp.obj[m][1].y = 1 * gp.tileSize;

		gp.obj[m][2] = new OBJ_Coin(gp);
		gp.obj[m][2].x = 3 * gp.tileSize;
		gp.obj[m][2].y = 10 * gp.tileSize;

		gp.obj[m][3] = new OBJ_Coin(gp);
		gp.obj[m][3].x = 5 * gp.tileSize;
		gp.obj[m][3].y = 7 * gp.tileSize;

		gp.obj[m][4] = new OBJ_Coin(gp);
		gp.obj[m][4].x = 7 * gp.tileSize;
		gp.obj[m][4].y = 11 * gp.tileSize;

		gp.obj[m][5] = new OBJ_Box(gp);
		gp.obj[m][5].x = gp.tileSize * 3;
		gp.obj[m][5].y = gp.tileSize * 9;

		gp.obj[m][6] = new OBJ_Box(gp);
		gp.obj[m][6].x = gp.tileSize * 3;
		gp.obj[m][6].y = gp.tileSize * 11;

		gp.obj[m][7] = new OBJ_Box(gp);
		gp.obj[m][7].x = gp.tileSize * 6;
		gp.obj[m][7].y = gp.tileSize * 7;

		gp.obj[m][8] = new OBJ_Box(gp);
		gp.obj[m][8].x = gp.tileSize * 8;
		gp.obj[m][8].y = gp.tileSize * 3;

		gp.obj[m][9] = new OBJ_Box(gp);
		gp.obj[m][9].x = gp.tileSize * 9;
		gp.obj[m][9].y = gp.tileSize * 6;

		gp.obj[m][10] = new OBJ_Box(gp);
		gp.obj[m][10].x = gp.tileSize * 10;
		gp.obj[m][10].y = gp.tileSize * 2;

		gp.obj[m][11] = new OBJ_Door(gp);
		gp.obj[m][11].x = 9 * gp.tileSize;
		gp.obj[m][11].y = 9 * gp.tileSize;

		m = 2;

		gp.obj[m][0] = new OBJ_Coin(gp);
		gp.obj[m][0].x = 6 * gp.tileSize;
		gp.obj[m][0].y = 6 * gp.tileSize;

		gp.obj[m][1] = new OBJ_Door(gp);
		gp.obj[m][1].x = 10 * gp.tileSize;
		gp.obj[m][1].y = 10 * gp.tileSize;

		gp.obj[m][2] = new OBJ_Box(gp);
		gp.obj[m][2].x = gp.tileSize * 10;
		gp.obj[m][2].y = gp.tileSize * 10;

		m = 3;

		gp.obj[m][3] = new OBJ_Coin(gp);
		gp.obj[m][3].x = 4 * gp.tileSize;
		gp.obj[m][3].y = 3 * gp.tileSize;

		gp.obj[m][1] = new OBJ_Coin(gp);
		gp.obj[m][1].x = 8 * gp.tileSize;
		gp.obj[m][1].y = 3 * gp.tileSize;

		gp.obj[m][2] = new OBJ_Coin(gp);
		gp.obj[m][2].x = 6 * gp.tileSize;
		gp.obj[m][2].y = 7 * gp.tileSize;

		gp.obj[m][0] = new OBJ_Door(gp);
		gp.obj[m][0].x = 6 * gp.tileSize;
		gp.obj[m][0].y = 10 * gp.tileSize;

		gp.obj[m][4] = new OBJ_Box(gp);
		gp.obj[m][4].x = gp.tileSize * 2;
		gp.obj[m][4].y = gp.tileSize * 5;

		gp.obj[m][5] = new OBJ_Box(gp);
		gp.obj[m][5].x = gp.tileSize * 3-5;
		gp.obj[m][5].y = gp.tileSize * 6;

		gp.obj[m][6] = new OBJ_Box(gp);
		gp.obj[m][6].x = gp.tileSize * 6;
		gp.obj[m][6].y = gp.tileSize * 8;

		gp.obj[m][7] = new OBJ_Box(gp);
		gp.obj[m][7].x = gp.tileSize * 9;
		gp.obj[m][7].y = gp.tileSize * 6;

		gp.obj[m][8] = new OBJ_Box(gp);
		gp.obj[m][8].x = gp.tileSize * 10;
		gp.obj[m][8].y = gp.tileSize * 5;

	}
	
	public void setMonster() {
		m = 0;
		
		gp.monster[m][0] = new MON_Cobra(gp);
		gp.monster[m][0].x = gp.tileSize * 2;
		gp.monster[m][0].y = gp.tileSize * 11;
		
		gp.monster[m][1] = new MON_Cobra(gp);
		gp.monster[m][1].x = gp.tileSize * 6;
		gp.monster[m][1].y = gp.tileSize * 11;
		

		gp.monster[m][2] = new MON_Cobra(gp);
		gp.monster[m][2].x = gp.tileSize * 11;
		gp.monster[m][2].y = gp.tileSize * 7;
		
		m = 1;
		
		gp.monster[m][0] = new MON_Caveira(gp);
		gp.monster[m][0].x = gp.tileSize * 5;
		gp.monster[m][0].y = gp.tileSize * 6;
		
		gp.monster[m][1] = new MON_Caveira(gp);
		gp.monster[m][1].x = gp.tileSize * 7;
		gp.monster[m][1].y = gp.tileSize * 6;
		
		m = 2;
		gp.monster[m][1] = new MON_Roxo(gp, 1);
		gp.monster[m][1].setMonsterDirection("down");
		gp.monster[m][1].x = gp.tileSize * 8;
		gp.monster[m][1].y = gp.tileSize * 4;
		
		gp.monster[m][3] = new MON_Roxo(gp, 2);
		gp.monster[m][3].setMonsterDirection("left");
		gp.monster[m][3].x = gp.tileSize * 8;
		gp.monster[m][3].y = gp.tileSize * 8;
		
		gp.monster[m][2] = new MON_Roxo(gp, 3);
		gp.monster[m][2].setMonsterDirection("up");
		gp.monster[m][2].x = gp.tileSize * 4;
		gp.monster[m][2].y = gp.tileSize * 8;
		
		gp.monster[m][0] = new MON_Roxo(gp, 4);
		gp.monster[m][0].setMonsterDirection("right");
		gp.monster[m][0].x = gp.tileSize * 4;
		gp.monster[m][0].y = gp.tileSize * 4;
		
		m = 3;
		
		gp.monster[m][0] = new MON_Medusa(gp);
		gp.monster[m][0].x = gp.tileSize * 4;
		gp.monster[m][0].y = gp.tileSize * 7;
	
		gp.monster[m][1] = new MON_Medusa(gp);
		gp.monster[m][1].x = gp.tileSize * 8;
		gp.monster[m][1].y = gp.tileSize * 7;
		

	}
}















