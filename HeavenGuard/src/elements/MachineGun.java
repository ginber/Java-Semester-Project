package elements;

import java.awt.Graphics2D;
import java.awt.Point;

public class MachineGun extends BaseWeapon {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final public static String TYPE = "mg";
	final public static String PATH = "HeavenGuard/res/images/machinegun/weapon_machinegun_0.png";
	final public static String BULLET_PATH = "HeavenGuard/res/images/machinegun/machinegunammo.png";
	
	public MachineGun (WeaponBuilder builder) {

		super(builder);

	}
	
	public MachineGun () {

		

	}

	@Override
	public void fire() {
		
		getBuilder().context.getBulletsOnScreen().add(getBullet());
		
	}

}
