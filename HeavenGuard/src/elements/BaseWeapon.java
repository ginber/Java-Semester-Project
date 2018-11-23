package elements;

import javax.swing.ImageIcon;

public abstract class BaseWeapon extends ImageIcon {
	
	private int fireSpeed;
	private double reloadTime;
	
	private String IMAGE_PATH;
	
	public double aimAngle(int mouseX, int mouseY, int weaponX, int weaponY ) {

		return Math.atan((mouseY - weaponY) / (mouseX - weaponX));
		
	}
	
	public double rotationAngle(long initialTime, double aimAngle, double gravity) {
    	double flightTime = (2 * fireSpeed * Math.sin(aimAngle)) / gravity;
    	return (2 * aimAngle) * ((System.currentTimeMillis() - initialTime) / flightTime);
    	}
	
	public void fire(int x, int y) {
		
	}

	protected int getFireSpeed() {
		return fireSpeed;
	}

	protected void setFireSpeed(int fireSpeed) {
		this.fireSpeed = fireSpeed;
	}
	
}

