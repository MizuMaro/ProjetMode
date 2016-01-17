package ObstacleFactory;

import java.awt.Point;


/**
 * La classe Obstacle est la classe dont va herité toutes les autres classes qui
 * seront obstacle 
 * Elle sert a la factory comme base.
 * @author Omar
 * 
 */
public abstract class Obstacle {
	protected int taille;
	protected Point c;
	protected boolean actif;
	protected boolean carre;
	protected boolean mouvement;
	protected String nom ;

	public abstract void setLimites_x(int[] a);
	public abstract void setLimites_y(int[] a);
	public abstract int[] getLimites_x();
	public abstract int[] getLimites_y();

	public abstract void moveX();
	public abstract void moveY();
	/**
	 * Cette fonction permet de savoir si l'obstacle a deja ete actife ou non.
	 * @return Si la fonction retourne vrai, l'obstacle peut etre actife par l'oiseau.
	 */
	public boolean isActif() {
		return this.actif;
	}

	/**
	 * Cette fonction permet de paramï¿½trer l'etat de l'obstacle.
	 * @param t Si vrai, l'obstacle sera sujet aux collisions avec l'oiseau ; sinon, non.
	 */
	public void setActif(boolean t) {
		this.actif = t;
	}
	/**
	 * Cette fonction permet de paramï¿½trer l'etat de l'obstacle.
	 * @param t Si vrai, l'obstacle sera sujet aux collisions avec l'oiseau ; sinon, non.
	 */
	public void setCarre(boolean t) {
		this.carre = t;
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
	 * Cette fonction permet d'obtenir les coordonnees de l'obstacle.
	 * @return Point contenant les coordonnees x et y de l'obstacle.
	 */
	public Point getC() {
		return c;
	}

	public String getNom() {
		return nom;
	}

	/**
	 * Cette fonction permet de parametrer les coordonnï¿½es de l'obstacle.
	 * @param x Coordonnee en x de l'obstacle.
	 * @param y Coordonnee en y de l'obstacle.
	 */
	public void setC(int x, int y) {
		this.c.x = x;
		this.c.y = y;
	}

	public boolean isCarre() {
		return this.carre;
	}
	
	public boolean isMouvement(){
		return this.mouvement;
	}
	
	
}