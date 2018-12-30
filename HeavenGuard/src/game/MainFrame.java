package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import elements.*;


public class MainFrame extends JFrame {

	// Path for resources
	private final String BG_PATH = "HeavenGuard/res/images/misc/menu.png";
	private final String BASE_PATH = "HeavenGuard/res/images/misc/base.png";
	private final String HOUSE_PATH = "HeavenGuard/res/images/misc/house.png";
	private final String BGMUSIC_PATH = "HeavenGuard/res/music/music01.wav";
	private final String SFXFIRE_PATH = "HeavenGuard/res/music/cannonfire.wav";
	private final String SFXONHIT_PATH = "HeavenGuard/res/music/explosion.wav";
	private final String CURSOR_PATH = "HeavenGuard/res/images/misc/cross.png";

	// Weapon of the Base in this frame, its Bullet and an EnemyShip 
	// object to create enemy ships
	private BaseWeapon baseWeapon = null;
	private Bullet bullet = null;
	private EnemyShip enemyShip = null;

	private int enemyMove = 1;
	private int score = 0;
	private int baseHP = 100;

	private Graphics2D g2d = null;
	private boolean isMusicPlaying = true; // Music will play when the game starts as default 
	private boolean sfx = true;
	
	public boolean isPaused = false;
	// Clip object to play audio files
	private Clip clip = null;
	private Clip fireClip = null;
	private Clip collisionClip = null;

	private Timer timer;

	// An ArrayList to store Bullet objects on the screen
	private ArrayList<Bullet> bulletsOnScreen;
	// Another one to store EnemyShip objects on the screen
	private ArrayList<EnemyShip> shipsOnScreen;
	// Another one to store scores for the leaderboard
	private ArrayList<Integer> scoreList;

	private int[] houseHP = {100,100,100,100};

	private int movingFactor = 4;

	// A custom listener for tracking mouse motions
	private final HGMouseMotionListener listener = new HGMouseMotionListener(this);

	// Containers for images	
	// firstWeaponContainer and bulletContainer will be invisible, but they will determine the
	// locations of images when paint() method is called
	JLabel backgroundContainer = null;
	JLabel baseContainer = null;
	JLabel firstWeaponContainer = null; // initial weapon, blueprint object for other draws
	JLabel bulletContainer = null;
	JLabel[] houseContainers = new JLabel[4];
	JProgressBar[] houseHealthBars = new JProgressBar[4];

	JLabel scoreLabel = null;

	private MenuBar menu = null;

	// If the weapon is a CannonWeapon, this JProgressBar will be shown when loading the bullet
	JProgressBar cannonBar = null;

	// Width and height of the final screen in terms of pixels
	private final int screenWidth = getToolkit().getScreenSize().width;
	private final int screenHeight = getToolkit().getScreenSize().height;

	// X and Y coordinates of weapon and bullets
	private int weaponX, weaponY, bulletX ,bulletY;

	// BuuferedImages for the main elements of the game
	BufferedImage bulletImage, weaponImage;


	private int refreshRate = 30; // Refresh rate of the screen in milliseconds


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

		baseWeapon = new WeaponBuilder(30, this).build(tag);
		bullet = baseWeapon.getBullet();

		weaponImage = (BufferedImage) baseWeapon.getImage();
		bulletImage = (BufferedImage) bullet.getImage();

