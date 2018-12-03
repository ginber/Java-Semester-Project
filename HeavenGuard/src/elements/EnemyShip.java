package elements;

import javax.swing.ImageIcon;

public abstract class EnemyShip extends ImageIcon {
	
	private int health, damage, speed;
	
	private String tag, imgPath;
	
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
	
	public abstract void fire();
	public abstract void move(int xDir, int yDir);

}
