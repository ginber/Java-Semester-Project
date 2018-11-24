package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

// Custom MouseListener to handle mouse events

public class HGMouseListener implements MouseListener {
	
	int x, y;
	
	MainFrame context;
	
	public HGMouseListener(MainFrame context) {
		
		this.context = context;
		
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		x = e.getX();
		y = e.getY();
		
		context.getBaseWeapon().fire(x, y);

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

