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
	private Point currentLocation;

	//private MainFrame context = null;

	private MainFrame context = null;
	private BufferedImage image = null;

	public String getPath() {

		return path;

	}

	public void setPath(String path) {

		this.path = path;

	}

	public Bullet(String path, MainFrame context) {

		this.path = path;
		this.context = context;

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

	public Point calculateMove(double angle, int initialSpeed) {

		int changeX = initialSpeed;
		int changeY = (int) (changeX * Math.tan(angle));

		Point newPoint = new Point(changeX, changeY);

		if(isOnScreen(newPoint)) {

			currentLocation = new Point(currentLocation.x + changeX, currentLocation.y - changeY);

			return currentLocation;

		}

		return new Point(0, 0);

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
