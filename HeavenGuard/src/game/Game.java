package game;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game {
	
	private static final String NAME = "HeavenGuard";
	private static final int GRAVITY = 10;
	
	public static void main(String[] args) {
		
		//new Game();
		
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {

				new MainMenu(new MainFrame(NAME, false, ""), NAME);

				
			}
			
		});
		
	}
	
		/*public Game() {
			 setSize(getToolkit().getScreenSize().width,getToolkit().getScreenSize().width);
			 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 setTitle(" S A ");
			 setResizable(false);
			 MainMenu initialPage = new MainMenu();
			 add(initialPage);
			 this.setVisible(true);
			
			 
		}*/
	
	public static int getGravity() { return GRAVITY; }

}
