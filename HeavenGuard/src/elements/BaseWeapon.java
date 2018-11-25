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

import com.sun.media.sound.ModelSource;

public class BaseWeapon extends ImageIcon {

	private int fireSpeed;
	protected double reloadTime = 0;
	protected int damage,weapon1lvl = 1,weapon2lvl = 1,weapon3lvl = 1,weapon4lvl = 1;

	private static final String CANNON_PATH = "HeavenGuard/res/images/cannongun/weapon_cannon_0.png";
	private static final String MACHINEGUN_PATH = "HeavenGuard/res/images/machinegun/weapon_machinegun_0.png";
	private static final String LASERGUN_PATH = "HeavenGuard/res/images/misc/lasergun_0.png";
	private static final String SHIELDGUN_PATH = "HeavenGuard/res/images/misc/shieldgunammoready.png";

	private static BufferedImage weaponImage = null;

	private static BaseWeapon baseWeapon = null;

	public BaseWeapon createWeapon(String tag) {
		
		if(tag.equals("cannon")) {
			
			try {
				
				weaponImage = ImageIO.read(new File(CANNON_PATH));

			} catch(IOException e) {

				System.out.println("Weapon could not be found");

			}


			baseWeapon = new CannonWeapon(100*weapon1lvl, weapon1lvl * 3);
			
		}
		
		else if(tag.equals("mg")) {
			
			try { 
				weaponImage = ImageIO.read(new File(MACHINEGUN_PATH));
			} catch(IOException e) {
				System.out.println("Weapon could not be found");
			}
			baseWeapon = new MachineGun(weapon2lvl + 1);
			
		}
		
	else if(tag.equals("lasergun")) {
			
			try { 
				weaponImage = ImageIO.read(new File(LASERGUN_PATH));
			} catch(IOException e) {
				System.out.println("Weapon could not be found");
			}
			baseWeapon = new LaserGun(30 - (weapon3lvl * 2), (weapon3lvl*10));
			
		}
	else if(tag.equals("shieldgun")) {
		
		try { 
			weaponImage = ImageIO.read(new File(SHIELDGUN_PATH));
		} catch(IOException e) {
			System.out.println("Weapon could not be found");
		}
		baseWeapon = new ShieldGun(60 - (weapon4lvl * 2), (weapon4lvl * 20));
		
	}
		
		// en sonunda
		
		baseWeapon.setImage(weaponImage);
		return baseWeapon;
		
	}

	public double aimAngle(double mouseX, double mouseY, double weaponX, double weaponY ) {
		
		System.out.println("Weapon x: " + weaponX);
		System.out.println("Weapon y: " + weaponY);
		return (Math.toDegrees(Math.atan((Math.abs((mouseY - weaponY)) / (mouseX - weaponX)))));

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

