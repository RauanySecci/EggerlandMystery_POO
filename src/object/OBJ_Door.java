package object;

import entity.Entity;
import main.Painel;

public class OBJ_Door extends Entity {
	
	Painel gp;
	
	public OBJ_Door(Painel gp) {
		
		super(gp);
		
		name = "Door";
		down1 = setup("/objects/portafechada");
		collision = false;
		
	}
}
