package elements;

import java.awt.Graphics2D;

public class ShieldGun extends BaseWeapon{

	final public static String TYPE = "shieldgun";
	final public static String PATH = "HeavenGuard/res/images/misc/shieldgunammoready.png";
	final public static String BULLET_PATH = "HeavenGuard/res/images/shieldgun/shieldgunammo.png";

	public ShieldGun(WeaponBuilder builder) {

		super(builder);

	}

	public ShieldGun() {

		

	}

	@Override
	public void fire() {

		getBuilder().context.getBulletsOnScreen().add(getBullet());

	}

}
