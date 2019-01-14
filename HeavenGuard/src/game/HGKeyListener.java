package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Custom KeyListener to handle key presses

public class HGKeyListener implements KeyListener {
	
	MainFrame context = null;
	
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
		
		if(e.getKeyCode() == KeyEvent.VK_L) {
			context.submitScore(52543);
			context.kebaboins = 200000;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			
			MenuBar gui = context.getMenu();
			gui.setSize(20,70);
		
			gui.setName("CONSOLE");
			
			context.requestFocus();
			
			if(!context.isPaused) {
							
				gui.setVisible(true);
				
			
				context.getTimer().stop();
				context.isPaused = true;
				
				Graphics g = context.getGraphics();
				context.requestFocus();
				g.setFont(new Font("Cracked Code", Font.PLAIN, context.getScreenWidth() / 12));
				g.setColor(Color.RED);
				
				g.drawString("PAUSED", context.getScreenWidth() / 2 - context.getScreenWidth() / 5, context.getScreenHeight() / 2);
				
			} else {
				
				gui.dispose();
				gui.setVisible(false);
				
				context.getTimer().start();
				context.isPaused = false;
				
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
