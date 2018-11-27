package elements;

public class WeaponFactory {
	
	public static void createWeapon(String weaponTag, int maxPower, int damage, String path) {
		
		if(weaponTag.equals("cannon")) {
			
			
			
			new CannonWeapon(maxPower, damage);
			
		}
		
		else if(weaponTag.equals("mg")) {
			
			new CannonWeapon(maxPower, damage);
			
		}
		
		else if(weaponTag.equals("lasergun")) {
			
			new CannonWeapon(maxPower, damage);
			
		}
		
		else if(weaponTag.equals("shieldgun")) {
			
			new CannonWeapon(maxPower, damage);
			
		}
		
	}

}
