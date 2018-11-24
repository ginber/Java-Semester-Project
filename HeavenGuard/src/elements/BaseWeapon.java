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

	private static String IMAGE_PATH = "HeavenGuard/res/images/cannongun/weapon_cannon_0.png";

	private static BufferedImage weaponImage = null;

	private static BaseWeapon baseWeapon = null;

	public  BaseWeapon() {

		try {

			weaponImage = ImageIO.read(new File(IMAGE_PATH));

		} catch(IOException e) {

			System.out.println("Weapon could not be found");

		}

		setImage(weaponImage);

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

	public void rotate(double rotationAngle) {

		AffineTransform transform = AffineTransform.getRotateInstance(rotationAngle, 
				weaponImage.getWidth() / 2, weaponImage.getHeight() / 2);

		AffineTransformOp transformOp = new AffineTransformOp(transform, 
				AffineTransformOp.TYPE_BILINEAR);

		Graphics2D g2d = (Graphics2D) weaponImage.getGraphics();

		g2d.drawImage(transformOp.filter(weaponImage, null), weaponImage.getWidth() / 2,
				weaponImage.getHeight() / 2, null);

	}

	protected int getFireSpeed() {
		return fireSpeed;
	}

	protected void setFireSpeed(int fireSpeed) {
		this.fireSpeed = fireSpeed;
	}

}

