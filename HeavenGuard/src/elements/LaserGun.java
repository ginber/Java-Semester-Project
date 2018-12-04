package elements;

import java.awt.Graphics2D;

public class LaserGun extends BaseWeapon{
	
	public final static String TYPE = "shieldgun";
	
	public LaserGun(WeaponBuilder builder) {
		
		super(builder);
		
	}

	@Override
	public void fire() {
		
		getBuilder().context.getBulletsOnScreen().add(getBullet());
		
	}
}
