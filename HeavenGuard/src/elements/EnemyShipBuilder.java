package elements;

import java.awt.image.BufferedImage;
import java.util.Random;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import game.MainFrame;

public class EnemyShipBuilder {

	int health, damage, speed, lvl, xPosition, yPosition;
	String tag, imgPath;
	
	MainFrame context = null;
	
	BufferedImage shipImage = null;

	
	public EnemyShipBuilder(MainFrame context) {
		this.context = context;
	}
	
	public EnemyShipBuilder damage(int damage) {
		this.damage=damage;
		return this;
	}
	
	public EnemyShipBuilder health(int health) {
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
			//tag = BasicEnemyShip.TAG;
			
			hellBringers = new BasicEnemyShip(this);
			
		}	
		
		createImage(imgPath);
		//hellBringers.setImage(shipImage);
		hellBringers.setIcon(new ImageIcon(shipImage));
		
	
		
		// Burasý düzeltilmeli
		xPosition = new Random().nextInt(context.getScreenWidth() - shipImage.getWidth());
		yPosition = new Random().nextInt(context.getScreenHeight() / 3 - shipImage.getHeight());
		// -----------------------------------------------------------------------------------
		
		hellBringers.setxPosition(xPosition);
		hellBringers.setyPosition(yPosition);
		
		System.out.println("Enemy Ship Created\nType: " + tag + "\nHealth: " + health + "\nDamage: " + damage + "\nLevel: " + lvl);
		
		return hellBringers;
		
	}

	private void createImage(String shipPath) {

		try {

			shipImage = ImageIO.read(new File(shipPath));

		} catch(IOException e) {

			System.out.println("Enemy image could not be found");

		}

	}

	



}
