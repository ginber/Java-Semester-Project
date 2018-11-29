package elements;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.MainFrame;

// Builder class for BaseWeapon since there are so many child classes for it

public class WeaponBuilder {

	int damage, reloadTime, fireSpeed, weaponLevel, maxPower;
	String weaponPath;
	Bullet bullet;
	MainFrame context = null;
	
	BufferedImage weaponImage = null;

	public WeaponBuilder(int damage, MainFrame context) {
		this.damage = damage;
		this.context = context;
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
			bullet = new Bullet(BaseWeapon.CANNONBULLET_PATH, context);
			weaponToReturn = new CannonWeapon(this);

		}

		else if(tag.equals("mg")) {

			weaponPath = BaseWeapon.MACHINEGUN_PATH;
			bullet = new Bullet(BaseWeapon.MGBULLET_PATH, context);
			weaponToReturn = new MachineGun(this);

		}

		else if(tag.equals("lasergun")) {

			weaponPath = BaseWeapon.LASERGUN_PATH;
			bullet = new Bullet(BaseWeapon.LASERBULLET_PATH, context);
			weaponToReturn = new LaserGun(this);

		}

		else if(tag.equals("shieldgun")) {

			weaponPath = BaseWeapon.SHIELDGUN_PATH;
			bullet = new Bullet(BaseWeapon.SHIELDBULLET_PATH, context);
			weaponToReturn = new ShieldGun(this);

		}

		createImage(weaponPath);
		weaponToReturn.setImage(weaponImage);

		return weaponToReturn;

	}
	
	private void createImage(String weaponPath) {
		
		try {

			weaponImage = ImageIO.read(new File(weaponPath));

		} catch(IOException e) {

			System.out.println("Weapon could not be found");

		}
		
	}


}
