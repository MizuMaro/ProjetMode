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
	
	final static String TITRE = "Angry Nerds";
	
	final static int LARGEUR_ECRAN = 1200;
	final static int HAUTEUR_ECRAN = 600;
	final static Color COULEUR_BACKGROUND = Color.BLACK;
	final static Point COORDONNEES_ORIGINE = new Point(50,400);
	
	
	final static int TAILLE_OISEAU = 40;
	final static Color COULEUR_OISEAU = Color.RED;
	final static Color COULEUR_OISEAU_TOUCHE = Color.pink;
	
	final static Color COULEUR_BEC = Color.YELLOW;
	
	final static Color COULEUR_TRAJECTOIRE = Color.gray;
	final static boolean TRAJECTOIRES = true;
	final static boolean TRAJECTOIRE_UNIQUE = true;
	
	final static int TAILLE_OBSTACLES = 40;
	final static Color COULEUR_OBSTACLE = Color.GREEN;
	final static Color COULEUR_OBSTACLE_TOUCHE = Color.RED;
	
}
