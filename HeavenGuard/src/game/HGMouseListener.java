package game;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

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


			if (barFill < context.cannonBar.getMaximum()) {
				barFill+=2*((context.weaponlevel + 6)/7);		
				//System.out.println("bf:" + barFill);
				context.cannonBar.setValue(barFill);	
			}


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
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("HeavenGuard/res/images/misc/cross.png");
		Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "img");
		context.setCursor(c);
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
		
		if(SwingUtilities.isLeftMouseButton(e) && !SwingUtilities.isRightMouseButton(e) && !context.isPaused) {
			
			if(weapon.getType().equals(CannonWeapon.TYPE)) {

				cannonWeapon = (CannonWeapon) weapon;

				timer = new Timer();

				timer.scheduleAtFixedRate(new HGCannonTimerTask(cannonWeapon), 0, 20);


			} else {
				
				weapon.setFiring(true);

			}
			
		} else {
			
			
			
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {


		if(SwingUtilities.isLeftMouseButton(e) && !SwingUtilities.isRightMouseButton(e) && !context.isPaused) {

			if(weapon.getType().equals(CannonWeapon.TYPE)) {

				context.getBaseWeapon().setFiring(true);
				timer.cancel();

				context.getBaseWeapon().setFireSpeed(barFill);
				barFill = 0;		
				context.cannonBar.setValue(barFill);
				//context.playCannonFire();


			} else {

				weapon.setFiring(false);

			}

		} else {
			
			Thread warningThread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					
					try {
						
						context.warning((Graphics2D)context.getGraphics());
						Thread.sleep(1000);
						
					} catch(InterruptedException e) {
						
						
						
					}
					
				}
			});
			
			
		}


	}

}

