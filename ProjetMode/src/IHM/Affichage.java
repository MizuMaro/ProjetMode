
package IHM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;

import Element.Constantes;
import Element.Images;
import Element.Oiseau;
import MVC.Model;
import ObstacleFactory.Obstacle;

@SuppressWarnings("serial")
public class Affichage extends JPanel {

	private Oiseau a;
	private ArrayList<Obstacle> listeObstacle;
	private boolean collision = false;
	private Point[] p = new Point[3];

	public Affichage(Oiseau a, ArrayList<Obstacle> listeObstacle) {
		//initimg();
		this.a = a;
		this.listeObstacle = listeObstacle;
		this.setSize(Constantes.TAILLE_ECRAN[0], Constantes.TAILLE_ECRAN[1]);
	}

	public void paintComponent(Graphics g) {

		if (Model.debug) {

			// Dessin du background
			g.setColor(Constantes.COULEUR_BACKGROUND);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			// dessin de la trajectoire passee
			if (Constantes.TRAJECTOIRES) {
				g.setColor(Constantes.COULEUR_TRAJECTOIRE);

				// dessin de la trajectoire
				for (int i = 0; i < a.getPassage().size(); i++) {
					g.fillOval(a.getPassage().get(i).x + a.getTaille() / 2, a.getPassage().get(i).y + a.getTaille() / 2,
							3, 3);
				}

			}

			// position de depart
			g.setColor(Color.BLUE);
			g.fillOval(Constantes.COORDONNEES_ORIGINE.x, Constantes.COORDONNEES_ORIGINE.y, Constantes.TAILLE_OISEAU,
					Constantes.TAILLE_OISEAU);

			// orientation de la courbe de lancer
			if (Constantes.DISTANCE) {
				g.setColor(Color.WHITE);
				g.drawLine(Constantes.COORDONNEES_ORIGINE.x + Constantes.TAILLE_OISEAU / 2,
						Constantes.COORDONNEES_ORIGINE.y + Constantes.TAILLE_OISEAU / 2,
						a.getC().x + Constantes.TAILLE_OISEAU / 2, a.getC().y + Constantes.TAILLE_OISEAU / 2);
			}

			// dessin du bec

			CreerTriangle(a.getC().x + a.getTaille() / 2, a.getC().y + a.getTaille(), a.getC().x + a.getTaille() / 2,
					a.getC().y, (a.getC2().x - 50) + a.getTaille() + a.getTaille() / 2,
					a.getC2().y + a.getTaille() / 2);
			int[] px = { p[0].x, p[1].x, p[2].x };
			int[] py = { p[0].y, p[1].y, p[2].y };
			g.setColor(Constantes.COULEUR_BEC);
			g.fillPolygon(px, py, 3);

			// Dessin du sol
			g.setColor(Constantes.COULEUR_SOL);
			g.drawLine(0, Constantes.HAUTEUR_SOL, Constantes.TAILLE_ECRAN[0], Constantes.HAUTEUR_SOL);

			// Dessin des obstacles
			for (Obstacle o : listeObstacle) {

				if (o.isActif()) {
					g.setColor(Constantes.COULEUR_OBSTACLE);
				} else {
					g.setColor(Constantes.COULEUR_OBSTACLE_TOUCHE);
				}

				if (!o.isCarre()) {
					g.drawOval(o.getC().x, o.getC().y, o.getTaille(), o.getTaille());
				} else {
					g.drawRect(o.getC().x, o.getC().y, o.getTaille(), o.getTaille());
				}

			}

			// dessin de l'oiseau
			if (!collision) {
				g.setColor(Constantes.COULEUR_OISEAU);
			} else {
				g.setColor(Constantes.COULEUR_OISEAU_TOUCHE);
			}

			g.fillOval(a.getC().x, a.getC().y, a.getTaille(), a.getTaille());
			g.setColor(Color.WHITE);
			g.fillOval(a.getC().x + 23, a.getC().y + 8, 12, 12);
			g.setColor(Color.BLACK);
			g.fillOval(a.getC().x + 27, a.getC().y + 11, 6, 6);

			// Indications
			g.setColor(Color.white);
			g.drawString("Le rayon de battement est actuellement contraint a " + Constantes.RAYON_DEPART + " pixels.",
					10, 20);
			if (distance(Constantes.COORDONNEES_ORIGINE.x + Constantes.TAILLE_OISEAU / 2,
					Constantes.COORDONNEES_ORIGINE.y + Constantes.TAILLE_OISEAU / 2,
					a.getC().x + Constantes.TAILLE_OISEAU / 2,
					a.getC().y + Constantes.TAILLE_OISEAU / 2) > Constantes.RAYON_DEPART) {
				g.setColor(Color.red);
			}
			g.drawString("La valeur actuelle de celui-ci est de "
					+ (int) distance(Constantes.COORDONNEES_ORIGINE.x + Constantes.TAILLE_OISEAU / 2,
							Constantes.COORDONNEES_ORIGINE.y + Constantes.TAILLE_OISEAU / 2,
							a.getC().x + Constantes.TAILLE_OISEAU / 2, a.getC().y + Constantes.TAILLE_OISEAU / 2)
					+ " pixels.", 10, 40);
			g.setColor(Color.WHITE);
			g.drawString("Pour repositionner l'oiseau sur la position d'origine, appuyez sur la touche <r>.", 10, 60);
			g.drawString("Pour passer du mode debug au mode graphique et inversement, appuyez sur la touche <g>.", 10, 80);

			// Coordonnees de l'oiseau
			g.drawString("(" + a.getC().x + "," + a.getC().y + ")", a.getC().x + 10, a.getC().y - 10);

			// Coordonnees de l'origine
			g.drawString("(" + Constantes.COORDONNEES_ORIGINE.x + "," + Constantes.COORDONNEES_ORIGINE.y + ")",
					Constantes.COORDONNEES_ORIGINE.x + 10, Constantes.COORDONNEES_ORIGINE.y - 10);

			// Equation de la droite
			/*
			g.drawString("(" + a.getC().y + " - " + Constantes.COORDONNEES_ORIGINE.y + ")", 45, 90);
			g.drawString("y = _________ = " + coeffDirecteur() + "x + b", 27, 94);
			g.drawString("(" + a.getC().x + " - " + Constantes.COORDONNEES_ORIGINE.x + ")", 45, 110);
			*/

		} else {

			// Dessin du background
			g.drawImage(Images.BACKGROUND, 0, 0, Constantes.TAILLE_ECRAN[0], Constantes.TAILLE_ECRAN[1], null);

			// dessin de la trajectoire passee
			if (Constantes.TRAJECTOIRES) {
				g.setColor(Constantes.COULEUR_TRAJECTOIRE);

				// dessin de la trajectoire
				for (int i = 0; i < a.getPassage().size(); i++) {
					g.fillOval(a.getPassage().get(i).x + a.getTaille() / 2, a.getPassage().get(i).y + a.getTaille() / 2,
							3, 3);
				}

			}

			// fronde
			if (a.getC().x < Constantes.COORDONNEES_ORIGINE.x) {
				g.setColor(Color.BLACK);
				g.drawLine(a.getC().x + Constantes.TAILLE_OISEAU / 2, a.getC().y + Constantes.TAILLE_OISEAU / 2, 200,
						350);
				g.drawLine(a.getC().x + Constantes.TAILLE_OISEAU / 2, a.getC().y + Constantes.TAILLE_OISEAU / 2, 150,
						350);
			}

			// lance-pierres (rapport d'echelle = 2.487)
			g.drawImage(Images.SLINGSHOT, Constantes.COORDONNEES_ORIGINE.x - 30, 327, 90, 200, null);

			// Dessin des obstacles
			for (Obstacle o : listeObstacle) {

				g.setColor(Constantes.COULEUR_OBSTACLE_TOUCHE);

				if (o.isActif()) {
					if (o.isCarre()) {
						g.drawImage(Images.OBSTACLE, o.getC().x, o.getC().y, Constantes.TAILLE_OBSTACLES,
								Constantes.TAILLE_OBSTACLES, null);
					} else {
						g.drawImage(Images.CAISSE_RONDE, o.getC().x, o.getC().y, Constantes.TAILLE_OBSTACLES,
								Constantes.TAILLE_OBSTACLES, null);
					}
				} else {
					if (o.isCarre()) {
						g.drawRect(o.getC().x, o.getC().y, o.getTaille(), o.getTaille());
					} else {
						g.drawOval(o.getC().x, o.getC().y, o.getTaille(), o.getTaille());
					}
				}
			}

			// dessin de l'oiseau
			if (!collision) {
				g.setColor(Constantes.COULEUR_OISEAU);
			} else {
				g.setColor(Constantes.COULEUR_OISEAU_TOUCHE);
			}

			g.fillOval(a.getC().x, a.getC().y, a.getTaille(), a.getTaille());

			// dessin du bec
			CreerTriangle(a.getC().x + a.getTaille() / 2, a.getC().y + a.getTaille(), a.getC().x + a.getTaille() / 2,
					a.getC().y, (a.getC2().x - 50) + a.getTaille() + a.getTaille() / 2,
					a.getC2().y + a.getTaille() / 2);
			int[] px = { p[0].x, p[1].x, p[2].x };
			int[] py = { p[0].y, p[1].y, p[2].y };
			g.fillPolygon(px, py, 3);

			g.drawImage(Images.BIRD, a.getC().x, a.getC().y, Constantes.TAILLE_OISEAU, Constantes.TAILLE_OISEAU, null);

			// lance-pierres
			g.drawImage(Images.SLINGSHOT_UP, Constantes.COORDONNEES_ORIGINE.x - 30, 327, 90, 200, null);

		}
	}

	public double getAngle(Point c, Point c2) {
		double xDiff = c2.x - c.x;
		double yDiff = c2.y - c.y;
		return Math.toDegrees(Math.atan2(yDiff, xDiff));

	}

	public void setCollision(boolean b) {
		this.collision = b;
	}

	public void CreerTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		p[0] = new Point(x1, y1);
		p[1] = new Point(x2, y2);
		p[2] = new Point(x3, y3);
	}

	public double distance(double x1, double y1, double x2, double y2) {
		return Math.round(Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)));
	}

	public double coeffDirecteur() {
		double d = (a.getC().y - Constantes.COORDONNEES_ORIGINE.y);
		double down = Math.abs(a.getC().x - Constantes.COORDONNEES_ORIGINE.x);

		if (down == 0) {
			return d;
		} else {
			return d / down;
		}
	}


}