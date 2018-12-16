package game;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;



public class MenuBar extends JFrame{
	JMenuBar menubar;
	JMenu file;
	JMenuItem exit, leaderboards, mutesfx, mutemusic, howtoplay;
	MainFrame context = null;
	
	public MenuBar(MainFrame context) {
		
		this.context = context;
		
		setAutoRequestFocus(false);
		
		setLayout(new FlowLayout());
		menubar = new JMenuBar();
		
		addKeyListener(new HGKeyListener(context));
	
		
		setJMenuBar(menubar);
		
		file = new JMenu("Options");
		menubar.add(file);
		
		
		
		leaderboards = new JMenuItem("LeaderBoards");
		file.add(leaderboards);
		
		mutemusic = new JMenuItem("Mute Music");
		file.add(mutemusic);
		
		mutesfx = new JMenuItem("Mute SFX");
		file.add(mutesfx);
		
		
		
		howtoplay = new JMenuItem("How To Play");
		file.add(howtoplay);
		
		exit = new JMenuItem("Exit");
		file.add(exit);
		
		event e = new event();
		exit.addActionListener(e);
		
		event2 f = new event2();
		leaderboards.addActionListener(f);
		
		event3 g = new event3();
		mutesfx.addActionListener(g);
		
		event4 h = new event4();
		mutemusic.addActionListener(h);
		
		event5 J = new event5();
		howtoplay.addActionListener(J);
		
	}
	


	public class event implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
		
}
	public class event2 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent f) {
			
			JOptionPane.showMessageDialog(null, "dogukan birinci ne sandin bebek");
		}
		
}
	public class event3 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent g) {
			
			//closesfx
		
		}
		
}
	public class event4 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent h) {
			
			//closemusic
		}
		
}
	public class event5 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent J) {
			
			JOptionPane.showMessageDialog(null, "ne demek nasi oynuyoz dusmanlari vurcan iste mk");
		}
		
}

	
}