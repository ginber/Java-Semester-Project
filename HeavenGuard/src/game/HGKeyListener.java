package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Custom KeyListener to handle key presses

public class HGKeyListener implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_Q) {
			
			System.exit(0);
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
		
	}
	
	

}
