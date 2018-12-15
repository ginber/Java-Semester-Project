package elements;

import java.awt.Graphics2D;

public class LaserGun extends BaseWeapon{

	final public static String TYPE = "lasergun";
	final public static String PATH = "HeavenGuard/res/images/misc/lasergun_0.png";
	final public static String BULLET_PATH = "HeavenGuard/res/images/LaserGun/lasergunammo.png";

	public LaserGun(WeaponBuilder builder) {

		super(builder);

	}

	public LaserGun() {

		

	}

	@Override
	public void fire() {

		getBuilder().context.getBulletsOnScreen().add(getBullet());

	}

}
