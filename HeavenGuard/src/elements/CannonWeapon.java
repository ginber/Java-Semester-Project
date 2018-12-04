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
	
	private boolean isFired = false;
	
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
	public void fire() {	
		
		if(!isFired) {
			
			getBuilder().context.getBulletsOnScreen().add(getBullet());
			
			isFired = true;
			
		}
		
	}


}
