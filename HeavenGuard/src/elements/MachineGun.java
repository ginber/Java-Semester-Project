package elements;

import java.awt.Graphics2D;

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
	public Bullet fire(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
		Bullet bullet = getBullet();
		
		return bullet;
		
	}

}
