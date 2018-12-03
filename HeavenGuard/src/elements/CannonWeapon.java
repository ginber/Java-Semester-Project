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
	public Bullet fire(Graphics2D g2d) {
		
		Bullet bullet = getBullet();
		
		int bulletX = bullet.getCurrentLocation().x;
		int bulletY = bullet.getCurrentLocation().y;
		
		bullet.setCurrentLocation(new Point(bulletX, bulletY));
		
		while(bullet.isOnScreen()) {
			
			bulletX += getFireSpeed();
			bulletY += bulletX * Math.tan(getAngle());
			
			bullet.setCurrentLocation(new Point(bulletX, bulletY));
			
			g2d.drawImage(bullet.getImage(), bulletX, bulletY, null);
			
		}
				
		setFiring(false);
		
		return bullet;
		
	}


}
