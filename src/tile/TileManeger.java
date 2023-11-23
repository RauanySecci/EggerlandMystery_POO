package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.Painel;
import main.UtilityTool;

public class TileManeger {
	Painel gp;
	public Tile [] tile;
	public int mapTileNum[][][];
	
	public TileManeger(Painel gp) {
		this.gp = gp;
		tile = new Tile[2];
		mapTileNum = new int [gp.m][gp.maxScreenCol][gp.maxScreenRow];
		getTileImage();
		loadMap("/maps/map01.txt", 0);
		loadMap("/maps/map02.txt", 1);
		loadMap("/maps/map03.txt", 2);
		loadMap("/maps/map04.txt", 3);
		loadMap("/maps/map05.txt", 4);
	}
	
	public void getTileImage() {
		setup(0, "bricks", true);
        setup(1, "preto", false);     
	}
	
	public void loadMap(String file, int map) {
		try {
			InputStream is = getClass().getResourceAsStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); //importar o arquivo de texto que tem mapeado meu background
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
				String line = br.readLine();
				while (col< gp.maxScreenCol) { //pegando os numeros do nosso arquivo txt
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[map][col][row] = num;
					col++;
					
				}
				
				if(col == gp.maxScreenCol) {
					col = 0;
					row++;
				}
				
			}
			br.close(); //fechar a leitura
		}catch(Exception e) {
			
		}
	}
	
	public void setup(int index, String imageName, boolean collision) {
		
		UtilityTool uTool = new UtilityTool();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read((getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		int col = 0;
		int row = 0;
		int x = 0;
		int y =0;
		while(col<gp.maxScreenCol && row<gp.maxScreenRow) {
			int tileNum = mapTileNum[gp.map][col][row]; //para saber o que deve desenhar '0' é tijolo '1' é parte preta
			g2.drawImage(tile[tileNum].image, x , y, null);
			col++;
			x+= gp.tileSize;
			
			if(col == gp.maxScreenCol) {
				col = 0;
				x=0;
				row++;
				y += gp.tileSize;
						
			}
		}
		

       
}
}