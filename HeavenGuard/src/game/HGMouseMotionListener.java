package game;

import elements.BaseWeapon;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class HGMouseMotionListener implements MouseMotionListener {

	MainFrame context;

	BaseWeapon bw;

	public HGMouseMotionListener(MainFrame context) {

		this.context = context;
		bw = context.getBaseWeapon();

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		context.rotateWeapon(bw.aimAngle(e.getX(), e.getY(), context.getScreenWidth() / 2,
				context.getScreenHeight() - context.getBaseHeight()));

	}

}
