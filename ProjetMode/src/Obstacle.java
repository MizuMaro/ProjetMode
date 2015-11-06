import java.awt.Point;
import javax.swing.JPanel;
/**
 * La classe Obstacle est la classe incarnant les obstacles du projet.
 * @author R�my
 *
 */

@SuppressWarnings("serial")
public class Obstacle extends JPanel {
	int taille;
	Point c;
	boolean actif;

	/**
	 * Constructeur � appeler pour cr�er un obstacle.
	 * @param c Coordonn�es de l'obstacle � cr�er.
	 */
	public Obstacle(Point c) {
		this.taille = Constantes.TAILLE_OBSTACLES;
		this.c = c;
		this.actif = true;

	}

	/**
	 * Cette fonction permet de savoir si l'obstacle a d�j� �t� touch� ou non.
	 * @return Si la fonction retourne vrai, l'obstacle peut �tre touch� par l'oiseau.
	 */
	public boolean getActif() {
		return this.actif;
	}

	/**
	 * Cette fonction permet de param�trer l'�tat de l'obstacle.
	 * @param t Si vrai, l'obstacle sera sujet aux collisions avec l'oiseau ; sinon, non.
	 */
	public void setActif(boolean t) {
		this.actif = t;
	}

	/**
	 * Cette fonction permet d'obtenir la taille de l'obstacle.
	 * @return La taille de l'obstacle sur lequel la fonction a �t� appel�e.
	 */
	public int getTaille() {
		return taille;
	}

	/**
	 * Cette fonction permet de param�trer la taille de l'obstacle.
	 * @param taille La taille de l'obstacle que l'on veut param�trer.
	 */
	public void setTaille(int taille) {
		this.taille = taille;
	}

	/**
	 * Cette fonction permet d'obtenir les coordonn�es de l'obstacle.
	 * @return Point contenant les coordonn�es x et y de l'obstacle.
	 */
	public Point getC() {
		return c;
	}

	/**
	 * Cette fonction permet de param�trer les coordonn�es de l'obstacle.
	 * @param x Coordonn�e en x de l'obstacle.
	 * @param y Coordonn�e en y de l'obstacle.
	 */
	public void setC(int x, int y) {
		this.c.x = x;
		this.c.y = y;
	}
}
