package elements;

import java.awt.Graphics2D;

public class ShieldGun extends BaseWeapon{
	
	public final static String TYPE = "shieldgun";
	
	public ShieldGun(WeaponBuilder builder) {
		
		super(builder);
		
	}

	@Override
	public Bullet fire(Graphics2D g2d) {
		
		Bullet bullet = getBullet();
		
		return bullet;
		
	}
	
	

}
