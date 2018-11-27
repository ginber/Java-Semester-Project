package game;

import elements.BaseWeapon;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class HGMouseMotionListener implements MouseMotionListener {
	
	int x, y;

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		x = e.getX();
		y = e.getY();

	}
	
	public int getX() { return x; }
	public int getY() { return y; }

}
