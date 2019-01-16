package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executor;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.MainFrame;

public class BasicEnemyShip extends EnemyShip {

	final static String TAG = "BES";
	final static String IMG_PATH = "HeavenGuard/res/images/spaceship1/enemy0frame.png";
	final static String IMG2_PATH = "HeavenGuard/res/images/spaceship1/enemy2.png";
	final static String IMG3_PATH = "HeavenGuard/res/images/spaceship1/boss.png";
	final static String BULLET_PATH = CannonWeapon.BULLET_PATH;

	private ArrayList<Image> bulletsFired = new ArrayList<>();
	private JLabel bulletLabel;
	private boolean hasFired = false;

	public BasicEnemyShip(EnemyShipBuilder builder) {

		super(builder);

	}

	@Override
	public void fire() {

		BasicEnemyBullet beb = new BasicEnemyBullet(BULLET_PATH, getBuilder().context);

		// JLabel bulletLabel = new JLabel(beb);

		Thread enemyFireThread = new Thread(new Runnable() {

			Graphics2D g2d = (Graphics2D) getBuilder().context.getBackgroundContainer().getGraphics();
			int xPos, yPos, changeY = 1;
			int bulletCount = 0;

			@Override
			public void run() {
				
				

				xPos = getxPosition();
				yPos = getyPosition() + 1 + changeY;

				while (yPos < getBuilder().context.getScreenHeight()
						- getBuilder().context.getHouseContainers()[0].getHeight()) {

					g2d.setColor(Color.CYAN);
					g2d.fillOval(xPos, yPos, 10, 10);

					yPos += changeY;

					for (Bullet b : getBuilder().context.getBulletsOnScreen()) {

						if (b.currentLocation.x < xPos + 10 && (b.currentLocation.x > xPos)
								&& (b.currentLocation.y < yPos + 10) && (b.currentLocation.y > yPos)) {

							// Bullets hit each other
							//System.out.println("mermiyi vurdun kerata");
							return;
						}


					}

					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				// evlerin canýný azalt
				for (int i = 0; i < 4; i++) {
					if ((getBuilder().context.getHouseContainers()[i].getX()
							+ (getBuilder().context.getHouseContainers()[i].getWidth()
									- getBuilder().context.getHouseContainers()[i].getIcon().getIconWidth())
									/ 2 <= xPos)
							&& (getBuilder().context.getHouseContainers()[i].getX()
									+ getBuilder().context.getHouseContainers()[i].getWidth()
									- (getBuilder().context.getHouseContainers()[i].getWidth()
											- getBuilder().context.getHouseContainers()[i].getIcon().getIconWidth())
											/ 2 >= xPos)) {
						getBuilder().context.getHouseHP()[i] -= 10;
						//System.out.println(i + ". evin caný: " + getBuilder().context.getHouseHP()[i]);
						getBuilder().context.getHouseHealthBars()[i].setValue(getBuilder().context.getHouseHP()[i]);
					}
				}
				
				if(getBuilder().context.getBaseContainer().getX() <= xPos && 
						getBuilder().context.getBaseContainer().getX() + getBuilder().context.getBaseContainer().getWidth() >= xPos ) {
					
					getBuilder().context.setBaseHP(getBuilder().context.getBaseHP()-10);
					getBuilder().context.getBaseHealthBar().setValue(getBuilder().context.getBaseHP());
				}
				for ( int i = 0 ; i < 4 ; i++) {
					if(getBuilder().context.getHouseHP()[i] > 51 && getBuilder().context.getHouseHP()[i] < 75 ) {
						
						
					
					BufferedImage houseImage = null;

					try {

						houseImage = ImageIO.read(new File(getBuilder().context.HOUSE2_PATH));

					} catch(IOException e) {

						System.out.println("Could not load houses");

					}

					Image scaledImage = MainFrame.getScaledImage(houseImage, getBuilder().context.getScreenWidth() / 6, getBuilder().context.getScreenHeight() / 8);

					ImageIcon house = new ImageIcon(scaledImage);
					
					getBuilder().context.getHouseContainers()[i].setIcon(house);
					
					}else if(getBuilder().context.getHouseHP()[i] > 0 && getBuilder().context.getHouseHP()[i] < 51 ) {
						
						
					
					BufferedImage houseImage = null;

					try {

						houseImage = ImageIO.read(new File(getBuilder().context.HOUSE3_PATH));

					} catch(IOException e) {

						System.out.println("Could not load houses");

					}

					Image scaledImage = MainFrame.getScaledImage(houseImage, getBuilder().context.getScreenWidth() / 6, getBuilder().context.getScreenHeight() / 8);

					ImageIcon house = new ImageIcon(scaledImage);
					
					getBuilder().context.getHouseContainers()[i].setIcon(house);
					
					}else if(getBuilder().context.getHouseHP()[i] == 0) {
						
						
					
					BufferedImage houseImage = null;

					try {

						houseImage = ImageIO.read(new File(getBuilder().context.HOUSE4_PATH));

					} catch(IOException e) {

						System.out.println("Could not load houses");

					}

					Image scaledImage = MainFrame.getScaledImage(houseImage, getBuilder().context.getScreenWidth() / 6, getBuilder().context.getScreenHeight() / 8);

					ImageIcon house = new ImageIcon(scaledImage);
					
					getBuilder().context.getHouseContainers()[i].setIcon(house);
					getBuilder().context.getHouseHP()[i] = -10;
					getBuilder().context.housecount --;
					getBuilder().context.getHouseHealthBars()[i].setVisible(false);
					}
				}
				
				
				
				
				
				
			}
		});

		enemyFireThread.start();
		
		//System.out.println(enemyFireThread.getName());

		/*
		 * 
		 * bulletLabel.setLocation(getxPosition(), getyPosition() +
		 * bulletLabel.getHeight());
		 * 
		 * MainFrame context = getBuilder().context;
		 * 
		 * GridBagConstraints gbc = new GridBagConstraints();
		 * 
		 * gbc.gridx = getGridIn(); gbc.gridy = 0; gbc.weightx = 0.5; gbc.weighty = 0.5;
		 * 
		 * context.getBackgroundContainer().add(bulletLabel);
		 * 
		 */

	}

	@Override
	public void move() {

		// Randomly decide whether the ship will move towards left or right & up and
		// down
		boolean right;
		boolean up = (Math.random() < 0.5) ? true : false;
		
		if(getxPosition() < getBuilder().context.getScreenWidth() / 2 - getBuilder().context.getScreenWidth() / 5) {
			right = (Math.random() < 0.55) ? true : false;
		}else if(getxPosition() > getBuilder().context.getScreenWidth() / 2 + getBuilder().context.getScreenWidth() / 5) {
			right = (Math.random() < 0.45) ? true : false;
		}else right = (Math.random() < 0.5) ? true : false;

		int changeX = (int) (Math.random() + 1) * (getLevel() * 5);
		int changeY = (int) (Math.random() + 1) * (getLevel() * 5);

		if (!right) {
			changeX = -changeX;
		}

		if (!up) {
			changeY = -changeY;
		}

		if (getxPosition() + changeX > 0 && getxPosition() + changeX < getBuilder().context.getScreenWidth()) {

			setxPosition(getxPosition() + changeX);

		} else {

			setxPosition(getxPosition() - changeX);

		}

		if (getyPosition() + changeY > 0 && getyPosition() + changeY < getBuilder().context.getScreenHeight()
				- getBuilder().context.getHouseContainers()[0].getHeight()) {

			setyPosition(getyPosition() + changeY);

		} else {

			setyPosition(getyPosition() - changeY);

		}

		setLocation(getxPosition(), getyPosition());

	}
	
	

}
