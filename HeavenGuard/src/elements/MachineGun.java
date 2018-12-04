package elements;

import java.awt.Graphics2D;
import java.awt.Point;

public class MachineGun extends BaseWeapon {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String TYPE = "mg";

	public MachineGun (WeaponBuilder builder) {

		super(builder);

	}

	@Override
	public void fire() {
		
		getBuilder().context.getBulletsOnScreen().add(getBullet());
		
	}

}
