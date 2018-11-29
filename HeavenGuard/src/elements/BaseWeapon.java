package elements;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class BaseWeapon extends ImageIcon {

	private int fireSpeed, damage, weaponLevel,  reloadTime;
	
	// Canonical paths of image files

	static final String CANNON_PATH = "HeavenGuard/res/images/cannongun/weapon_cannon_0.png";
	static final String MACHINEGUN_PATH = "HeavenGuard/res/images/machinegun/weapon_machinegun_0.png";
	static final String LASERGUN_PATH = "HeavenGuard/res/images/misc/lasergun_0.png";
	static final String SHIELDGUN_PATH = "HeavenGuard/res/images/misc/shieldgunammoready.png";
	
	static final String CANNONBULLET_PATH = "HeavenGuard/res/images/cannongun/cannonbullet.png";
	static final String MGBULLET_PATH = "HeavenGuard/res/images/machinegun/machinegun.png";
	static final String LASERBULLET_PATH = "HeavenGuard/res/images/LaserGun/lasergunammo.png";
	static final String SHIELDBULLET_PATH = "HeavenGuard/res/images/shieldgun/shieldgunammo.png";

	private static String weaponPath, bulletPath;

	private BufferedImage weaponImage = null;
	private BufferedImage bulletImage = null;
	
	public BaseWeapon(WeaponBuilder builder) {
		
		this.damage = builder.damage;
		this.reloadTime = builder.reloadTime;
		this.fireSpeed = builder.fireSpeed;
		this.weaponLevel = builder.weaponLevel;
		this.weaponPath = builder.weaponPath;
		this.bulletPath = builder.bulletPath;

		//this.bulletImage = builder.bulletImage;
		
	}

	public double aimAngle(double mouseX, double mouseY, double weaponX, double weaponY ) {
		double angle = (Math.toDegrees(Math.atan((Math.abs((mouseY - weaponY)) / (mouseX - weaponX)))));
		if(angle > 0) {
			return 90-angle;	
		}else return 270-angle;


	}

	public double rotationAngle(long initialTime, double aimAngle, double gravity) {

		double flightTime = (2 * fireSpeed * Math.sin(aimAngle)) / gravity;
		return (2 * aimAngle) * ((System.currentTimeMillis() - initialTime) / flightTime);

	}

	public void fire(double aimAngle) {

		

	}

	public int getFireSpeed() {
		return fireSpeed;
	}

	public void setFireSpeed(int fireSpeed) {
		this.fireSpeed = fireSpeed;
	}

	public String getWeaponPath() {

		return weaponPath;

	}

	public String getBulletPath() {

		return bulletPath;

	}

	public BufferedImage getBulletImage() {

		return bulletImage;

	}
}

