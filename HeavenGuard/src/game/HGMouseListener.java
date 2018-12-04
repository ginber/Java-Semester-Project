package game;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import elements.BaseWeapon;
import elements.CannonWeapon;

// Custom MouseListener to handle mouse events

public class HGMouseListener implements MouseListener {

	int x, y, barFill = 0;
	MainFrame context;
	BaseWeapon weapon;
	CannonWeapon cannonWeapon;
	Timer timer;
	
	class HGCannonTimerTask extends TimerTask {
		
		int maxPower;
		
		public HGCannonTimerTask(CannonWeapon cw) {
			
			if(cw != null)
				maxPower = cw.getMaxPower();		
							
		}

		@Override
		public void run() {
			
			barFill++;		
			context.cannonBar.setValue(barFill);	
			cannonWeapon.setFireSpeed(barFill / 2000000);
			
		}
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public HGMouseListener(MainFrame context) {

		this.context = context;
		weapon = context.getBaseWeapon();
		timer = new Timer();

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

		if(weapon.getType().equals(CannonWeapon.TYPE)) {

			cannonWeapon = (CannonWeapon) weapon;
			
			timer = new Timer();
				
			timer.scheduleAtFixedRate(new HGCannonTimerTask(cannonWeapon), 0, 20);

					
		} else {

			weapon.setFiring(true);

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if(weapon.getType().equals(CannonWeapon.TYPE)) {

			cannonWeapon.setFiring(true);
			timer.cancel();
			
			barFill = 0;
			cannonWeapon.setFireSpeed(0);
			context.cannonBar.setValue(barFill);
			
		} else {

			weapon.setFiring(false);

		}

	}

}

