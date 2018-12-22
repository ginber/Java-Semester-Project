package elements;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import game.MainFrame;

public abstract class EnemyShip extends JLabel {


	private int health, damage, speed, level;
	private int xPosition,yPosition;
	private MainFrame context = null;
	private String tag, imgPath, explodedImgPath;
	private boolean isDead = false;
	private EnemyShipBuilder builder = null;
	private int index = 0;

	final static String EXPLODED_PATH = "HeavenGuard/res/images/spaceship1/boom.png";

	public EnemyShip(EnemyShipBuilder builder) {

		this.builder = builder;

		health = builder.health;
		damage = builder.damage;
		speed = builder.speed;
		level = builder.lvl;
		tag = builder.tag;
		imgPath = builder.imgPath;	
		context = builder.context;

	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}

	public void setPath(String imgPath) {
		this.imgPath = imgPath;
	}


	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public MainFrame getContext() {
		return context;
	}

	public void setContext(MainFrame context) {
		this.context = context;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getExplodedImgPath() {
		return explodedImgPath;
	}

	public void setExplodedImgPath(String explodedImgPath) {
		this.explodedImgPath = explodedImgPath;
	}

	public EnemyShipBuilder getBuilder() {

		return builder;

	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public abstract void fire();
	public abstract void move();

	public void die() {
		
		

		imgPath = EXPLODED_PATH;

		//move();
		
		System.out.println("x: " + getxPosition());
		
		

		Thread explosionThread = new Thread(new Runnable() {
			
			@Override
			public void run() {

				try {

					if(!isDead) {
						
						System.out.println("EnemyShip position when it died: \nx = " + getX() + "\ny = " + getY());
						
						Point currentLocation = new Point(getxPosition(), getyPosition());				
						
						isDead = true;
						
						Image explosionImage = MainFrame.getScaledImage(
								ImageIO.read(new File(imgPath)),
								getWidth(),
								getHeight());
						
						/*
						
						setIcon(new ImageIcon(MainFrame.getScaledImage(
								ImageIO.read(new File(imgPath)),
								getWidth(),
								getHeight())));
								
								
						
						setIcon(getIcon());
						
						System.out.println("EnemyShip position after icon: \nx = " + getX() + "\ny = " + getY());
						
						*/
						
						setLocation(currentLocation);
						setIcon(null);
						Graphics2D g2d = (Graphics2D) context.getGraphics();
						
						for(int i = 0; i < 700; i++) {
							
							g2d.drawImage(explosionImage, currentLocation.x, currentLocation.y, null);
							Thread.sleep(1);
							
						}
						d
						System.out.println("EnemyShip position after sleeping: \nx = " + getX() + "\ny = " + getY());
						
						
						
					}

				}  catch(IOException e) {

					e.printStackTrace();

				} catch(InterruptedException e) {

					e.printStackTrace();

				}

			}
		});

		explosionThread.start();

	}

}
