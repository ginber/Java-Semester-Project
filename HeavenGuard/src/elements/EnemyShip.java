package elements;

import java.util.*;

import javax.swing.ImageIcon;

import game.MainFrame;

public abstract class EnemyShip extends ImageIcon{
	
	private int health, damage, speed;
	private int xPosition,yPosition;
	private MainFrame context = null;
	private String tag, imgPath;
	
	public EnemyShip(String tag, String imgPath,MainFrame context) {
		
		setTag(tag);
		setPath(imgPath);
		setContext(context);
		this.xPosition =(int) Math.random()*context.getWidth();
		this.yPosition = (int) Math.random()*(context.getHeight()/3);
		
	}

	public EnemyShip(String tag, String imgPath) {
		
		setTag(tag);
		setPath(imgPath);
				
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

	public abstract void fire();
	public abstract void move(int xDir, int yDir);
	

}
