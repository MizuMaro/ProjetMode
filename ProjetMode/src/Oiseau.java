
import java.awt.Point;
import java.util.ArrayList;

public class Oiseau {
	int taille;
	Point c;
	Point c2 = new Point();
	ArrayList<Point> passage = new ArrayList<>();

	// ajouter angle pour l'oiseau
	/**
	 * Constructeur de l'oiseau.
	 * @param c Coordonnées où l'on souhaite placer l'oiseau.
	 */
	public Oiseau(Point c) {
		this.taille = Constantes.TAILLE_OISEAU;
		this.c = c;

	}

	/**
	 * Retourne la taille de l'oiseau.
	 * @return taille de l'oiseau.
	 */
	public int getTaille() {
		return taille;
	}

	/**
	 * Paramètre la taille de l'oiseau.
	 * @param taille Taille souhaitée pour l'oiseau.
	 */
	public void setTaille(int taille) {
		this.taille = taille;
	}

	/**
	 * Permet de savoir les coordonnées actuelles de l'oiseau.
	 * @return Retourne un point, comportant les coordonnées en x et y de l'oiseau.
	 */
	public Point getC() {
		return c;
	}

	/**
	 * Paramètre la position de l'oiseau.
	 * @param x Coordonnée en x de l'oiseau.
	 * @param y Coordonnée en y de l'oiseau.
	 */
	public void setC(int x, int y) {
		this.c.x = x;
		this.c.y = y;
		// pour afficher en pointillé
		if (x % 3 == 0)
			passage.add(new Point(x, y));
	}

	public void setC2(int x2, int y2) {
		this.c2.x = x2;
		this.c2.y = y2;

	}

	/**
	 * Permet d'effacer les trajectoires retenues en mémoire.
	 */
	public void effacerTrajectoire() {
		passage.clear();
	}
}
