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
		
		if(e.getKeyCode() == KeyEvent.VK_N) {
			if (context.isSfx()) {
				context.setSfx(false);
			}
			else if (!context.isSfx()) {
				context.setSfx(true);
			}
			
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			
			MenuBar gui = context.getMenu();
			gui.setSize(20,70);
		
			gui.setName("CONSOLE");
			
			context.requestFocus();
			
			if(!isPaused) {
							
				gui.setVisible(true);
				
			
				context.getTimer().stop();
				isPaused = true;
				
			} else {
				
				gui.dispose();
				gui.setVisible(false);
				
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
