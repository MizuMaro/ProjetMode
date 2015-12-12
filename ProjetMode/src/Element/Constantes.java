
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
	
	/**
	 * Implementation du design pattern Singleton.
	 * L'instance est creee a l'initialisation.
	 */
	private Constantes(){}
	@SuppressWarnings("unused")
	private static Constantes c = new Constantes();
	
	public final static String TITRE = "Angry Nerds";
	
	public final static int[] TAILLE_ECRAN = new int[]{1200,610};
	public final static Color COULEUR_BACKGROUND = Color.BLACK;
	public final static Point COORDONNEES_ORIGINE = new Point(150,350);
	
	
	public final static int TAILLE_OISEAU = 40;
	public final static Color COULEUR_OISEAU = Color.RED;
	public final static Color COULEUR_OISEAU_TOUCHE = Color.PINK;
	
	public final static Color COULEUR_BEC = Color.ORANGE;
	
	public final static Color COULEUR_TRAJECTOIRE = Color.gray;
	public final static boolean TRAJECTOIRES = true;
	public final static boolean TRAJECTOIRE_UNIQUE = true;
	
	public final static int TAILLE_OBSTACLES = 40;
	public final static Color COULEUR_OBSTACLE = Color.GREEN;
	public final static Color COULEUR_OBSTACLE_TOUCHE = Color.RED;
	public final static int VITESSE_OBSTACLES = 1;
	
	public final static int HAUTEUR_SOL = 525;
	public final static Color COULEUR_SOL = Color.RED;
	
	public final static int RAYON_DEPART = 200;
	public final static boolean DISTANCE = true;
	
	
}
