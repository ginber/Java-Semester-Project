package elements;

import game.MainFrame;

public class BasicEnemyBullet extends Bullet {

	public BasicEnemyBullet(String path, MainFrame context) {
		
		super(path, context);
		
	}
	
	public void move(int initialSpeed) {
		super.move(0, initialSpeed);
	}

}
