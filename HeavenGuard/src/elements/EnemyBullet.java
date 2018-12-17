package elements;

import java.awt.Point;

import javax.swing.JLabel;

import game.MainFrame;

public class EnemyBullet extends Bullet {
	
	private int startingX, startingY;

	public EnemyBullet(String path, MainFrame context) {
		
		super(path, context);
		
		setCurrentLocation(new Point(startingX, startingY));
		
	}

	@Override
	public boolean collision() {

		boolean xCheck = false, yCheck = false;

		for (JLabel h : context.getHouseContainers()) {

			xCheck = ((h.getLocation().getX() + h.getWidth()) > getCurrentLocation().getX()
					&& (h.getLocation().getX()) < getCurrentLocation().getX());

			yCheck = ((h.getLocation().getY()) <= getCurrentLocation().getY());
			
		}

		return xCheck && yCheck;
	}

	@Override
	public void move(double angle, int initialSpeed) {
		
		double yChange = initialSpeed + getTimeFired() * 9.8;
		
	}
	
}


