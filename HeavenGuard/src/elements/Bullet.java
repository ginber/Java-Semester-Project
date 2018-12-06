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
	private int firedSpeed;
	private Point currentLocation;
	
	
	private boolean moving = false;

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
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
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

	}

	public Point getCurrentLocation() {

		return currentLocation;

	}

	public void setCurrentLocation(Point newLocation) {

		if(isOnScreen(newLocation)) {
			
			System.out.println("deðiþtirmeden önce: " + currentLocation);
			
			currentLocation = newLocation;
			
			System.out.println("deðiþtirdikten sonra: " + currentLocation);
			
		}
		
		

	}

	// Returns the new position of Bullet after it is fired
	public Point calculateMove(double angle, int initialSpeed) {
		
		angle += 90;
		double flightTime = (2 * initialSpeed * Math.sin(angle)) / (9.8);
		
		//System.out.println("angle: " + angle);
		//System.out.println("i s: " + initialSpeed);
		
		double changeX = -(initialSpeed * Math.cos(Math.toRadians(angle)));
	
		double changeY = (initialSpeed * Math.sin(Math.toRadians(angle)));
		
		//System.out.println("ch x " + changeX);
		//System.out.println("ch y " + changeY);
		
		Point newPoint = new Point((int) (getCurrentLocation().x + changeX), (int) (getCurrentLocation().y - changeY));
		
		//System.out.println("ananýn pointi " + newPoint);
					
			
			return	newPoint;
	}

	// Function that gives how many degrees the bullet fired by this weapon should rotate 
	// momentarily
	public double bulletRotationAngle(long initialTime, double aimAngle, double gravity) {

		double flightTime = (2 * weapon.getFireSpeed() * Math.sin(aimAngle)) / gravity;
		return (2 * aimAngle) * ((System.currentTimeMillis() - initialTime) / flightTime);

	}
	
	public void makeMove(Point nextP) {
		context.getBulletsOnScreen().get(index).setCurrentLocation(nextP);
		//context.getBulletsOnScreen().get(index).currentLocation.x = nextP.x;
		//context.getBulletsOnScreen().get(index).currentLocation.y = nextP.y;
		//System.out.println(context.getBulletsOnScreen().get(index - 1).getCurrentLocation());
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
