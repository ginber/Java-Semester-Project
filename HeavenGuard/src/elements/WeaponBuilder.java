package elements;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.MainFrame;

// Builder class for BaseWeapon since there are so many child classes for it

public class WeaponBuilder {

	int reloadTime, fireSpeed, weaponLevel, maxPower, damage;
	String weaponPath, type, bulletPath;
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

			weaponPath = CannonWeapon.PATH;
			type = CannonWeapon.TYPE;
			bullet = new Bullet(CannonWeapon.BULLET_PATH, context);
			bulletPath = CannonWeapon.BULLET_PATH;
			weaponToReturn = new CannonWeapon(this);

		}

		else if(tag.equals(MachineGun.TYPE)) {

			weaponPath = MachineGun.PATH;
			type = MachineGun.TYPE;
			bullet = new Bullet(MachineGun.BULLET_PATH, context);
			bulletPath = MachineGun.BULLET_PATH;
			weaponToReturn = new MachineGun(this);

		}

		else if(tag.equals(LaserGun.TYPE)) {

			weaponPath = LaserGun.PATH;
			type = LaserGun.TYPE;
			bullet = new Bullet(LaserGun.BULLET_PATH, context);
			bulletPath = LaserGun.BULLET_PATH;
			weaponToReturn = new LaserGun(this);

		}

		else if(tag.equals(ShieldGun.TYPE)) {

			weaponPath = ShieldGun.PATH;
			type = ShieldGun.TYPE;
			bullet = new Bullet(ShieldGun.BULLET_PATH, context);
			bulletPath = ShieldGun.BULLET_PATH;
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
