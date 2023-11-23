package data;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable{
	// PLAYER STATUS
	
	private static final long serialVersionUID = 388491969764013129L;
	int mapa;
	int life;
	int nCoins;
	int auxNCoins;
	int passNivel;
	int maisVida;
	int playerX;
	int playerY;
	
	// Objects
	ArrayList<String> objNames = new ArrayList<>();
	ArrayList<Integer> objX = new ArrayList<>();
	ArrayList<Integer> objY= new ArrayList<>();
	
}
