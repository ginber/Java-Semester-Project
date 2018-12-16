package elements;

import javax.swing.JLabel;

import game.MainFrame;

public class EnemyBullet extends Bullet {

	public EnemyBullet(String path, MainFrame context) {
		super(path, context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean collision() {

		boolean xCheck = false, yCheck = false;

		for (JLabel h : context.getHouseContainers()) {

			xCheck = ((h.getLocation().getX() + h.getWidth()) < getCurrentLocation().getX()
					&& (h.getLocation().getX()) > getCurrentLocation().getX());

			yCheck = ((h.getLocation().getY()) <= getCurrentLocation().getY());
		}

		return xCheck && yCheck;
	}

	public void move() {
		super.move(180, 10);
	}
}


