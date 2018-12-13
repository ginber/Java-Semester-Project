package elements;

import java.util.Random;

public class BasicEnemyShip extends EnemyShip {

	final static String TAG = "BES";
	final static String IMG_PATH = "HeavenGuard/res/images/spaceship1/enemy0frame0.png";
	
	
	public BasicEnemyShip(EnemyShipBuilder builder) {
		
		super(builder);
		
	}
	

	@Override
	public void fire() {
		
		

	}

	@Override
	public void move() {
		
		boolean right = (Math.random() < 0.5) ? true : false;
		
		int changeX = (int) (Math.random()+1)*(getLevel()*20);
		
		if(!right) {
			changeX = -changeX;
		}
		
		setxPosition(getxPosition()+changeX);
		
	}


	
	
}
