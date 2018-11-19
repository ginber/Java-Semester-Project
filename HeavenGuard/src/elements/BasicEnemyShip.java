package elements;

public class BasicEnemyShip extends EnemyShip {

	private final static String TAG = "BES";
	private final static String IMG_PATH = "res/images/enemy0frame0.png";
	
	private final int SPEED = 20;
	private final int HEALTH = 20;
	private final int DAMAGE = 20;
	
	
	public BasicEnemyShip() {
		
		super(TAG, IMG_PATH);
		
		setSpeed(SPEED);
		setHealth(HEALTH);
		setDamage(DAMAGE);
		
	}
	
	@Override
	public void fire() {
		
		

	}

	@Override
	public void move(int xDir, int yDir) {
		
		
		
	};

}
