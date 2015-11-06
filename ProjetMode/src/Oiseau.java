
import java.awt.Point;
import java.util.ArrayList;

public class Oiseau {
	int taille;
	Point c;
	Point c2 = new Point();
	ArrayList<Point> passage = new ArrayList<>();
	ArrayList<Point> passageTangInter = new ArrayList<>();

	// ajouter angle pour l'oiseau
	/**
	 * Constructeur de l'oiseau.
	 * @param c Coordonn�es o� l'on souhaite placer l'oiseau.
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
	 * Param�tre la taille de l'oiseau.
	 * @param taille Taille souhait�e pour l'oiseau.
	 */
	public void setTaille(int taille) {
		this.taille = taille;
	}

	/**
	 * Permet de savoir les coordonn�es actuelles de l'oiseau.
	 * @return Retourne un point, comportant les coordonn�es en x et y de l'oiseau.
	 */
	public Point getC() {
		return c;
	}

	/**
	 * Param�tre la position de l'oiseau.
	 * @param x Coordonn�e en x de l'oiseau.
	 * @param y Coordonn�e en y de l'oiseau.
	 */
	public void setC(int x, int y) {
		this.c.x = x;
		this.c.y = y;

		// pour afficher en pointill�

		if (x % 3 == 0)
			passage.add(new Point(x, y));
	}

	public void setC2(int x2, int y2) {
		this.c2.x = x2;
		this.c2.y = y2;
		if (x2 % 3 == 0)
			passageTangInter.add(new Point(x2, y2));

	}

	/**
	 * Permet d'effacer les trajectoires retenues en m�moire.
	 */
	public void effacerTrajectoire() {
		passage.clear();
	}
	
	public void effacerTrajectoireTangeante() {
		passageTangInter.clear();
	}
}
