package elements;

import java.awt.Graphics2D;
import java.awt.Point;

public class CannonWeapon extends BaseWeapon {

	private static final long serialVersionUID = 1L;
	
	final public static String TYPE = "cannon";
	final public static String PATH = "HeavenGuard/res/images/cannongun/weapon_cannon_0.png";
	final public static String BULLET_PATH = "HeavenGuard/res/images/cannongun/cannonbullet.png";
	
	private int maxPower;

	public CannonWeapon(WeaponBuilder builder) {

		super(builder);

	}
	
	public CannonWeapon() {

		

	}

	public int getMaxPower() {
		return maxPower;
	}

	public void setMaxPower(int maxPower) {
		this.maxPower = maxPower;
	}

	@Override
	public void setFireSpeed(int fireSpeed) {
		// Fire speed was too much
		super.setFireSpeed(fireSpeed * 3 / 4);

	}

	@Override
	public void fire() {	

		Bullet bullet = getBuilder().context.getBullet();

		System.out.println("CannonWeapon firing");
		bullet.setTimeFired(System.currentTimeMillis());
		bullet.setIndex(getBuilder().context.getBulletsOnScreen().size());
		bullet.setFiredAngle(getBuilder().context.getBaseWeapon().getAngle());
		bullet.setFired(true);

		getBuilder().context.getBulletsOnScreen().add(bullet);

		//System.out.println("ateþleme açýsý: " + getAngle());

		setFiring(false);

	}

}
