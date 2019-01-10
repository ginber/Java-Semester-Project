package elements;

import java.awt.image.BufferedImage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public abstract class BaseWeapon extends ImageIcon {

	private int fireSpeed, weaponLevel,  reloadTime;
	private boolean isFiring = false;
	
	private double angle, damage;
	
	private Clip clip = null;
	private AudioInputStream sfxInput = null;

	private String weaponPath, type, bulletPath, musicPath;
	private BufferedImage weaponImage = null;	
	private WeaponBuilder weaponBuilder = null;

	private Bullet bullet;

	public BaseWeapon(WeaponBuilder builder) {
		
		weaponBuilder = builder;

		this.damage = builder.damage;
		this.reloadTime = builder.reloadTime;
		this.fireSpeed = builder.fireSpeed;
		this.weaponLevel = builder.weaponLevel;
		this.weaponPath = builder.weaponPath;
		this.bullet = builder.bullet;
		this.weaponImage = builder.weaponImage;
		this.type = builder.type;
		this.bulletPath = builder.bulletPath;
		
		try {
			
			clip = AudioSystem.getClip();
			
		} catch (LineUnavailableException e) {
			
			e.printStackTrace();
			
		}

	}
	
	public BaseWeapon() {
		
	}

	public Bullet getBullet() {

		return bullet;

	}

	// Function that gives how many degrees the weapon should rotate according to mouse movement
	public double aimAngle(double mouseX, double mouseY, double weaponX, double weaponY ) {

		double angle = (Math.toDegrees(Math.atan((Math.abs((mouseY - weaponY)) / (mouseX - weaponX)))));

		if(angle > 0) {

			return (90 - angle);

		}

		return (270 - angle);

	}
	
	public abstract void fire();
	
	public void playFireSound() {
		
		
		
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

	public boolean isFiring() {
		return isFiring;
	}

	public void setFiring(boolean isFiring) {
		this.isFiring = isFiring;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public WeaponBuilder getBuilder() {
		return weaponBuilder;
	}

	
	public Bullet createBullet() {
		
		return new Bullet(bulletPath, getBuilder().context);
		
	}

	public Clip getClip() {
		return clip;
	}

	public void setClip(Clip clip) {
		this.clip = clip;
	}
	
	
	
}

