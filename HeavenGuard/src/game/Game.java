package game;

import javax.swing.SwingUtilities;

public class Game {
	
	private static final String NAME = "HeavenGuard";
	private static final int GRAVITY = 10;
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				new MainFrame(NAME);
				
			}
			
		});
		
	}
	
	public static int getGravity() { return GRAVITY; }

}
