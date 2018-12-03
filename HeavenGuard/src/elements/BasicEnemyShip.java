package elements;

public class BasicEnemyShip extends EnemyShip {

	public final static String TAG = "BES";
	private final static String IMG_PATH = "res/images/enemy0frame0.png";
	
	private final int SPEED = 20;
	private final int HEALTH = 20;
	private final int DAMAGE = 20;
	private int xPosition,yPosition;
	
	
	public BasicEnemyShip(EnemyShipBuilder builder) {
		
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
		
		
		
	}

	protected int getxPosition() {
		return xPosition;
	}

	protected void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	protected int getyPosition() {
		return yPosition;
	}

	protected void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	protected static String getImgPath() {
		return IMG_PATH;
	}
	
}
