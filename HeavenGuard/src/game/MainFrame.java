package game;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import elements.*;


public class MainFrame extends JFrame {
	
	private final String BG_PATH = "res/images/misc/background.png";
	private final String BASE_PATH = "res/images/misc/base.png";
	
	private BaseWeapon baseWeapon;
	
	JPanel container;
	
	JLabel backgroundContainer = null;
	
	private final int screenWidth = getToolkit().getScreenSize().width;
	private final int screenHeight = getToolkit().getScreenSize().height;
	
	public void createBackground() {
		
		BufferedImage bgImage = null;
		
		try {
			
			bgImage = ImageIO.read(new File(BG_PATH));
			
		} catch(IOException e) {
			
			System.out.println("Could not load background");
			
		}
		
		Image scaledImage = getScaledImage(bgImage, screenWidth, screenHeight);
		
		ImageIcon background = new ImageIcon(scaledImage);
		
		backgroundContainer = new JLabel(background);
		
	}
	
	public MainFrame(String title) {
		
		super(title);
		
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
		
		container = new JPanel();
		//container.setLayout(new OverlayLayout(container));
		
		createBackground();
		
		JLabel base = new JLabel(new ImageIcon(BASE_PATH));
		
		/*
		base.setAlignmentX(Component.CENTER_ALIGNMENT);
		base.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		base.setOpaque(false);
		backgroundContainer.setOpaque(false);
		
		*/
		
		//backgroundContainer.add(base);
		container.add(backgroundContainer);
		//container.add(base);
		
		add(container);
		
		addMouseListener(new HGMouseListener(this));
		addKeyListener(new HGKeyListener());
		
		setVisible(true);
		
	}
	
	public static Image getScaledImage(Image image, int width, int height) {
		
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = resizedImage.createGraphics();
		
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.drawImage(image, 0, 0, width, height, null);
	    g.dispose();

	    return resizedImage;
		
	}
	
	public BaseWeapon getBaseWeapon() { return baseWeapon; }

}

