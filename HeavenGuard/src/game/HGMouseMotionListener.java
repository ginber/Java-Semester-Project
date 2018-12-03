package game;

import elements.BaseWeapon;
import elements.CannonWeapon;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class HGMouseMotionListener implements MouseMotionListener {
	
	int x, y;
	private MainFrame context = null;
	
	public HGMouseMotionListener(MainFrame context) {
		
		this.context = context;
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		
		if(!(context.getBaseWeapon().getType().equals(CannonWeapon.TYPE))) {
			
			x = e.getX();
			y = e.getY();
			
		}
		

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		x = e.getX();
		y = e.getY();

	}
	
	public int getX() { return x; }
	public int getY() { return y; }

}
