package elements;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import game.MainFrame;

public abstract class EnemyShip extends ImageIcon{

	
	private int health, damage, speed, level;
	private int xPosition,yPosition;
	private MainFrame context = null;
	private String tag, imgPath, explodedImgPath;
	
	final static String EXPLODED_PATH = "HeavenGuard/res/images/spaceship1/boom.png";
	
	public EnemyShip(EnemyShipBuilder builder) {
		
		health = builder.health;
		damage = builder.damage;
		speed = builder.speed;
		level = builder.lvl;
		tag = builder.tag;
		imgPath = builder.imgPath;	
		
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

	public abstract void fire();
	public abstract void move();

	public void die() {
		
		imgPath = EXPLODED_PATH;
		
		try {
			setImage(ImageIO.read(new File(EXPLODED_PATH)));
			//Thread.sleep(1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //catch(InterruptedException e) {
			//e.printStackTrace();
		//}
		
		
	}

}
