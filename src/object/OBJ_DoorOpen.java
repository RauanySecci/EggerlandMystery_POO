package object;

import entity.Entity;
import main.Painel;

public class OBJ_DoorOpen extends Entity{
	
	public OBJ_DoorOpen(Painel gp) {
		
		super(gp);
		
		name = "DoorOpen";
		down1 = setup("/objects/portaaberta");
		collision = false;
	}
}
