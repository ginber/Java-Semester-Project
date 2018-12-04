package elements;

import java.awt.Graphics2D;

public class ShieldGun extends BaseWeapon{
	
	public final static String TYPE = "shieldgun";
	
	public ShieldGun(WeaponBuilder builder) {
		
		super(builder);
		
	}

	@Override
	public void fire() {
		
		getBuilder().context.getBulletsOnScreen().add(getBullet());
		
	}
	
	

}
