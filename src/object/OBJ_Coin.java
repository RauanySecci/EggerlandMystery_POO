package object;

import entity.Entity;
import main.Painel;

public class OBJ_Coin extends Entity{

	public OBJ_Coin(Painel gp) {
		super(gp);
		
		
		name = "Coin";
		down1 = setup("/objects/coin1");
		
	}
}
