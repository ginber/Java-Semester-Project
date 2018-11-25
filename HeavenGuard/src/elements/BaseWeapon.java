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

	private int fireSpeed;
	private double reloadTime;

	private final String CANNON_PATH = "HeavenGuard/res/images/cannongun/weapon_cannon_0.png";
	private final String MACHINEGUN_PATH = "HeavenGuard/res/images/machinegun/weapon_machinegun_0.png";
	//private final String WEAPON_2_PATH = "HeavenGuard/res/images/misc/house.png";
	//private final String WEAPON_3_PATH = "HeavenGuard/res/images/misc/house.png";

	private static BufferedImage weaponImage = null;

	private static BaseWeapon baseWeapon = null;

	public BaseWeapon createWeapon(String tag) {
		
		if(tag.equals("cannon")) {
			
			try {
				
				weaponImage = ImageIO.read(new File(CANNON_PATH));

			} catch(IOException e) {

				System.out.println("Weapon could not be found");

			}

			baseWeapon = new CannonWeapon(30);
			
		}
		
		else if(tag.equals("mg")) {
			
			// bla bla bla
			
		}
		
		// en sonunda
		
		setImage(weaponImage);
		return baseWeapon;
		
	}

	public double aimAngle(int mouseX, int mouseY, int weaponX, int weaponY ) {

		return Math.atan((mouseY - weaponY) / (mouseX - weaponX));

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

