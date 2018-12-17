package elements;

import game.MainFrame;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.swing.ImageIcon;

public class Bullet extends ImageIcon {

	private String path;
	protected int index = 0; // index of the bullet in the ArrayList in MainFrame
	protected Point currentLocation;
	private long timeFired = 0;
	private double firedAngle;
	private boolean isFired = false, isHit = false;

	private final String EXPLOSION_PATH = "HeavenGuard/res/music/explosion.wav";
	private File musicPath = null;
	private AudioInputStream audioInput = null;

	protected MainFrame context = null;
	private BaseWeapon weapon = null;
	private BufferedImage image = null;

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

		currentLocation = newLocation;

	}

	// Returns the new position of Bullet after it is fired
	public void move(double angle, int initialSpeed) {

		angle += 90;
		long time = (System.currentTimeMillis() - getTimeFired()) / 100;

		double changeX = -(initialSpeed * Math.cos(Math.toRadians(angle)));

		double changeY = (initialSpeed * Math.sin(Math.toRadians(angle))) - time * 9.8;

		currentLocation = new Point((int) (getCurrentLocation().x + changeX), 
				(int) (getCurrentLocation().y - changeY));
		//	return new Point((int) (getCurrentLocation().x + changeX), (int) (getCurrentLocation().y - changeY));

		/*
		if((context.getEnemyShip().getxPosition()+context.getEnemyImage().getWidth()/2) > currentLocation.getX() && (context.getEnemyShip().getxPosition()-context.getEnemyImage().getWidth()/2) < currentLocation.getX() && 
				(context.getEnemyShip().getyPosition()-context.getEnemyImage().getHeight()/2) < currentLocation.getY() &&  (context.getEnemyShip().getyPosition()+context.getEnemyImage().getHeight()/2) > currentLocation.getY() ) {

			isHit = true;

		}
		*/

		if(collision()) {
			
			isHit = true;
			System.out.println("Enemy eliminated");
			context.playCollisionSFX();
			
		}

	}

	// Function that gives how many degrees the bullet fired by this weapon should rotate 
	// momentarily
	public double bulletRotationAngle(long initialTime, double aimAngle, double gravity) {

		double flightTime = (2 * weapon.getFireSpeed() * Math.sin(aimAngle)) / gravity;
		return (2 * aimAngle) * ((System.currentTimeMillis() - initialTime) / flightTime);

	}

	public boolean isOnScreen() {

		return ((context.getScreenWidth() > currentLocation.x) && (currentLocation.x > 0)
				&& (context.getScreenHeight() > currentLocation.y)  );

	}

	public boolean isOnScreen(Point p) {

		return ((context.getScreenWidth() > p.x) && (p.x > 0)
				&& (context.getScreenHeight() > p.y) && (p.y > 0));

	}

	protected long getTimeFired() {
		return timeFired;
	}

	protected void setTimeFired(long timeFired) {
		this.timeFired = timeFired;
	}

	public double getFiredAngle() {
		return firedAngle;
	}

	public void setFiredAngle(double firedAngle) {
		this.firedAngle = firedAngle;
	}

	public boolean isHit() {
		return isHit;
	}

	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}


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

	public boolean isFired() {
		return isFired;
	}

	public void setFired(boolean isFired) {
		this.isFired = isFired;
	}
	
	public boolean collision() {
		
		boolean xCheck = false, yCheck = false;
		
		for(EnemyShip e : context.getShipsOnScreen()) {
			
			xCheck = (currentLocation.x < e.getLocation().getX() + e.getWidth())
			&& (currentLocation.x > e.getxPosition());
			
			yCheck = (currentLocation.y < e.getLocation().getY() + e.getHeight())
					&& (currentLocation.y > e.getyPosition());
			
			if(xCheck && yCheck) {
				
				e.die();
				return true;
				
			}
			
		} 
		
		return false;
		
	}

}
