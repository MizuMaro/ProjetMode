package Element;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * La classe Images contient toutes les images utilisees dans le projet.
 * 
 * @author Remy
 *
 */
public class Images {
	
	// Images du menu
	
	public static Image NAME;
	public static Image SKY;
	public static Image JOUER;
	public static Image QUITTER;
	public static Image JOUER1;
	public static Image QUITTER1;
	public static Image GIF;
	
	
	// Images du jeu
	
	public static Image BIRD;
	public static Image BACKGROUND;
	public static Image SLINGSHOT;
	public static Image SLINGSHOT_UP;
	public static Image OBSTACLE;
	public static Image CAISSE_RONDE;
	
	
	
	/**
	 * Implementation du design pattern Singleton.
	 * L'instance est creee a l'initialisation.
	 */
	private Images(){
		try {
			NAME = ImageIO.read(new File("img/menu/name.png"));
			SKY = ImageIO.read(new File("img/menu/sky.jpg"));
			JOUER = ImageIO.read(new File("img/menu/Jouer.png"));
			QUITTER = ImageIO.read(new File("img/menu/Quitter.png"));
			JOUER1 = ImageIO.read(new File("img/menu/Jouer1.png"));
			QUITTER1 = ImageIO.read(new File("img/menu/Quitter1.png"));
			GIF = ImageIO.read(new File("img/menu/red.gif"));
			
			BIRD = ImageIO.read(new File("img/birds/red/bird.png"));
			BACKGROUND = ImageIO.read(new File("img/background.jpg"));
			SLINGSHOT = ImageIO.read(new File("img/slingshot.png"));
			SLINGSHOT_UP = ImageIO.read(new File("img/slingshot_up.png"));
			OBSTACLE = ImageIO.read(new File("img/caisse.png"));
			CAISSE_RONDE = ImageIO.read(new File("img/caisse_ronde.png"));
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	
	}
	@SuppressWarnings("unused")
	private static Images i = new Images();


}
