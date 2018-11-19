package elements;

public abstract class BaseWeapon {
	
	private int fireSpeed;
	
	public double aimAngle(int mouseX, int mouseY, int weaponX, int weaponY ) {

		return Math.atan((mouseY - weaponY) / (mouseX - weaponX));
		
	}
	
	public double rotationAngle(long initialTime, double aimAngle, double gravity) {
    	double flightTime = (2 * fireSpeed * Math.sin(aimAngle)) / gravity;
    	return (2 * aimAngle) * ((System.currentTimeMillis() - initialTime) / flightTime);
    }

}
