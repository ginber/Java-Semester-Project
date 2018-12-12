package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Custom KeyListener to handle key presses

public class HGKeyListener implements KeyListener {
	
	MainFrame context = null;
	private boolean isPaused = false;
	
	public  HGKeyListener(MainFrame context) {
		
		this.context = context;
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_Q) {
			
			System.exit(0);
			
		} 
		
		if(e.getKeyCode() == KeyEvent.VK_M) {
			
			context.setMusicPlaying(!context.isMusicPlaying());
			context.playBackgroundMusic();
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			
			if(!isPaused) {
				
				context.getTimer().stop();
				isPaused = true;
				
			} else {
				
				context.getTimer().start();
				isPaused = false;
				
			}
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
		
	}
	
	

}