		firstWeaponContainer = new JLabel(baseWeapon);
		bulletContainer = new JLabel(bullet);

	}

	public void createEnemy(String tag, int howMany) {

		for(int i = 0; i < howMany; i++) {

			enemyShip = new EnemyShipBuilder(this).level(1).build(tag);
			enemyShip.setIndex(shipsOnScreen.size());
			//enemyShip.setVisible(false);
			//enemyShip.setBorder(BorderFactory.createLineBorder(Color.RED, 10));
			shipsOnScreen.add(enemyShip);

		}

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
			houseHealthBars[i] = new JProgressBar();
			houseHealthBars[i].setValue(houseHP[i]);
			houseHealthBars[i].setPreferredSize(new Dimension(100, 10));
			houseHealthBars[i].setForeground(Color.RED);

		}

	}

	// Method to toggle music on/off
	public void playBackgroundMusic() {

		if(isMusicPlaying) {

			clip.setFramePosition(0); // Rewinding the audio to its beginning
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);

		} else {

			clip.stop();

		}

	}

	public void playFireSFX() {

		if(sfx) {

			fireClip.setFramePosition(0);
			fireClip.start();

		}

	}

	public void playCollisionSFX() {

		if(sfx) {

			collisionClip.setFramePosition(0);
			collisionClip.start();

		}

	}

	public void warning(Graphics2D g2d) {

		Graphics2D graphics = g2d;
		graphics.setFont(new Font("Comic Sans MS", Font.PLAIN, 60));
		graphics.setColor(Color.GREEN);
		graphics.drawString("O tuþ deðil caným kardeþim benim", screenWidth / 2, screenHeight / 2);

	}

	public MainFrame(String title) {

		super(title);

		// Making the frame full screen
		setExtendedState(MAXIMIZED_BOTH);
		// Removing system buttons
		setUndecorated(true);

		// A Timer object to refresh the screen at every refreshRate milliseconds
		timer = new Timer(refreshRate, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				repaint();
				enemyMove++;

			}
		});

		bulletsOnScreen = new ArrayList<>();
		shipsOnScreen = new ArrayList<>();

		scoreLabel = new JLabel();
		scoreLabel.setForeground(Color.RED);
		scoreLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

		menu = new MenuBar(this);

		cannonBar = new JProgressBar(0, 120);
		cannonBar.setPreferredSize(new Dimension(100, 8));
		cannonBar.setForeground(Color.CYAN);

		// Getting the audio file ready to play 
		// This shouldn't be in playBackgroundMusic(), otherwise it will change the object
		// that clip points out each time the method is called
		File musicPath = new File(BGMUSIC_PATH);
		File fireMusicPath = new File(SFXFIRE_PATH);
		File collisionMusicPath = new File(SFXONHIT_PATH);

		AudioInputStream bgInput = null;
		AudioInputStream fireInput = null;
		AudioInputStream collisionInput = null;

		try {

			bgInput = AudioSystem.getAudioInputStream(musicPath);
			fireInput = AudioSystem.getAudioInputStream(fireMusicPath);
			collisionInput = AudioSystem.getAudioInputStream(collisionMusicPath);

			clip = AudioSystem.getClip();
			fireClip = AudioSystem.getClip();
			collisionClip = AudioSystem.getClip();

			clip.open(bgInput);
			fireClip.open(fireInput);
			collisionClip.open(collisionInput);

		} catch (LineUnavailableException e) {

			System.out.println("Could not retrieve the audio system line");

		} catch (IOException e) {

			System.out.println("Could not load music from audio file");

		} catch (UnsupportedAudioFileException e) {

			System.out.println("Audio file is not supported in your system");

		}

		// Creating the environment & playing the music
		createBackground();
		createBase();
		createHouse();
		createWeapon("cannon");
		createEnemy("BES", 4);
		playBackgroundMusic();

		timer.start(); // Self explanatory

		// GridBagLayout is useful when adding many objects that are positioned 
		// relative to each other
		backgroundContainer.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		for(EnemyShip es : shipsOnScreen) {

			backgroundContainer.add(es);
			System.out.println("EnemyShip is created at \nx = "+ es.getX() + "\ny = " + es.getY());

		}

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.fill = GridBagConstraints.BOTH;

		// Should have added everything to Content Pane instead of a container image, but too late
		backgroundContainer.add(Box.createGlue(), constraints); // Dummy object for GridBagLayout

		// Adding houses
		constraints.gridy = 2;
		constraints.weighty = 0.0;
		constraints.weightx = 0.0;
		constraints.anchor = GridBagConstraints.PAGE_END;
		constraints.fill = GridBagConstraints.HORIZONTAL;

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

		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.NONE;

		// Adding healthbars for houses
		for(int i = 0; i < houseHealthBars.length + 1; i++) {

			if(i == 2) {
				continue;
			}

			constraints.gridx = i;

			if(i < 2) {

				backgroundContainer.add(houseHealthBars[i], constraints);

			} else if(i > 2) {

				backgroundContainer.add(houseHealthBars[i - 1], constraints);

			}

		}

		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.weightx = 0.0;
		constraints.weighty = 0.5;
		constraints.anchor = GridBagConstraints.SOUTH;

		backgroundContainer.add(bulletContainer, constraints);

		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.weightx = 1.0;
		constraints.weighty = 0.5;
		constraints.anchor = GridBagConstraints.PAGE_END;

		backgroundContainer.add(firstWeaponContainer, constraints);

		if(baseWeapon.getType().equals(CannonWeapon.TYPE)) {

			constraints.gridy = 1;
			constraints.weightx = 0.1;
			constraints.weighty = 0.15;
			constraints.anchor = GridBagConstraints.EAST;
			backgroundContainer.add(cannonBar, constraints);

		}

		constraints.gridx = 4;
		constraints.gridy = 0;
		constraints.weighty = 1.0;
		constraints.weightx = 1.0;
		constraints.anchor = GridBagConstraints.NORTH;

		backgroundContainer.add(scoreLabel, constraints);

		/*
		for(EnemyShip enemyShip : shipsOnScreen) {

			backgroundContainer.add(enemyShip);

		}
		 */
		// Kind of added everything into Content Pane, well, at least technically
		getContentPane().add(backgroundContainer);

		pack();

		// Adding listeners
		addMouseListener(new HGMouseListener(this));
		addMouseMotionListener(listener);
		addKeyListener(new HGKeyListener(this));

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
		 * algorithms to use while drawing it by using RenderingHints
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

		g2d = (Graphics2D) getGraphics();

		// AffineTansform object to set the rotation of the Graphics object
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(rotationAngle), 
				baseContainer.getX() + (baseContainer.getWidth() / 2), baseContainer.getY());

		// Saving the initial transform state to reverting back to it once the drawing is complete
		AffineTransform backup = g2d.getTransform();

		g2d.setTransform(transform);

		firstWeaponContainer.setVisible(false);
		bulletContainer.setVisible(false);

		weaponX = firstWeaponContainer.getX();
		weaponY = firstWeaponContainer.getY();

		bulletX = bulletContainer.getX();
		bulletY = bulletContainer.getY();

		g2d.drawImage(weaponImage, weaponX, weaponY, null);

		if(baseWeapon.isFiring()) {

			bullet = baseWeapon.createBullet();
			bullet.setCurrentLocation(new Point(bulletX, bulletY));
			baseWeapon.fire();
			playFireSFX();

		}	

		g2d.setTransform(backup);

		Iterator<Bullet> bulletIterator = bulletsOnScreen.iterator();

		try {

			for(Bullet b : bulletsOnScreen) {

				if(b != null) {


					b.move(b.getFiredAngle(), baseWeapon.getFireSpeed());

					g2d.drawImage(b.getImage(), b.getCurrentLocation().x, 
							b.getCurrentLocation().y, null);

				}

			}

		} catch(ConcurrentModificationException e) {

			System.out.println("Böyle biþey yok ki");

		}



		while(bulletIterator.hasNext()) {

			bullet = bulletIterator.next();

			if(!bullet.isOnScreen() || bullet.isHit()) {

				if(bulletIterator != null) {

					bulletIterator.remove();

				}

			}  

		}

		Iterator<EnemyShip> shipIterator = shipsOnScreen.iterator();

		while(shipIterator.hasNext()) {

			enemyShip = shipIterator.next();

			if(enemyShip.isDead()) {

				//backgroundContainer.remove(shipsOnScreen.get(enemyShip.getIndex()));
				shipIterator.remove();
				score += 50;

			}

		}
		
		for(EnemyShip ship : shipsOnScreen) {

			if(enemyMove % movingFactor == 0 && !ship.isDead() ) {

				ship.move(); // each 120 milliseconds
				//backgroundContainer.add(ship);

			} 

			//g2d.drawImage(ship.getImage(), ship.getxPosition(), ship.getyPosition(), null);

		}

		scoreLabel.setText("Current Score: " + score);


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

	public int getRefreshRate() {
		return refreshRate;
	}

	public boolean isMusicPlaying() {
		return isMusicPlaying;
	}

	public void setMusicPlaying(boolean isMusicPlaying) {
		this.isMusicPlaying = isMusicPlaying;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public JLabel getFirstWeaponContainer() {
		return firstWeaponContainer;
	}

	public void setFirstWeaponContainer(JLabel firstWeaponContainer) {
		this.firstWeaponContainer = firstWeaponContainer;
	}

	public JLabel getBulletContainer() {
		return bulletContainer;
	}

	public void setBulletContainer(JLabel bulletContainer) {
		this.bulletContainer = bulletContainer;
	}

	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

	public EnemyShip getEnemyShip() {
		return enemyShip;
	}

	public void setEnemyShip(EnemyShip enemyShip) {
		this.enemyShip = enemyShip;
	}

	public boolean isSfx() {
		return sfx;
	}

	public JLabel[] getHouseContainers() {
		return houseContainers;
	}

	public void setHouseContainers(JLabel[] houseContainers) {
		this.houseContainers = houseContainers;
	}

	public void setSfx(boolean sfx) {
		this.sfx = sfx;
	}

	public ArrayList<EnemyShip> getShipsOnScreen() {
		return shipsOnScreen;
	}

	public void setShipsOnScreen(ArrayList<EnemyShip> shipsOnScreen) {
		this.shipsOnScreen = shipsOnScreen;
	}

	public MenuBar getMenu() {

		return menu;

	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<Integer> getScoreList() {
		return scoreList;
	}

	public void setScoreList(ArrayList<Integer> scoreList) {
		this.scoreList = scoreList;
	}

	public int getBaseHP() {
		return baseHP;
	}

	public void setBaseHP(int baseHP) {
		this.baseHP = baseHP;
	}

	public int[] getHouseHP() {
		return houseHP;
	}

	public void setHouseHP(int[] houseHP) {
		this.houseHP = houseHP;
	}



}

