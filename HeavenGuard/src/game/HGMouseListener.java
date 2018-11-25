package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import elements.BaseWeapon;

// Custom MouseListener to handle mouse events

public class HGMouseListener implements MouseListener {
	
	int x, y;
	
	MainFrame context;
	
	BaseWeapon bw;
	
	public HGMouseListener(MainFrame context) {
		
		this.context = context;
		bw = context.getBaseWeapon();
		
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
		//System.out.println( x +"\n"+ y);
		//context.getBaseWeapon().fire(x, y);
		
		context.rotateWeapon(bw.aimAngle(x, y, context.getScreenWidth() / 2, context.getBaseHeight()));

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

