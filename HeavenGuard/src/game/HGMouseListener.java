package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import elements.BaseWeapon;

// Custom MouseListener to handle mouse events

public class HGMouseListener implements MouseListener {
	
	int x, y;
	MainFrame context;
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public HGMouseListener(MainFrame context) {

	    this.context = context;

    }

	@Override
	public void mouseClicked(MouseEvent e) {
		
		x = e.getX();
		y = e.getY();

<<<<<<< HEAD
		//context.fireBullet(x, y);
		
=======

		context.fireBullet(x, y);
		System.out.println(x);
		System.out.println(y);

>>>>>>> bbc859c911a1fb31267069ef3cfa2013623d7656
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		

	}

}

