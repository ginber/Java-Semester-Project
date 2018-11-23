package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.OverlayLayout;

import elements.*;


public class MainFrame extends JFrame {

	private final String BG_PATH = "HeavenGuard/res/images/misc/background.png";
	private final String BASE_PATH = "HeavenGuard/res/images/misc/base.png";
	private final String HOUSE_PATH = "HeavenGuard/res/images/misc/house.png";

	private BaseWeapon baseWeapon;

	JLabel backgroundContainer = null;
	JLabel baseContainer = null;
	JLabel[] houseContainers = new JLabel[4];

	private final int screenWidth = getToolkit().getScreenSize().width;
	private final int screenHeight = getToolkit().getScreenSize().height;

	public void createBackground() {

		BufferedImage bgImage = null;

		try {

			bgImage = ImageIO.read(new File(BG_PATH));

		} catch(IOException e) {

			System.out.println("Could not load background");

		}

		Image scaledImage = getScaledImage(bgImage, screenWidth, screenHeight);

		ImageIcon background = new ImageIcon(scaledImage);

		backgroundContainer = new JLabel(background);

	}

	public void createBase() {

		BufferedImage baseImage = null;

		try {

			baseImage = ImageIO.read(new File(BASE_PATH));

		} catch(IOException e) {

			System.out.println("Could not load base");

		}

		Image scaledImage = getScaledImage(baseImage, screenWidth / 5, screenHeight / 5);

		ImageIcon base = new ImageIcon(scaledImage);

		baseContainer = new JLabel(base);

	}

	public void createHouse() {

		BufferedImage houseImage = null;

		try {

			houseImage = ImageIO.read(new File(HOUSE_PATH));

		} catch(IOException e) {

			System.out.println("Could not load houses");

		}

		Image scaledImage = getScaledImage(houseImage, screenWidth / 6, screenHeight / 6);

		ImageIcon house = new ImageIcon(scaledImage);

		for(int i = 0; i < houseContainers.length; i++) {

			houseContainers[i] = new JLabel(house);

		}

	}

	public MainFrame(String title) {

		super(title);

		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);

		createBackground();
		createBase();
		createHouse();

		backgroundContainer.setLayout(new BorderLayout());

		//baseContainer.setBorder(BorderFactory.createLineBorder(Color.RED));

		JPanel leftContainer = new JPanel(new BorderLayout());
		JPanel rightContainer = new JPanel(new BorderLayout());
		
		leftContainer.setOpaque(false);
		rightContainer.setOpaque(false);
		
		//leftContainer.setBorder(BorderFactory.createLineBorder(Color.GREEN,10));
		
		baseContainer.setLayout(new BorderLayout());	
		
		for(int i = 0; i < houseContainers.length; i++) {
			
			if(i < 2) {
				
				leftContainer.add(houseContainers[i], BorderLayout.PAGE_END);
				
			} else {
				
				rightContainer.add(houseContainers[i], BorderLayout.PAGE_END);
				
			}
			
		}
		
		baseContainer.add(leftContainer, BorderLayout.WEST);
		baseContainer.add(rightContainer, BorderLayout.EAST);

		backgroundContainer.add(baseContainer, BorderLayout.SOUTH);

		add(backgroundContainer);

		addMouseListener(new HGMouseListener(this));
		addKeyListener(new HGKeyListener());

		setVisible(true);

	}

	public static Image getScaledImage(Image image, int width, int height) {

		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resizedImage.createGraphics();

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();

		return resizedImage;

	}

	public BaseWeapon getBaseWeapon() { return baseWeapon; }

}

