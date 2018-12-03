package elements;

import java.awt.image.BufferedImage;

import game.MainFrame;

public class EnemyShipBuilder {

	private int health, damage, speed;
	private int lvl;
	private String tag, imgPath;
	
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
		
		EnemyShip HellBringers = null;
		
		if(tag.equals(BasicEnemyShip.getTag())) {
			
			imgPath = BasicEnemyShip.getImgPath();
			tag = BasicEnemyShip.getTag();
			HellBringers = new BasicEnemyShip(this);
		}
		return HellBringers;
		
	}






}
