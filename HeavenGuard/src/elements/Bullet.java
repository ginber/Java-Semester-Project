package elements;

import game.MainFrame;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Bullet extends ImageIcon {

	private String path;
	private int index = 0; // index of the bullet in the ArrayList in MainFrame
	private Point currentLocation;

	private MainFrame context = null;
	private BaseWeapon weapon = null;
	private BufferedImage image = null;
	
	public final static int RETURN_SUCCESS = 1;
	public final static int RETURN_FAIL = 0;

	public String getPath() {

		return path;

	}

	public void setPath(String path) {

		this.path = path;

	}

	public Bullet(String path, MainFrame context) {

		this.path = path;
		this.context = context;
		
		weapon = context.getBaseWeapon();

		try {

			image = ImageIO.read(new File(path));

		} catch(IOException e) {

			System.out.println("Could not load bullet");

		}

		setImage(image);

		index = context.getBulletsOnScreen().size();
		context.getBulletsOnScreen().add(this);

	}

	public Point getCurrentLocation() {

		return currentLocation;

	}

	public void setCurrentLocation(Point newLocation) {

		if(isOnScreen(newLocation)) {
			
			currentLocation = newLocation;
			return;
			
		}

	}

	// Returns the new position of Bullet after it is fired
	public int calculateMove(double angle, int initialSpeed) {

		System.out.println("angle: " + angle);
		System.out.println("i s: " + initialSpeed);
		
		int changeX = initialSpeed;
		int changeY = (int) (changeX / Math.abs(Math.tan(angle)));
		
		Point newPoint = new Point(getCurrentLocation().x + changeX, getCurrentLocation().y + changeY);
		
		if(isOnScreen(newPoint)) {
			
			setCurrentLocation(newPoint);
			
			return RETURN_SUCCESS;
			
		}
		
		return RETURN_FAIL;

	}

	// Function that gives how many degrees the bullet fired by this weapon should rotate 
	// momentarily
	public double bulletRotationAngle(long initialTime, double aimAngle, double gravity) {

		double flightTime = (2 * weapon.getFireSpeed() * Math.sin(aimAngle)) / gravity;
		return (2 * aimAngle) * ((System.currentTimeMillis() - initialTime) / flightTime);

	}


	public boolean isOnScreen() {

		return ((context.getScreenWidth() > currentLocation.x) && (currentLocation.x > 0)
				&& (context.getScreenHeight() > currentLocation.y) && (currentLocation.y > 0));

	}

	public boolean isOnScreen(Point p) {

		return ((context.getScreenWidth() > p.x) && (p.x > 0)
				&& (context.getScreenHeight() > p.y) && (p.y > 0));

	}

}
