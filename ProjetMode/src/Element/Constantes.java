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
	
	public final static String TITRE = "Angry Nerds";
	
	public final static int LARGEUR_ECRAN = 1200;
	public final static int HAUTEUR_ECRAN = 610;
	public final static Color COULEUR_BACKGROUND = Color.BLACK;
	public final static Point COORDONNEES_ORIGINE = new Point(150,350);
	
	
	public final static int TAILLE_OISEAU = 40;
	public final static Color COULEUR_OISEAU = Color.RED;
	public final static Color COULEUR_OISEAU_TOUCHE = Color.PINK;
	
	public final static Color COULEUR_BEC = Color.YELLOW;
	
	public final static Color COULEUR_TRAJECTOIRE = Color.gray;
	public final static boolean TRAJECTOIRES = false;
	public final static boolean TRAJECTOIRE_UNIQUE = false;
	
	public final static int TAILLE_OBSTACLES = 40;
	public final static Color COULEUR_OBSTACLE = Color.GREEN;
	public final static Color COULEUR_OBSTACLE_TOUCHE = Color.RED;
	public final static int VITESSE_OBSTACLES = 1;
	
	public final static int HAUTEUR_SOL = 550;
	public final static Color COULEUR_SOL = Color.LIGHT_GRAY;
	
	public final static int RAYON_DEPART = 120;
	public final static boolean DISTANCE = true;
	
	
}
