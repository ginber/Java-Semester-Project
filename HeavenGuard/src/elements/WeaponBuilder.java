package elements;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.MainFrame;

// Builder class for BaseWeapon since there are so many child classes for it

public class WeaponBuilder {

	int reloadTime, fireSpeed, weaponLevel, maxPower, damage;
	String weaponPath, type;
	Bullet bullet;
	MainFrame context = null;

	BufferedImage weaponImage = null;

	public WeaponBuilder(int damage, MainFrame context) {
		this.context = context;
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

		if(tag.equals(CannonWeapon.TYPE)) {

			weaponPath = BaseWeapon.CANNON_PATH;
			type = CannonWeapon.TYPE;
			bullet = new Bullet(BaseWeapon.CANNONBULLET_PATH, context);
			weaponToReturn = new CannonWeapon(this);

		}

		else if(tag.equals(MachineGun.TYPE)) {

			weaponPath = BaseWeapon.MACHINEGUN_PATH;
			type = MachineGun.TYPE;
			bullet = new Bullet(BaseWeapon.MGBULLET_PATH, context);
			weaponToReturn = new MachineGun(this);

		}

		else if(tag.equals(LaserGun.TYPE)) {

			weaponPath = BaseWeapon.LASERGUN_PATH;
			type = LaserGun.TYPE;
			bullet = new Bullet(BaseWeapon.LASERBULLET_PATH, context);
			weaponToReturn = new LaserGun(this);

		}

		else if(tag.equals(ShieldGun.TYPE)) {

			weaponPath = BaseWeapon.SHIELDGUN_PATH;
			type = ShieldGun.TYPE;
			bullet = new Bullet(BaseWeapon.SHIELDBULLET_PATH, context);
			weaponToReturn = new ShieldGun(this);

		}

		createImage(weaponPath);
		weaponToReturn.setImage(weaponImage);

		System.out.println("Weapon created\nType: " + weaponToReturn.getType()
		+ "\nDamage: " + damage);

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
