package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
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
import javax.swing.JLayeredPane;
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
	private final String TRIPLEKILL_PATH = "HeavenGuard/res/music/triplekill.wav";
	private final String QUADRAKILL_PATH = "HeavenGuard/res/music/quadrakill.wav";
	private final String PENTAKILL_PATH = "HeavenGuard/res/music/pentakill.wav";
	private final String LEGENDARY_PATH = "HeavenGuard/res/music/legendary.wav";
	private final String DOUBLEKILL_PATH = "HeavenGuard/res/music/doublekill.wav";



	// Weapon of the Base in this frame, its Bullet and an EnemyShip 
	// object to create enemy ships
	private BaseWeapon baseWeapon = null;
	private Bullet bullet = null;
	private EnemyShip enemyShip = null;

	private int enemyMove = 1;
	private int score = 0;
	private int baseHP = 100;
	private int kebaboinadder = 0, combo = 0;
	private int gridDetected;

	public int kebaboins = 0, baselevel = 1, houselevel = 1, weaponlevel = 1,prevHighScore;

	public boolean secondWeaponAvailable = false;

	public String berkaysinirlenme = "", playername;
	public String current = "Buy Machine Gun";

	private Graphics2D g2d = null;
	private boolean isMusicPlaying = false; // Music won't play when the game starts as default 
	private boolean sfx = true;

	public boolean isPaused = false;
	// Clip object to play audio files
	private Clip clip = null;
	private Clip fireClip = null;
	private Clip collisionClip = null;
	private Clip doublekill = null;
	private Clip triplekill = null;
	private Clip quadrakill = null;
	private Clip pentakill = null;
	private Clip legendary = null;

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
	JProgressBar baseHealthBar = new JProgressBar();
	
	ArrayList<JLabel> enemyBullets = new ArrayList<>();

	JLabel scoreLabel = null;
	JLabel kebaboinlabel = null;

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

		//backgroundContainer = new JLayeredPane();

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

	public MainFrame(String title, boolean start, String playername) {

		if(start) {


			setTitle(title);

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
					menu.editmenu();
					kebaboinadder++;
					if (kebaboinadder > 100) {
						kebaboins += houseContainers.length * (houselevel);
						baseHP += houseContainers.length * (houselevel);
						kebaboinadder = 0;
					}
					if (baseHP > baselevel * 100) {
						baseHP = baselevel * 100;
					}
				}

			}
					);

			bulletsOnScreen = new ArrayList<>();
			shipsOnScreen = new ArrayList<>();

			scoreLabel = new JLabel();
			scoreLabel.setForeground(Color.RED);
			scoreLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
			
			kebaboinlabel = new JLabel();
			kebaboinlabel.setForeground(Color.RED);
			kebaboinlabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));

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
			File a = new File(DOUBLEKILL_PATH);
			File b = new File(TRIPLEKILL_PATH);
			File c = new File(QUADRAKILL_PATH);
			File d = new File(PENTAKILL_PATH);
			File ef = new File(LEGENDARY_PATH);

			AudioInputStream doub = null;
			AudioInputStream trip = null;
			AudioInputStream quad = null;
			AudioInputStream pent = null;
			AudioInputStream leg = null;


			AudioInputStream bgInput = null;
			AudioInputStream fireInput = null;
			AudioInputStream collisionInput = null;
			try {

				doub = AudioSystem.getAudioInputStream(a);
				trip = AudioSystem.getAudioInputStream(b);
				quad = AudioSystem.getAudioInputStream(c);
				pent = AudioSystem.getAudioInputStream(d);
				leg = AudioSystem.getAudioInputStream(ef);

				doublekill = AudioSystem.getClip();
				triplekill = AudioSystem.getClip();
				quadrakill = AudioSystem.getClip();
				pentakill = AudioSystem.getClip();
				legendary = AudioSystem.getClip();

				doublekill.open(doub);
				triplekill.open(trip);
				quadrakill.open(quad);
				pentakill.open(pent);
				legendary.open(leg);

			} catch (LineUnavailableException kk1) {

				System.out.println("Could not retrieve the audio system line");

			} catch (IOException kk2) {

				System.out.println("Could not load music from audio file");

			} catch (UnsupportedAudioFileException kk3) {

				System.out.println("Audio file is not supported in your system");

			}


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

			// Adding health bars for houses
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
			
			// Adding health bar of our base 
			baseHealthBar.setValue(100);
			baseHealthBar.setPreferredSize(new Dimension(100,10));
			baseHealthBar.setForeground(Color.GREEN);
			baseContainer.setLayout(new GridBagLayout());
			constraints.weightx = 1;
			constraints.weighty = 1 ; 
			constraints.anchor = GridBagConstraints.CENTER;
			baseContainer.add(baseHealthBar, constraints);

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

			constraints.gridy = 1;
			
			backgroundContainer.add(kebaboinlabel, constraints);

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
					if (!bullet.isOnScreen()) {
						combo = 0;
					}
					if (bullet.isHit()) {
						combo++;
						if (combo == 2) {
							playDoubleKill();
						}
						else if (combo == 3) {
							playTripleKill();
						}
						else if (combo == 4) {
							playQuadraKill();
						}
						else if (combo == 5) {
							playPentaKill();
						}
						else if (combo > 5) {
							playLegendary();
						}

					}

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

			/*
			if(enemyMove % 70 == 0) {

				enemyShip.fire();

			}
			*/
			
			for(int i = 0; i < houseContainers.length; i++) {

					enemyShip.setGridIn(i);
					
					int rng = new Random().nextInt(200);
					// %2 chance
					if(rng == 0) {
						
						enemyShip.fire();
						
					
				}
				
			}

		}

		for(EnemyShip ship : shipsOnScreen) {

			if(enemyMove % movingFactor == 0 && !ship.isDead() ) {

				ship.move(); // each 120 milliseconds
				//System.out.println("EnemyShip moved");
				//backgroundContainer.add(ship);

			} 

			//g2d.drawImage(ship.getImage(), ship.getxPosition(), ship.getyPosition(), null);

		}

		scoreLabel.setText("Current Score: " + score);
		kebaboinlabel.setText("Kebaboin: " + kebaboins);


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

	public JLabel getBackgroundContainer() {
		return backgroundContainer;
	}

	public void setBackgroundContainer(JLabel backgroundContainer) {
		this.backgroundContainer = backgroundContainer;
	}

	public ArrayList<JLabel> getEnemyBullets() {
		return enemyBullets;
	}

	public void setEnemyBullets(ArrayList<JLabel> enemyBullets) {
		this.enemyBullets = enemyBullets;
	}
	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public void submitScore(int Score) {

		File asd = new File("C:/Users/alican/Desktop/scorelist.txt");
		asd.getParentFile().mkdirs();
		Random rand = new Random();


		try {
			PrintWriter outFile = new PrintWriter(asd);
			outFile.print(playername + "\n");
			int[] store = new int[30];
			for (int i = 0; i<20; i++) {
				store[i]=rand.nextInt(10);
			}
			if ((store[3] + store[7])>9) {
				store[26] = (store[3] + store[7])%10;
			}
			else {
				store[26] = store[3] + store[7];
			}
			if((store[14]*3 + store[19] - 5)>9) {
				store[27] = (store[14]*3 + store[19] - 5)%10;
			}
			else {
				store[27] = (store[14]*3 + store[19] - 5);
			}
			if ((store[2] + store[1])>9) {
				store[28] = (store[2] + store[1])%10;
			}
			else {
				store[28] = store[1] + store[2];
			}
			store[29] = 0;
			String abcd = "" + Score;
			store[23] = Integer.parseInt(abcd.substring(0,1));
			store[21] =  Integer.parseInt(abcd.substring(1,2));
			store[25] = Integer.parseInt(abcd.substring(2,3));
			store[22] =  Integer.parseInt(abcd.substring(3,4));
			store[24] = Integer.parseInt(abcd.substring(4,5));;

			for (int a = 0; a<30; a++) {
				outFile.print(store[a]);
			}

			outFile.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getLeaderBoards() {
		try {
			String asd = "", pName = "";
			FileInputStream fis = new FileInputStream("C:/Users/alican/Desktop/scorelist.txt");
			Scanner scan = new Scanner(fis);
			while (scan.hasNextLine()) {
				pName = scan.nextLine();
				asd = scan.nextLine();
				
			}
			int[] decryptor = new int[30];
			boolean test1 = false, test2 = false, test3 = false;
			for (int i = 0; i<asd.length(); i++) {
				decryptor[i] = Integer.parseInt(asd.substring(i, i+1));
			}
			if ( (decryptor[3] + decryptor[7])>9 && decryptor[26] == (decryptor[3] + decryptor[7])%10){
				test1=true;
			}
			else if ( (decryptor[3] + decryptor[7])<=9 && decryptor[26] == (decryptor[3] + decryptor[7])){
				test1=true;
			}
			if ( (decryptor[14]*3 + decryptor[19] - 5)>9 && decryptor[27] == (decryptor[14]*3 + decryptor[19] - 5)%10){
				test2=true;
			}
			else if ( (decryptor[14]*3 + decryptor[19] - 5)<=9 && decryptor[27] == (decryptor[14]*3 + decryptor[19] - 5)){
				test2=true;
			}
			if ( (decryptor[2] + decryptor[1])>9 && decryptor[28] == (decryptor[2] + decryptor[1])%10){
				test3=true;
			}
			else if ( (decryptor[2] + decryptor[1])<=9 && decryptor[28] == (decryptor[2] + decryptor[1])){
				test3=true;
			}

			if (test1 && test2 && test3) {
				berkaysinirlenme = "" + decryptor[23] + decryptor[21] + decryptor[25] + decryptor[22] + decryptor[24];
				prevHighScore = Integer.parseInt(berkaysinirlenme);
			}
			else {
				File asdf = new File("C:/Users/alican/Desktop/scorelist.txt");
				asdf.getParentFile().mkdirs();

				PrintWriter outFile = new PrintWriter(asdf);
				outFile.print("");
				outFile.close();
				berkaysinirlenme = "y u try to hack fren :(";
			}


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void playDoubleKill() {

		if(sfx) {

			doublekill.setFramePosition(0);
			doublekill.start();

		}

	}

	public void playTripleKill() {

		if(sfx) {

			triplekill.setFramePosition(0);
			triplekill.start();

		}

	}
	public void playQuadraKill() {

		if(sfx) {

			quadrakill.setFramePosition(0);
			quadrakill.start();

		}

	}
	public void playPentaKill() {

		if(sfx) {

			pentakill.setFramePosition(0);
			pentakill.start();

		}

	}
	public void playLegendary() {

		if(sfx) {

			legendary.setFramePosition(0);
			legendary.start();

		}

	}

	public int getGridDetected() {
		
		return gridDetected;
		
	}

	public JProgressBar[] getHouseHealthBars() {
		return houseHealthBars;
	}

	public void setHouseHealthBars(JProgressBar[] houseHealthBars) {
		this.houseHealthBars = houseHealthBars;
	}

	public JLabel getBaseContainer() {
		return baseContainer;
	}

	public void setBaseContainer(JLabel baseContainer) {
		this.baseContainer = baseContainer;
	}

	public JProgressBar getBaseHealthBar() {
		return baseHealthBar;
	}

	public void setBaseHealthBar(JProgressBar baseHealthBar) {
		this.baseHealthBar = baseHealthBar;
	}

	


}

