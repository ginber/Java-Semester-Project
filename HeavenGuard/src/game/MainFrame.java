package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
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

	private BaseWeapon baseWeapon = null;

	JLabel backgroundContainer = null;
	JLabel baseContainer = null;
	JLabel weaponContainer = null;
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
	
	public void createWeapon(String tag) {
		
		baseWeapon = new BaseWeapon().createWeapon(tag);
		
		weaponContainer = new JLabel(baseWeapon);
		
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
		createWeapon("cannon");
		
		backgroundContainer.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.fill = GridBagConstraints.BOTH;
		
		backgroundContainer.add(Box.createGlue(), constraints); // Dummy object for GridBagLayout
		
		constraints.gridy = 2;
		constraints.weighty = 0;
		constraints.anchor = GridBagConstraints.PAGE_END;
		constraints.fill = GridBagConstraints.NONE;
		
		constraints.gridx = 2;
		backgroundContainer.add(baseContainer, constraints);
		
		constraints.gridx = 0;
		backgroundContainer.add(houseContainers[0], constraints);
		
		constraints.gridx = 1;
		backgroundContainer.add(houseContainers[1], constraints);
		
		constraints.gridx = 3;
		backgroundContainer.add(houseContainers[2], constraints);
		
		constraints.gridx = 4;
		backgroundContainer.add(houseContainers[3], constraints);
		
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.anchor = GridBagConstraints.PAGE_END;
		
		backgroundContainer.add(weaponContainer, constraints);
		
		//weaponContainer.setBorder(BorderFactory.createLineBorder(Color.RED, 5));

		add(backgroundContainer);

		addMouseListener(new HGMouseListener());
		addMouseMotionListener(new HGMouseMotionListener(this));
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
	
	public int getBaseHeight() { return baseContainer.getHeight(); }
	
	public int getScreenWidth() { return screenWidth; }
	public int getScreenHeight() { return screenHeight; }
	
	public void rotateWeapon(final double rotationAngle) {
		
		BufferedImage weaponImage = null;
		
		try {
			
			weaponImage = ImageIO.read(new File(baseWeapon.getWeaponPath()));
			
		} catch(IOException e) {
			
			System.out.println("Could not loat the weapon image for some reason lol");
			
		}
		
		Graphics g = getGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(rotationAngle));
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		
		g2d.drawImage(op.filter(weaponImage, null), screenWidth / 2, screenHeight - baseContainer.getHeight(), null);
		
	}

}

