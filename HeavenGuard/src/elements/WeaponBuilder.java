package elements;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

// Builder class for BaseWeapon since there are so many child classes for it

public class WeaponBuilder {

	int damage, reloadTime, fireSpeed, weaponLevel, maxPower;
	String weaponPath, bulletPath;

	BufferedImage weaponImage = null;
	BufferedImage bulletImage = null;

	public WeaponBuilder(int damage) {
		this.damage = damage;
	}

	public WeaponBuilder reloadTime(int reloadTime) {
		this.reloadTime = reloadTime;
		return this;
	}

	public WeaponBuilder maxPower(int maxPower) {
		this.maxPower = maxPower;
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

		BaseWeapon weaponToReturn = null;

		if(tag.equals("cannon")) {

			weaponPath = BaseWeapon.CANNON_PATH;
			bulletPath = BaseWeapon.CANNONBULLET_PATH;
			weaponToReturn = new CannonWeapon(this);

		}

		else if(tag.equals("mg")) {

			weaponPath = BaseWeapon.MACHINEGUN_PATH;
			bulletPath = BaseWeapon.MGBULLET_PATH;
			weaponToReturn = new MachineGun(this);

		}

		else if(tag.equals("lasergun")) {

			weaponPath = BaseWeapon.LASERGUN_PATH;
			bulletPath = BaseWeapon.LASERBULLET_PATH;
			weaponToReturn = new LaserGun(this);

		}

		else if(tag.equals("shieldgun")) {

			weaponPath = BaseWeapon.SHIELDGUN_PATH;
			bulletPath = BaseWeapon.SHIELDBULLET_PATH;
			weaponToReturn = new ShieldGun(this);

		}

		createImages(weaponPath, bulletPath);
		weaponToReturn.setImage(weaponImage);

		return weaponToReturn;

	}

	private void createImages(String weaponPath, String bulletPath) {

		try {

			weaponImage = ImageIO.read(new File(weaponPath));
			bulletImage = ImageIO.read(new File(bulletPath));

		} catch(IOException e) {

			System.out.println("Weapon or bullet could not be found");

		}

	}


}
