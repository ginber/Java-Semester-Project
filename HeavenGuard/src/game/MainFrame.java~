package game;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame {
	
	private final String BG_PATH = "res/images/misc/background.png";
	
	public MainFrame(String title) {
		
		super(title);
		
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
		
		JLabel background = new JLabel(new ImageIcon(BG_PATH));
		
		setLayout(new BorderLayout());
		
		add(background, BorderLayout.CENTER);
		
		addKeyListener(new HGKeyListener());
		
		setVisible(true);
		
	}

}
