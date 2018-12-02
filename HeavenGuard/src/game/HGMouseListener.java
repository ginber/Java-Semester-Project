package game;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.TimerTask;
import java.util.Timer;

import elements.BaseWeapon;

// Custom MouseListener to handle mouse events

public class HGMouseListener implements MouseListener {
	
	int x, y;
	MainFrame context;
	BaseWeapon weapon;
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public HGMouseListener(MainFrame context) {

	    this.context = context;
	    weapon = context.getBaseWeapon();
	    	
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		weapon.setFiring(true);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		weapon.setFiring(false);

	}

}

