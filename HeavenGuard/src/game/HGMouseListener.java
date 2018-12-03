package game;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Timer;

import elements.BaseWeapon;
import elements.CannonWeapon;

// Custom MouseListener to handle mouse events

public class HGMouseListener implements MouseListener {

	int x, y;
	MainFrame context;
	BaseWeapon weapon;
	CannonWeapon cannonWeapon;
	Timer timer;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public HGMouseListener(MainFrame context) {

		this.context = context;
		weapon = context.getBaseWeapon();
		
		timer = new Timer(0, null);

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
			int barFill = cannonWeapon.getMaxPower();
			
			timer.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					context.cannonBar.setValue(barFill);
					
				}
			});
			
			timer.setDelay(100);
			
			timer.start();

		} else {

			weapon.setFiring(true);

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if(weapon.getType().equals(CannonWeapon.TYPE)) {

			weapon.setFiring(true);
			timer.stop();
			
			cannonWeapon.setMaxPower(0);
			context.cannonBar.setValue(0);
			
		} else {

			weapon.setFiring(false);

		}

	}

}

