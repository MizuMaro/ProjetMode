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
	
	/** Constructeur privé */
	private Images(){
		try {
			NAME = ImageIO.read(new File("img/menu/name.png"));
			SKY = ImageIO.read(new File("img/menu/sky.jpg"));
			JOUER = ImageIO.read(new File("img/menu/Jouer.png"));
			QUITTER = ImageIO.read(new File("img/menu/Quitter.png"));
			JOUER1 = ImageIO.read(new File("img/menu/Jouer1.png"));
			QUITTER1 = ImageIO.read(new File("img/menu/Quitter1.png"));
			FREE = ImageIO.read(new File("img/menu/Free.png"));
			FREE1 = ImageIO.read(new File("img/menu/Free1.png"));
			GIF = ImageIO.read(new File("img/menu/red.gif"));
			
			BIRD = ImageIO.read(new File("img/birds/red/bird.png"));
			BACKGROUND = ImageIO.read(new File("img/background.jpg"));
			FREE_BACKGROUND = ImageIO.read(new File("img/free_background.png"));
			SLINGSHOT = ImageIO.read(new File("img/slingshot.png"));
			SLINGSHOT_UP = ImageIO.read(new File("img/slingshot_up.png"));
			OBSTACLE = ImageIO.read(new File("img/caisse.png"));
			CAISSE_RONDE = ImageIO.read(new File("img/caisse_ronde.png"));
			CAISSE_BOUGE = ImageIO.read(new File("img/caisse_bouge.png"));
			ROND_BOUGE = ImageIO.read(new File("img/rond_bouge.png"));
			VICTORY = ImageIO.read(new File("img/victory.png"));
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	
	}
	@SuppressWarnings("unused")
	private static Images i = new Images();

	/** Instance unique pré-initialisée */
	private static Images INSTANCE = new Images();
	
	/** Point d'accès pour l'instance unique du singleton */
	public static Images getInstance(){	
		return INSTANCE;
	}
	
	// Images du menu
	
	public Image NAME;
	public Image SKY;
	public Image JOUER;
	public Image QUITTER;
	public Image JOUER1;
	public Image QUITTER1;
	public Image FREE;
	public Image FREE1;
	public Image GIF;
	public Image VICTORY;
	
	
	// Images du jeu
	
	public Image BIRD;
	public Image BACKGROUND;
	public Image FREE_BACKGROUND;
	public Image SLINGSHOT;
	public Image SLINGSHOT_UP;
	public Image OBSTACLE;
	public Image CAISSE_RONDE;
	public Image CAISSE_BOUGE;
	public Image ROND_BOUGE;
	
}
	
	

