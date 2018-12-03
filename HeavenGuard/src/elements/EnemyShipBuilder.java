package elements;

import java.awt.image.BufferedImage;
import java.util.Random;

import game.MainFrame;

public class EnemyShipBuilder {

	int health, damage, speed, lvl, xPosition, yPosition;
	String tag, imgPath;
	
	MainFrame context = null;
	
	BufferedImage weaponImage = null;

	
	public EnemyShipBuilder(MainFrame context) {
		this.context = context;
	}
	
	public EnemyShipBuilder damage(int damage) {
		this.damage=damage;
		return this;
	}
	
	public EnemyShipBuilder health(int damage) {
		this.health=health;
		return this;
	}
	
	public EnemyShipBuilder speed(int speed) {
		this.speed=speed;
		return this;
	}
	
	public EnemyShipBuilder level(int lvl) {
		this.lvl=lvl;
		return this;
	}

	public EnemyShip build(String tag) {
		
		EnemyShip hellBringers = null;
		
		if(tag.equals(BasicEnemyShip.TAG)) {
			
			imgPath = BasicEnemyShip.IMG_PATH;
			tag = BasicEnemyShip.TAG;
			
			hellBringers = new BasicEnemyShip(this);
			
		}
		
		xPosition = new Random().nextInt(context.getScreenWidth());
		yPosition = new Random().nextInt(context.getScreenHeight());
		
		hellBringers.setxPosition(xPosition);
		hellBringers.setyPosition(yPosition);
		
		return hellBringers;
		
	}


	



}
