package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import main.Painel;
import object.OBJ_Box;
import object.OBJ_Coin;
import object.OBJ_Door;
import object.OBJ_DoorOpen;

public class SaveLoad{
	Painel gp;

	public SaveLoad(Painel gp) {
		this.gp = gp;

	}
	
	public void getObject(String itemName, int i) {
		
		switch(itemName) {
		case "Box": gp.obj[gp.map][i]= new OBJ_Box(gp);
			break;
		case "Coin": gp.obj[gp.map][i] = new OBJ_Coin(gp);
			break;
		case "Door": gp.obj[gp.map][i] = new OBJ_Door(gp);
			break;	
		case "DoorOpen": gp.obj[gp.map][i] = new OBJ_DoorOpen(gp);
			break;
		}
		
	}

	public void save() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

			DataStorage ds = new DataStorage();

			// Player stats
			ds.mapa = gp.map;
			ds.life = gp.player.life;
			ds.nCoins = gp.player.nCoins;
			ds.auxNCoins = gp.player.auxNCoins;
			ds.passNivel = gp.player.passNivel;
			ds.maisVida = gp.player.maisVida;
			ds.playerX = gp.player.x;
			ds.playerY = gp.player.y;

			// Objects
			
			for(int i = 0; i < gp.obj[gp.map].length; i++) {
				if(gp.obj[gp.map][i] != null) {
					ds.objNames.add(gp.obj[gp.map][i].name);
					ds.objX.add(gp.obj[gp.map][i].x);
					ds.objY.add(gp.obj[gp.map][i].y);
				}
			}
	
			// Write the DataStorage object
			oos.writeObject(ds);

		}
		catch(Exception e){
			System.out.println("Save Exception: " + e.getMessage());
			e.printStackTrace(); // Adiciona isso para imprimir o rastreamento da pilha
		}
	}

	public void load() {

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

			// Read the DataStorage object
			DataStorage ds = (DataStorage)ois.readObject();

			gp.map = ds.mapa;
			gp.player.life = ds.life;
			gp.player.nCoins = ds.nCoins; 
			gp.player.auxNCoins = ds.auxNCoins;
			gp.player.passNivel = ds.passNivel; 
			gp.player.maisVida = ds.maisVida;
			gp.player.x = ds.playerX;
			gp.player.y = ds.playerY;


			// Clear objects
			for(int i = 0; i < gp.obj[gp.map].length; i++) {
				gp.obj[gp.map][i] = null;
			}
			
			// Clear monster
			for(int i = 0; i < gp.monster[gp.map].length; i++) {
				gp.monster[gp.map][i] = null;
			}
			
			//Adiciona monstros
			gp.aSetter.setMonster();
			
			//Adiciona os objetos
			for(int i = 0; i < ds.objNames.size(); i++) {
				getObject(ds.objNames.get(i), i);
				gp.obj[gp.map][i].x = ds.objX.get(i);
				gp.obj[gp.map][i].y = ds.objY.get(i);
			}

		}
		catch(Exception e) {
			System.out.println("Load Exception: " + e.getMessage());
			e.printStackTrace(); // Adiciona isso para imprimir o rastreamento da pilha
		}
	}
}















