package elements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JLabel;

public class BasicEnemyShip extends EnemyShip {

	final static String TAG = "BES";
	final static String IMG_PATH = "HeavenGuard/res/images/spaceship1/enemy2.png";
	final static String BULLET_PATH = CannonWeapon.BULLET_PATH;


	public BasicEnemyShip(EnemyShipBuilder builder) {

		super(builder);

	}


	@Override
	public void fire() {
		
		System.out.println("Enemy firing!!!");		
		
		Thread enemyFireThread = new Thread(new Runnable() {
			
			Graphics2D g2d = (Graphics2D) getBuilder().context.getGraphics();
			int xPos, yPos, changeY = 0;
			int bulletCount = 0;
			
			@Override
			public void run() {
				
				xPos = getxPosition();
				yPos = getyPosition() + 10 + changeY;
				g2d.setColor(Color.CYAN);
				g2d.fillOval(xPos, yPos, 10, 10);
				
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				changeY++;
				
			}
		});
		
		enemyFireThread.start();

		
		
		
		
		//g2d.drawOval(xPos, yPos, 3, 10);

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
