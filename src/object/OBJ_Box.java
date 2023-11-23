package object;


import entity.Entity;
import main.Painel;

public class OBJ_Box extends Entity {
	
	Painel gp;
	
	public OBJ_Box(Painel gp) {
		
		super(gp);
		
		name = "Box";
		down1 = setup("/objects/box");
		collision = true;
		
		solidArea.x = 3;
		solidArea.y = 1;
		solidArea.width = 52;
		solidArea.height = 52;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
}
