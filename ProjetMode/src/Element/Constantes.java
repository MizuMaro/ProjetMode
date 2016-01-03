
package Element;
import java.awt.Color;
import java.awt.Point;

/**
 * La classe Constantes contient toutes les constantes utilisees dans le projet :
 * couleurs, tailles d'obstacles ainsi que d'autres parametres y sont presents.
 * 
 * @author Remy
 *
 */
public class Constantes {
	
	/** Constructeur privé */
	private Constantes(){}

	/** Instance unique pré-initialisée */
	private static Constantes INSTANCE = new Constantes();

	/** Point d'accès pour l'instance unique du singleton */
	public static Constantes getInstance(){	
		return INSTANCE;
	}

	
	public final String TITRE = "Angry Nerds";
	
	public final int[] TAILLE_ECRAN = new int[]{1200,610};
	public final Color COULEUR_BACKGROUND = Color.BLACK;
	public final Point COORDONNEES_ORIGINE = new Point(150,350);
	
	
	public final int TAILLE_OISEAU = 40;
	public final Color COULEUR_OISEAU = Color.RED;
	public final Color COULEUR_OISEAU_TOUCHE = Color.PINK;
	
	public final Color COULEUR_BEC = Color.ORANGE;
	
	public final Color COULEUR_TRAJECTOIRE1 = Color.WHITE;
	public final Color COULEUR_TRAJECTOIRE2 = Color.BLACK;
	public final boolean TRAJECTOIRES = false;
	public final boolean TRAJECTOIRE_UNIQUE = true;
	
	public final int TAILLE_OBSTACLES = 40;
	public final Color COULEUR_OBSTACLE = Color.GREEN;
	public final Color COULEUR_OBSTACLE_TOUCHE = Color.RED;
	public final int VITESSE_OBSTACLES = 1;
	
	public final int HAUTEUR_SOL = 525;
	public final Color COULEUR_SOL = Color.RED;
	
	public final int RAYON_DEPART = 200;
	public final boolean DISTANCE = true;
	
	
}
