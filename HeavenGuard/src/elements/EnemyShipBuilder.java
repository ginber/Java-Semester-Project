package elements;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
		
		EnemyShip hellBringers = null;
		
		if(tag.equals(BasicEnemyShip.TAG)) {
			
			imgPath = BasicEnemyShip.IMG_PATH;
			tag = BasicEnemyShip.TAG;
			hellBringers = new BasicEnemyShip(this);
		}
		return hellBringers;
		
	}

	private void createImage(String IMG_Path) {

		try {

			weaponImage = ImageIO.read(new File(IMG_Path));

		} catch(IOException e) {

			System.out.println("Enemy image could not be found");

		}

	}





}
