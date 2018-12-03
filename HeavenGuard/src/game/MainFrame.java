package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import elements.*;


public class MainFrame extends JFrame {

	// Path for images that are used in environment
	private final String BG_PATH = "HeavenGuard/res/images/misc/background.png";
	private final String BASE_PATH = "HeavenGuard/res/images/misc/base.png";
	private final String HOUSE_PATH = "HeavenGuard/res/images/misc/house.png";

	// Weapon of the Base in this frame and its Bullet
	private BaseWeapon baseWeapon = null;
	private Bullet bullet = null;
	
	private ArrayList<Bullet> bulletsOnScreen;

	// A custom listener for tracking mouse motions
	private final HGMouseMotionListener listener = new HGMouseMotionListener(this);

	// Containers for images	
	JLabel backgroundContainer = null;
	JLabel baseContainer = null;
	JLabel firstWeaponContainer = null; // initial weapon, blueprint object for other draws
	JLabel[] houseContainers = new JLabel[4];
	
	JProgressBar cannonBar = null;

	// Width and height of the final screen in terms of pixels
	private final int screenWidth = getToolkit().getScreenSize().width;
	private final int screenHeight = getToolkit().getScreenSize().height;

	// X and Y coordinates of weapon and bullets
	private int weaponX, weaponY, bulletX ,bulletY;
	
	BufferedImage bulletImage, weaponImage;
	
	private int refreshRate = 50; // Refresh rate of the screen in milliseconds

	// Methods to initialize BufferedImages and JLabels
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

		baseWeapon = new WeaponBuilder(30, this).fireSpeed(10).build(tag);
		bullet = baseWeapon.getBullet();
		
		weaponImage = (BufferedImage) baseWeapon.getImage();
		bulletImage = (BufferedImage) bullet.getImage();

		firstWeaponContainer = new JLabel(baseWeapon);

	}

	public void createBase() {

		BufferedImage baseImage = null;

		try {

			baseImage = ImageIO.read(new File(BASE_PATH));

		} catch(IOException e) {

			System.out.println("Could not load base");

		}

		Image scaledImage = getScaledImage(baseImage, screenWidth / 6, screenHeight / 8);

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

		Image scaledImage = getScaledImage(houseImage, screenWidth / 6, screenHeight / 8);

		ImageIcon house = new ImageIcon(scaledImage);

		for(int i = 0; i < houseContainers.length; i++) {

			houseContainers[i] = new JLabel(house);

		}

	}

	public MainFrame(String title) {

		super(title);

		// Making the frame full screen
		setExtendedState(MAXIMIZED_BOTH);
		// Removing system buttons
		setUndecorated(true);

		// A Timer object to refresh the screen at every refreshRate milliseconds
		Timer timer = new Timer(refreshRate, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				repaint();

			}
		});
		
		bulletsOnScreen = new ArrayList<>();
		
		cannonBar = new JProgressBar(0, 100);
		
		// Creating the environment
		createBackground();
		createBase();
		createHouse();
		createWeapon("cannon");
		
		timer.start();

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
		
		constraints.gridx = 3;
		constraints.weightx = 1.0;
		constraints.anchor = GridBagConstraints.SOUTHWEST;
		
		backgroundContainer.add(cannonBar, constraints);

		//firstWeaponContainer.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
		//baseContainer.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));

		getContentPane().add(backgroundContainer);
		
		pack();
		
		bulletX = weaponX;
		System.out.println(bulletX);

		// Adding listeners
		addMouseListener(new HGMouseListener(this));
		addMouseMotionListener(listener);
		addKeyListener(new HGKeyListener());

		// Making the frame visible
		setVisible(true);

	}

	// A method to scale image
	public static Image getScaledImage(Image image, int width, int height) {

		/*
		 * 
		 * Create a BufferedImage container to draw the image into
		 * 
		 * Draw the image into it by getting its Graphics2D object and setting appropriate
		 * RenderingHint(s)
		 * 
		 */
		
		BufferedImage resizedImage = new BufferedImage(width, height, 
				BufferedImage.TYPE_INT_ARGB);
		
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

	// This is where the magic happens
	@Override
	public void paint(Graphics g) {

		// Every object in the frame will be set after superclass' constructor call
		super.paint(g); // I mean after this line

		// How many degrees should the weapon rotate in terms of degrees
		double rotationAngle = baseWeapon.aimAngle(listener.getX(), listener.getY(), screenWidth / 2,
				screenHeight - baseContainer.getHeight());
		
		baseWeapon.setAngle(rotationAngle);

		Graphics2D g2d = (Graphics2D) getGraphics();

		// AffineTansform object to set the rotation of the Graphics object
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(rotationAngle), 
				baseContainer.getX() + (baseContainer.getWidth() / 2), baseContainer.getY());

		// Saving the initial transform state to reverting back to it once the drawing is complete
		AffineTransform backup = g2d.getTransform();

		g2d.setTransform(transform);
		
		firstWeaponContainer.setVisible(false);
		
		weaponX = firstWeaponContainer.getX();
		weaponY = firstWeaponContainer.getY();
		
		bulletX = weaponX + bullet.getIconWidth() / 2;
		bulletY = weaponY - weaponImage.getHeight();
		
		bullet.setCurrentLocation(new Point(bulletX, bulletY));
		
		g2d.drawImage(weaponImage, weaponX, weaponY, null);
			
		while(baseWeapon.isFiring()) {
						
			bulletsOnScreen.add(baseWeapon.fire(g2d));	
			
		}

		g2d.setTransform(backup);

	}
	
	public JLabel getWeaponContainer() {
		
		return firstWeaponContainer;
		
	}

	public ArrayList<Bullet> getBulletsOnScreen() {
		return bulletsOnScreen;
	}

	public void setBulletsOnScreen(ArrayList<Bullet> bulletsOnScreen) {
		this.bulletsOnScreen = bulletsOnScreen;
	}
	
	
	
}

