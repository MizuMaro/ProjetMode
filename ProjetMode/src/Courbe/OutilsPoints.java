package Courbe;

import java.awt.Point;

/**
 * La classe OutilsPoints permet aux utilisateurs d'obtenir des informations sur
 * un espace de plusieurs points telles que la distance ou l'angle formes par
 * plusieurs points.
 * 
 * @author Rémy
 *
 */
public class OutilsPoints {

	private Point p1;
	private Point p2;

	public OutilsPoints(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	/**
	 * retourne la distance entre les deux points passes en parametre.
	 * 
	 * @param p1
	 *            Premier point de la methode.
	 * @param p2
	 *            Deuxieme point.
	 * @return retourne la distance entre les deux points.
	 */
	public double getDistance(Point p1, Point p2) {
		return Math.round(Math.sqrt((p2.getY() - p1.getY())
				* (p2.getY() - p1.getY()) + (p2.getX() - p1.getX())
				* (p2.getX() - p1.getX())));
	}

	/**
	 * est sensee retourner l'angle forme entre trois points
	 * 
	 * @return l'angle entre les trois points.
	 */
	public double getAngle() {
		Point p3 = new Point((int) p2.getX(), (int) p2.getY());
		return Math.cos(getDistance(p2, p1) / getDistance(p2, p3));
	}
}
