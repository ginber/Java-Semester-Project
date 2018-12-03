package elements;

import java.awt.Graphics2D;
import java.awt.Point;

public class CannonWeapon extends BaseWeapon {

	/**
	 * 
	 */
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
	public void fire(Graphics2D g2d) {
		
		Bullet bullet = getBullet();
		g2d.drawImage(bullet.getImage(), bullet.getCurrentLocation().x, 
				bullet.getCurrentLocation().y, null);
				
		setFiring(false);
		
	}


}
