package game;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;



public class MenuBar extends JFrame{
	JMenuBar menubar;
	JMenu file, asd,bo;
	JMenuItem exit, leaderboards, mutesfx, mutemusic, howtoplay, upgrade1, upgrade2, upgrade3, ayylmao, asdasd;
	MainFrame context = null;
	
	
	public MenuBar(MainFrame context) {
		
		this.context = context;
		
		context.getLeaderBoards();
		setAutoRequestFocus(false);
		
		setLayout(new FlowLayout());
		
		setPreferredSize(new Dimension(context.getScreenWidth() / 4, context.getScreenHeight() / 5));
		
		//setUndecorated(true);
		
		setResizable(false);
		menubar = new JMenuBar();
		
		addKeyListener(new HGKeyListener(context));
	
		
		setJMenuBar(menubar);
		
		file = new JMenu("Options");
		menubar.add(file);
		asd = new JMenu("Upgrades");
		menubar.add(asd);
		bo = new JMenu("Weapons");
		menubar.add(bo);
		
		upgrade1 = new JMenuItem("Upgrade Weapon");
		upgrade2 = new JMenuItem("Upgrade Houses");
		upgrade3 = new JMenuItem("Upgrade Base");
		ayylmao = new JMenuItem("Show Status");
		
		asd.add(ayylmao);
		asd.add(upgrade1);
		asd.add(upgrade2);
		asd.add(upgrade3);
		
		
		
		leaderboards = new JMenuItem("High Score");
		file.add(leaderboards);
		
		mutemusic = new JMenuItem("Toggle Music");
		file.add(mutemusic);
		
		mutesfx = new JMenuItem("Toggle SFX");
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
		
		event6 k = new event6();
		upgrade1.addActionListener(k);
		
		event7 l = new event7();
		upgrade2.addActionListener(l);
		
		event8 m = new event8();
		upgrade3.addActionListener(m);
		
		event10 z = new event10();
		ayylmao.addActionListener(z);
		
		
		asdasd = new JMenuItem(context.getCurrent());
		bo.add(asdasd);
		event11 b = new event11();
		asdasd.addActionListener(b);
		
		
	}
	
	public void editmenu() {
		if (!context.getCurrent().equals("Buy Machine Gun")) {
			menubar.remove(bo);
			menubar.revalidate();
		}
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
			
			JOptionPane.showMessageDialog(null, "Highest Score by " + context.recordname + " - " + context.berkaysinirlenme);
		}
		
}
	public class event3 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent g) {
			
			if (context.isSfx()) {
				context.setSfx(false);
			}
			else if (!context.isSfx()) {
				context.setSfx(true);
			}
		
		}
		
}
	public class event4 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent h) {
			
			context.setMusicPlaying(!context.isMusicPlaying());
			context.playBackgroundMusic();
		}
		
}
	public class event5 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent J) {
			
			JOptionPane.showMessageDialog(null, "Aim with mouse, shoot with left click");
		}
		
}
	public class event6 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent k) {
			int upgradecost = (int) ((context.weaponlevel*300)/(context.weaponlevel + 3));
			int input = JOptionPane.showConfirmDialog(null, "Upgrade weapon for " + upgradecost + " kebaboins?" );
			if (input == 0 && context.kebaboins >= upgradecost ) {
				context.kebaboins -= upgradecost;
				context.weaponlevel++;
			}
			else if (input == 0 && context.kebaboins < upgradecost ) {
				JOptionPane.showMessageDialog(null, "Not enough kebabs milord");
			}
		
			
		}
		
}
	public class event7 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent l) {
			
			int upgradecost = (int) ((context.houselevel*200)/(context.houselevel + 3));
			int input = JOptionPane.showConfirmDialog(null, "Upgrade houses for " + upgradecost + " kebaboins?" );
			if (input == 0 && context.kebaboins >= upgradecost ) {
				context.kebaboins -= upgradecost;
				context.houselevel++;
				for ( int i = 0 ; i < 4 ; i++) {
					if(context.getHouseHP()[i] > 0 ) {
					context.getHouseHP()[i] = 100;
					context.getHouseHealthBars()[i].setValue(context.getHouseHP()[i]);
					
					BufferedImage houseImage = null;

					try {

						houseImage = ImageIO.read(new File(context.HOUSE_PATH));

					} catch(IOException e) {

						System.out.println("Could not load houses");

					}

					Image scaledImage = context.getScaledImage(houseImage, context.getScreenWidth() / 6, context.getScreenHeight() / 8);

					ImageIcon house = new ImageIcon(scaledImage);
					
					context.getHouseContainers()[i].setIcon(house);;
					
					}
				}
			}
			else if (input == 0 && context.kebaboins < upgradecost ) {
				JOptionPane.showMessageDialog(null, "Not enough kebabs milord");
			}
		}
		
}
	public class event8 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent m) {
			
			int upgradecost = (int) ((context.baselevel*500)/(context.baselevel + 2));
			int input = JOptionPane.showConfirmDialog(null, "Upgrade base for " + upgradecost + " kebaboins?" );
			if (input == 0 && context.kebaboins >= upgradecost ) {
				context.kebaboins -= upgradecost;
				context.baselevel++;
			}
			else if (input == 0 && context.kebaboins < upgradecost ) {
				JOptionPane.showMessageDialog(null, "Not enough kebabs milord");
			}
		}
		
}
	public class event10 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent z) {
			
			JOptionPane.showMessageDialog(null, "STATUS\nWeapon Level: " + context.weaponlevel 
					+ "\nHouse Level: " + context.houselevel + "\nBase Level: " + context.baselevel);
		}
		
}
	public class event11 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent b) {
			
			int upgradecost = 3000;
			int input = JOptionPane.showConfirmDialog(null, "Buy machine gun for " + upgradecost + " kebaboins?" );
			if (input == 0 && context.kebaboins >= upgradecost ) {
				context.kebaboins -= upgradecost;
				context.setCurrent("Machine Gun Sold");
			}
			else if (input == 0 && context.kebaboins < upgradecost ) {
				JOptionPane.showMessageDialog(null, "Not enough kebabs milord");
			}
		}
		
}

	
}