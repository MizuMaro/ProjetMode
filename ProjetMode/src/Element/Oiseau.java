package Element;

import java.awt.Point;
import java.util.ArrayList;

/**
 * La classe Oiseau permet d'instancier un oiseau avec ses differents parametres
 * : sa taille, ses coordonnees, son angle, sa vitesse...
 * 
 * @author R�my
 */
public class Oiseau {
	private int taille;
	private Point c;
	private Point c2 = new Point();
	private Point prochCoord;
	private Point depart = new Point(-50, -50);
	private Point arrivee = new Point(-50, -50);

	private boolean trajectoire = false;

	private ArrayList<Point> passage = new ArrayList<>();
	private ArrayList<Point> passageTangInter = new ArrayList<>();
	private boolean vole;
	private boolean victory;

	private int score;
	private double vitesse;
	private double angle;

	/**
	 * Constructeur de l'oiseau.
	 * 
	 * @param c
	 *            - Coordonnees ou l'on souhaite placer l'oiseau.
	 */
	public Oiseau(Point c) {
		this.taille = Constantes.getInstance().TAILLE_OISEAU;
		this.c = c;
		this.vole = false;
		this.setVictory(false);
		this.score = 0;
	}

	/**
	 * Definie si l'oiseau vole ou non.
	 * 
	 * @param b
	 *            - Vrai si l'oiseau vole, faux dans le cas contraire.
	 */
	public void setVole(boolean b) {
		this.vole = b;
	}

	/**
	 * Retourne un boolean pour savoir si l'oiseau vole.
	 * 
	 * @return Vrai si l'oiseau vole, faux dans le cas contraire.
	 */
	public boolean getVole() {
		return this.vole;
	}

	/**
	 * Retourne la taille de l'oiseau.
	 * 
	 * @return La taille de l'oiseau.
	 */
	public int getTaille() {
		return this.taille;
	}

	/**
	 * Parametre la taille de l'oiseau.
	 * 
	 * @param taille
	 *            - Taille souhaitee pour l'oiseau.
	 */
	public void setTaille(int taille) {
		this.taille = taille;
	}

	/**
	 * Permet de savoir les coordonnees actuelles de l'oiseau.
	 * 
	 * @return Retourne un point, comportant les coordonnees en x et y de
	 *         l'oiseau.
	 */
	public Point getC() {
		return this.c;
	}

	public Point getC2() {
		return this.c2;
	}

	/**
	 * Parametre la position de l'oiseau.
	 * 
	 * @param x
	 *            - Coordonnee en x de l'oiseau.
	 * @param y
	 *            - Coordonnee en y de l'oiseau.
	 */
	public void setC(int x, int y) {
		this.c.x = x;
		this.c.y = y;

		// pour afficher en pointilles
		if (x % 3 == 0 && this.trajectoire)
			passage.add(new Point(x, y));
	}

	/**
	 * Rentre un point dans la trajectoire de passage de l'oiseau
	 * 
	 * @param p
	 *            - Point a entrer.
	 */
	public void setCoord(Point p) {
		this.c = p;
		if (p.x % 3 == 0 && this.trajectoire)
			passage.add(p);
	}

	public void setProchaineCoord(Point p) {
		this.prochCoord = p;
	}

	public Point getProchaineCoord() {
		return this.prochCoord;
	}

	/**
	 * Permet d'ajuster les coordonnees du prochain point du bec de l'oiseau.
	 * 
	 * @param x2
	 *            - Coordonnee en X.
	 * @param y2
	 *            - coordonnee en Y.
	 */
	public void setC2(int x2, int y2) {
		this.c2.x = x2;
		this.c2.y = y2;
		if (x2 % 3 == 0)
			passageTangInter.add(new Point(x2, y2));

	}

	public ArrayList<Point> getPassage() {
		return this.passage;
	}

	/**
	 * Permet d'effacer les trajectoires retenues en memoire.
	 */
	public void effacerTrajectoire() {
		passage.clear();
	}

	public void effacerTrajectoireTangeante() {
		passageTangInter.clear();
	}

	public boolean getTrajectoire() {
		return trajectoire;
	}

	public void setTrajectoire(boolean trajectoire) {
		this.trajectoire = trajectoire;
	}

	public Point getDepart() {
		return depart;
	}

	public void setDepart(Point depart) {
		this.depart = depart;
	}

	public int getScore() {
		return score;
	}

	public void addToScore(int i) {
		this.score += i;
	}

	public boolean isVictory() {
		return victory;
	}

	public void setVictory(boolean victory) {
		this.victory = victory;
	}

	public Point getArrivee() {
		return arrivee;
	}

	public void setArrivee(Point arrivee) {
		this.arrivee = arrivee;
	}

	public double getVitesse() {
		return vitesse;
	}

	public void setVitesse(double d) {
		this.vitesse = d;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double distance(double x1, double y1, double x2, double y2) {
		return Math.round(Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1)
				* (x2 - x1)));
	}
}
