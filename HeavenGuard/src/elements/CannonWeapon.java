package elements;

import java.awt.Graphics2D;
import java.awt.Point;

public class CannonWeapon extends BaseWeapon {

	private static final long serialVersionUID = 1L;
	public final static String TYPE = "cannon";
	private int maxPower;

	public CannonWeapon(WeaponBuilder builder) {

		super(builder);

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
		super.setFireSpeed(fireSpeed*2/3);

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

		System.out.println("ateþleme açýsý: " + getAngle());

		setFiring(false);

	}


}
