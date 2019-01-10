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
import javax.swing.BorderFactory;
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
	
	private final String NEWGAME_PATH = "HeavenGuard/res/images/misc/play.png";
	private final String LEADERBOARD_PATH = "HeavenGuard/res/images/misc/leaderboard.png";
	private final String SETTINGS_PATH = "HeavenGuard/res/images/misc/settings.png";
	private final String EXIT_PATH = "HeavenGuard/res/images/misc/exit.png";
	private final String NEWGAME_FOCUSED = "HeavenGuard/res/images/misc/playF.png";
	private final String LEADERBOARD_FOCUSED = "HeavenGuard/res/images/misc/leaderboardF.png";
	private final String SETTINGS_FOCUSED = "HeavenGuard/res/images/misc/settingsF.png";
	private final String EXIT_FOCUSED = "HeavenGuard/res/images/misc/exitF.png";
	
	private final String MenuBG_PATH = "HeavenGuard/res/images/misc/menu.png";

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

		NewGame = new JLabel(newGameIcon);
		
		NewGame.addMouseListener(new MouseListener() {
			
			MainFrame game;
			BufferedImage newImage;
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				try {

					newImage = ImageIO.read(new File(NEWGAME_PATH));

				} catch(IOException e1) {

					System.out.println("Could not load background");

				}

				Image scaledImage = getScaledImage(newImage, Context.getScreenWidth() / 6, Context.getScreenHeight() / 8);

				ImageIcon newGameIcon = new ImageIcon(scaledImage);

				NewGame.setIcon(newGameIcon);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				try {

					newImage = ImageIO.read(new File(NEWGAME_FOCUSED));

				} catch(IOException el) {

					System.out.println("Could not load background");

				}

				Image scaledImage = getScaledImage(newImage, Context.getScreenWidth() / 6, Context.getScreenHeight() / 8);

				ImageIcon newGameIcon = new ImageIcon(scaledImage);

				NewGame.setIcon(newGameIcon);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {

				game = new MainFrame(title, true);

				
			}
		});

		BufferedImage settingsImage = null;

		try {

			settingsImage = ImageIO.read(new File(SETTINGS_PATH));

		} catch(IOException e) {

			System.out.println("Could not load background");

		}

		scaledImage = getScaledImage(settingsImage, Context.getScreenWidth() / 6, Context.getScreenHeight() / 8);

		ImageIcon settingsIcon = new ImageIcon(scaledImage);
		
		Settings = new JLabel(settingsIcon);

		BufferedImage leaderboardImage = null;

		try {

			leaderboardImage = ImageIO.read(new File(LEADERBOARD_PATH));

		} catch(IOException e) {

			System.out.println("Could not load background");

		}

		scaledImage = getScaledImage(leaderboardImage, Context.getScreenWidth() / 6, Context.getScreenHeight() / 8);

		ImageIcon leaderboardIcon = new ImageIcon(scaledImage);
		
		Leaderboard =  new JLabel(leaderboardIcon);
		
		BufferedImage exitImage = null;
		
		try {

			exitImage = ImageIO.read(new File(EXIT_PATH));

		} catch(IOException el) {

			System.out.println("Could not load background");

		}

		scaledImage = getScaledImage(exitImage, Context.getScreenWidth() / 6, Context.getScreenHeight() / 8);

		ImageIcon exitIcon = new ImageIcon(scaledImage);

		Exit = new JLabel(exitIcon);
		
		Exit.addMouseListener(new MouseListener() {
			
			BufferedImage newImage = null;
			
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
				
				try {

					newImage = ImageIO.read(new File(EXIT_PATH));

				} catch(IOException el) {

					System.out.println("Could not load background");

				}

				Image scaledImage = getScaledImage(newImage, Context.getScreenWidth() / 6, Context.getScreenHeight() / 8);

				ImageIcon newGameIcon = new ImageIcon(scaledImage);

				Exit.setIcon(newGameIcon);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				try {

					newImage = ImageIO.read(new File(EXIT_FOCUSED));

				} catch(IOException el) {

					System.out.println("Could not load background");

				}

				Image newScaledImage = getScaledImage(newImage, Context.getScreenWidth() / 6, Context.getScreenHeight() / 8);

				ImageIcon exitIcon = new ImageIcon(newScaledImage);

				Exit.setIcon(exitIcon);
				
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