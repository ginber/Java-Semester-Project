package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import elements.*;


public class MainFrame extends JFrame {

	private final String BG_PATH = "HeavenGuard/res/images/misc/background.png";
	private final String BASE_PATH = "HeavenGuard/res/images/misc/base.png";
	private final String HOUSE_PATH = "HeavenGuard/res/images/misc/house.png";

	private BaseWeapon baseWeapon = null;

	private final HGMouseMotionListener listener = new HGMouseMotionListener();

	JLabel backgroundContainer = null;
	JLabel baseContainer = null;
	JLabel firstWeaponContainer = null;
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

		baseWeapon = new BaseWeapon().createBaseWeapon(tag);

		firstWeaponContainer = new JLabel(baseWeapon);

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

		Timer timer = new Timer(50, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				repaint();

			}
		});
		
		timer.start();

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

		backgroundContainer.add(firstWeaponContainer, constraints);

		//weaponContainer.setBorder(BorderFactory.createLineBorder(Color.RED, 5));

		add(backgroundContainer);

		addMouseListener(new HGMouseListener());
		addMouseMotionListener(listener);
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

	@Override
	public void paint(Graphics g) {

		super.paint(g);

		double rotationAngle = baseWeapon.aimAngle(listener.getX(), listener.getY(), screenWidth / 2,
				screenHeight - baseContainer.getHeight());

		BufferedImage weaponImage = null;

		try {

			weaponImage = ImageIO.read(new File(baseWeapon.getWeaponPath()));

		} catch(IOException e) {

			System.out.println("Could not load the weapon image for some reason lol");

		}

		Graphics2D g2d = (Graphics2D) getGraphics();

		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(rotationAngle), baseContainer.getX() + (baseContainer.getWidth() / 2), baseContainer.getY());

		AffineTransform backup = g2d.getTransform();

		g2d.setTransform(transform);
		
		firstWeaponContainer.setVisible(false);

		g2d.drawImage(weaponImage, firstWeaponContainer.getX(), screenHeight - baseContainer.getHeight() - firstWeaponContainer.getHeight(), null);
		
		baseWeapon.setImage(weaponImage);

		g2d.setTransform(backup);

	}

}

