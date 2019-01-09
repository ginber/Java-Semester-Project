package game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JFrame {

	private JLabel NewGame  = null; 
	private JLabel Settings = null;
	private JLabel Leaderboard = null;
	private JLabel Exit = null;
	private MainFrame Context = null;
	
	private final String NEWGAME_PATH = "HeavenGuard/res/images/misc/bring 'em on.jpg";
	
	private final String MenuBG_PATH = "HeavenGuard/res/images/misc/background.png";

	JLabel backgroundContainer = null;

	public void createBackground() {

		BufferedImage bgImage = null;

		try {

			bgImage = ImageIO.read(new File(MenuBG_PATH));

		} catch(IOException e) {

			System.out.println("Could not load background");

		}

		Image scaledImage = getScaledImage(bgImage, Context.getScreenWidth(), Context.getScreenHeight());

		ImageIcon background = new ImageIcon(scaledImage);

		backgroundContainer = new JLabel(background);

	}
	
	public MainMenu(MainFrame context, String title) {
		
		Context = context;
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		
		BufferedImage newGameImage = null;

		try {

			newGameImage = ImageIO.read(new File(NEWGAME_PATH));

		} catch(IOException e) {

			System.out.println("Could not load background");

		}

		Image scaledImage = getScaledImage(newGameImage, Context.getScreenWidth() / 6, Context.getScreenHeight() / 8);

		ImageIcon newGameIcon = new ImageIcon(scaledImage);

		JLabel NewGame = new JLabel(newGameIcon);
		
		NewGame.addMouseListener(new MouseListener() {
			
			MainFrame newGame;
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				newGame = new MainFrame(title);
				
			}
		});

		Settings = new JLabel("Settings");
		Settings.setForeground(Color.GREEN);
		Settings.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		Settings.setOpaque(true);
		Settings.setBackground(Color.RED);

		Leaderboard =  new JLabel("Better than you-list");
		Leaderboard.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		Leaderboard.setForeground(Color.MAGENTA);
		Leaderboard.setOpaque(true);
		Leaderboard.setBackground(Color.RED);

		Exit = new JLabel("Abandon your people");
		Exit.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		Exit.setForeground(Color.RED);
		Exit.setOpaque(true);
		Exit.setBackground(Color.RED);
		
		Exit.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
				
			}
		});


		createBackground();

		backgroundContainer.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.weighty = 0.25;
		constraints.weightx = 1;
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		
		backgroundContainer.add(NewGame, constraints);
		
		constraints.gridy = 3;
		backgroundContainer.add(Settings, constraints);
		
		constraints.gridy = 4;
		backgroundContainer.add(Leaderboard, constraints);
		
		constraints.gridy = 5;
		backgroundContainer.add(Exit, constraints);
		
		getContentPane().add(backgroundContainer);
		
		System.out.println(Exit.getX());
		System.out.println(Exit.getY());

		setVisible(true);



	}

	public static Image getScaledImage(Image image, int width, int height) {


		BufferedImage resizedImage = new BufferedImage(width, height, 
				BufferedImage.TYPE_INT_ARGB);

		Graphics2D g = resizedImage.createGraphics();

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		g.drawImage(image, 0, 0, width, height, null);

		g.dispose();

		return resizedImage;

	}

}