package elements;

import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executor;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import game.MainFrame;

public class BasicEnemyShip extends EnemyShip {

	final static String TAG = "BES";
	final static String IMG_PATH = "HeavenGuard/res/images/spaceship1/enemy2.png";
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
		
		JLabel bulletLabel = new JLabel(beb);
		
		bulletLabel.setLocation(getxPosition(), getyPosition() + bulletLabel.getHeight());
		
		MainFrame context = getBuilder().context;
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = getGridIn();
		gbc.gridy = 0;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		
		context.getBackgroundContainer().add(bulletLabel);

	}

	@Override
	public void move() {

		// Randomly decide whether the ship will move towards left or right & up and down
		boolean right = (Math.random() < 0.5) ? true : false;
		boolean up = (Math.random() < 0.5) ? true : false;

		int changeX = (int) (Math.random() + 1) * (getLevel() * 20);
		int changeY = (int) (Math.random() + 1) * (getLevel() * 20);

		if(!right) {
			changeX = -changeX;
		}

		if(!up) {
			changeY = -changeY;
		}


		if(getxPosition() + changeX > 0
				&& getxPosition() + changeX < getBuilder().context.getScreenWidth()) {

			setxPosition(getxPosition() + changeX);

		} else {

			setxPosition(getxPosition() - changeX);

		}

		if(getyPosition() + changeY > 0
				&& getyPosition() + changeY < getBuilder().context.getScreenHeight()
				- getBuilder().context.getHouseContainers()[0].getHeight()) {

			setyPosition(getyPosition() + changeY);

		} else {

			setyPosition(getyPosition() - changeY); 

		}

		setLocation(getxPosition(), getyPosition());

	}

}
