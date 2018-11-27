package elements;


public class WeaponBuilder {

	int damage, reloadTime, fireSpeed, weaponLevel;
	String weaponPath, bulletPath;

	public WeaponBuilder(int damage) {
		this.damage = damage;
	}

	public WeaponBuilder reloadTime(int reloadTime) {
		this.reloadTime = reloadTime;
		return this;
	}

	public WeaponBuilder weaponPath(String weaponPath) {
		this.weaponPath = weaponPath;
		return this;
	}

	public WeaponBuilder bulletPath(String bulletPath) {
		this.bulletPath = bulletPath;
		return this;
	}

	public WeaponBuilder fireSpeed(int fireSpeed) {

		this.fireSpeed = fireSpeed;
		return this;

	}

	public WeaponBuilder weaponLevel(int weaponLevel) {

		this.weaponLevel = weaponLevel;
		return this;

	}

	public BaseWeapon build(String tag) {

		if(tag.equals("cannon")) {

			return new CannonWeapon(maxPower, damage)

		}

	}


}
