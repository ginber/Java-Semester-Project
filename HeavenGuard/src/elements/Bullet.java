package elements;

import game.MainFrame;

import java.awt.Point;

import javax.swing.ImageIcon;

public class Bullet extends ImageIcon {

	private boolean onScreen = false;
	private String path;
	private Point currentLocation;
	private MainFrame context = null;
	private double aimAngle;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Bullet(Point location, boolean onScreen, MainFrame context, double aimAngle) {

		currentLocation = location;
		this.onScreen = onScreen;
		this.context = context;
		this.aimAngle = aimAngle;
		
		if(onScreen) {
			
			context.getBulletsOnScreen().add(this);
			
		}
		

	}

	public Point getCurrentLocation() {
		return currentLocation;
	}

	public Bullet(Point location) {

		currentLocation = location;

	}

	public boolean isOnScreen() {

		return ((context.getScreenWidth() > currentLocation.x) && (currentLocation.x > 0)
				&& (context.getScreenHeight() > currentLocation.y) && (currentLocation.y > 0));

	}

}
