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

	int x, y;
	MainFrame context;
	BaseWeapon weapon;
	CannonWeapon cannonWeapon;
	Timer timer;
	
	class HGCannonTimerTask extends TimerTask {
		
		int barFill = 0, maxPower;
		
		public HGCannonTimerTask(CannonWeapon cw) {
			
			if(cw != null)
				maxPower = cw.getMaxPower();
			
		}

		@Override
		public void run() {
			
			barFill++;
			// maxPower a göre oranlayýp artýrýrýz sonra þimdilik birer birer artsýn
			
			context.cannonBar.setValue(barFill);
			
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
			
			timer.schedule(new HGCannonTimerTask(cannonWeapon), 100);

		} else {

			weapon.setFiring(true);

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if(weapon.getType().equals(CannonWeapon.TYPE)) {

			weapon.setFiring(true);
			timer.cancel();
		
			context.cannonBar.setValue(0);
			
		} else {

			weapon.setFiring(false);

		}

	}

}

