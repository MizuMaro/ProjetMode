import java.awt.Point;
import javax.swing.JPanel;
/**
 * La classe Obstacle est la classe incarnant les obstacles du projet.
 * @author Rémy
 *
 */

@SuppressWarnings("serial")
public class Obstacle extends JPanel {
	int taille;
	Point c;
	boolean actif;

	/**
	 * Constructeur a appeler pour creer un obstacle.
	 * @param c Coordonnees de l'obstacle a creer.
	 */
	public Obstacle(Point c) {
		this.taille = Constantes.TAILLE_OBSTACLES;
		this.c = c;
		this.actif = true;

	}

	/**
	 * Cette fonction permet de savoir si l'obstacle a deja ete touche ou non.
	 * @return Si la fonction retourne vrai, l'obstacle peut etre touche par l'oiseau.
	 */
	public boolean getActif() {
		return this.actif;
	}

	/**
	 * Cette fonction permet de paramétrer l'etat de l'obstacle.
	 * @param t Si vrai, l'obstacle sera sujet aux collisions avec l'oiseau ; sinon, non.
	 */
	public void setActif(boolean t) {
		this.actif = t;
	}

	/**
	 * Cette fonction permet d'obtenir la taille de l'obstacle.
	 * @return La taille de l'obstacle sur lequel la fonction a ete appelee.
	 */
	public int getTaille() {
		return taille;
	}

	/**
	 * Cette fonction permet de parametrer la taille de l'obstacle.
	 * @param taille La taille de l'obstacle que l'on veut parametrer.
	 */
	public void setTaille(int taille) {
		this.taille = taille;
	}

	/**
	 * Cette fonction permet d'obtenir les coordonnées de l'obstacle.
	 * @return Point contenant les coordonnées x et y de l'obstacle.
	 */
	public Point getC() {
		return c;
	}

	/**
	 * Cette fonction permet de parametrer les coordonnées de l'obstacle.
	 * @param x Coordonnée en x de l'obstacle.
	 * @param y Coordonnée en y de l'obstacle.
	 */
	public void setC(int x, int y) {
		this.c.x = x;
		this.c.y = y;
	}
}
