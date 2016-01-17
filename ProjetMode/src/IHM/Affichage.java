package IHM;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;

import Element.Constantes;
import Element.Images;
import Element.Oiseau;
import Element.Sound;
import MVC.Model;
import ObstacleFactory.Obstacle;

/**
 * La classe Affichage permet un retour graphique de l'execution du programme.
 * @author Rémy
 *
 */
@SuppressWarnings("serial")
public class Affichage extends JPanel {

	protected Oiseau a;
	protected ArrayList<Obstacle> listeObstacle;
	protected boolean collision = false;
	protected Point[] p = new Point[3];
	private Font font;

	/**
	 * permet d'instancier un objet Affichage.
	 * @param a Oiseau a passer.
	 * @param listeObstacle Liste des obstacles a passer.
	 */
	public Affichage(Oiseau a, ArrayList<Obstacle> listeObstacle) {
		this.a = a;
		this.listeObstacle = listeObstacle;
		this.setSize(Constantes.getInstance().TAILLE_ECRAN[0], Constantes.getInstance().TAILLE_ECRAN[1]);

		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,new File("docs/font/font.ttf")));
			font = new Font("AngryBirds",Font.BOLD,40);
		} catch (IOException|FontFormatException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * methode principale qui permet le repaint de l'affichage.
	 */
	public void paintComponent(Graphics g) {

		if (Model.debug) {

			// Dessin du background
			g.setColor(Constantes.getInstance().COULEUR_BACKGROUND);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			// dessin de la trajectoire 

			// position de depart
			g.setColor(Constantes.getInstance().COULEUR_TRAJECTOIRE1);
			g.fillOval(a.getDepart().x+Constantes.getInstance().TAILLE_OISEAU/2-5, a.getDepart().y+Constantes.getInstance().TAILLE_OISEAU/2, 8, 8);
			g.setColor(Constantes.getInstance().COULEUR_TRAJECTOIRE2);
			g.drawOval(a.getDepart().x+Constantes.getInstance().TAILLE_OISEAU/2-5, a.getDepart().y+Constantes.getInstance().TAILLE_OISEAU/2, 8, 8);

			// position d'arrivee
			g.setColor(Constantes.getInstance().COULEUR_TRAJECTOIRE1);
			g.fillOval(a.getArrivee().x+Constantes.getInstance().TAILLE_OISEAU/2, a.getArrivee().y+Constantes.getInstance().TAILLE_OISEAU/2, 8, 8);
			g.setColor(Constantes.getInstance().COULEUR_TRAJECTOIRE2);
			g.drawOval(a.getArrivee().x+Constantes.getInstance().TAILLE_OISEAU/2, a.getArrivee().y+Constantes.getInstance().TAILLE_OISEAU/2, 8, 8);

			for (int i = 0; i < a.getPassage().size(); i++) {
				g.setColor(Constantes.getInstance().COULEUR_TRAJECTOIRE1);
				g.fillOval(a.getPassage().get(i).x + a.getTaille() / 2, a.getPassage().get(i).y + a.getTaille() / 2,
						3, 3);

				g.setColor(Constantes.getInstance().COULEUR_TRAJECTOIRE2);
				g.drawOval(a.getPassage().get(i).x + a.getTaille() / 2, a.getPassage().get(i).y + a.getTaille() / 2,
						3, 3);
			}


			// position de depart
			g.setColor(Color.BLUE);
			g.fillOval(Constantes.getInstance().COORDONNEES_ORIGINE.x, Constantes.getInstance().COORDONNEES_ORIGINE.y, Constantes.getInstance().TAILLE_OISEAU,
					Constantes.getInstance().TAILLE_OISEAU);
			// position pour le calcul de l'angle de depart

			g.fillOval(Constantes.getInstance().COORDONNEES_ORIGINE.x-100, Constantes.getInstance().COORDONNEES_ORIGINE.y, Constantes.getInstance().TAILLE_OISEAU,
					Constantes.getInstance().TAILLE_OISEAU);

			// position pour le calcul de l'angle de depart
			g.fillOval(Constantes.getInstance().COORDONNEES_ORIGINE.x-100, Constantes.getInstance().COORDONNEES_ORIGINE.y, Constantes.getInstance().TAILLE_OISEAU,
					Constantes.getInstance().TAILLE_OISEAU);
			g.setColor(Color.red);
			g.drawLine(Constantes.getInstance().COORDONNEES_ORIGINE.x+Constantes.getInstance().TAILLE_OISEAU/2, Constantes.getInstance().COORDONNEES_ORIGINE.y + Constantes.getInstance().TAILLE_OISEAU/2, 
					Constantes.getInstance().COORDONNEES_ORIGINE.x-100 + Constantes.getInstance().TAILLE_OISEAU/2, Constantes.getInstance().COORDONNEES_ORIGINE.y + Constantes.getInstance().TAILLE_OISEAU/2);

			// orientation de la courbe de lancer
			if (Constantes.getInstance().DISTANCE && !a.getVole()) {
				g.setColor(Color.GREEN);
				g.drawLine(Constantes.getInstance().COORDONNEES_ORIGINE.x + Constantes.getInstance().TAILLE_OISEAU / 2,
						Constantes.getInstance().COORDONNEES_ORIGINE.y + Constantes.getInstance().TAILLE_OISEAU / 2,
						a.getC().x + Constantes.getInstance().TAILLE_OISEAU / 2, a.getC().y + Constantes.getInstance().TAILLE_OISEAU / 2);
			}

			// droite entre le point d'horizontale et l'oiseau
			if(!a.getVole()){
				g.setColor(Color.YELLOW);
				g.drawLine(a.getC().x + Constantes.getInstance().TAILLE_OISEAU / 2, a.getC().y + Constantes.getInstance().TAILLE_OISEAU / 2, 
						Constantes.getInstance().COORDONNEES_ORIGINE.x-100 + Constantes.getInstance().TAILLE_OISEAU/2, Constantes.getInstance().COORDONNEES_ORIGINE.y + Constantes.getInstance().TAILLE_OISEAU/2);
			}

			// dessin du bec

			CreerTriangle(a.getC().x + a.getTaille() / 2, a.getC().y + a.getTaille(), a.getC().x + a.getTaille() / 2,
					a.getC().y, (a.getC2().x - 50) + a.getTaille() + a.getTaille() / 2,
					a.getC2().y + a.getTaille() / 2);
			int[] px = { p[0].x, p[1].x, p[2].x };
			int[] py = { p[0].y, p[1].y, p[2].y };
			g.setColor(Constantes.getInstance().COULEUR_BEC);
			g.fillPolygon(px, py, 3);

			// Dessin du sol
			g.setColor(Constantes.getInstance().COULEUR_SOL);
			g.drawLine(0, Constantes.getInstance().HAUTEUR_SOL, Constantes.getInstance().TAILLE_ECRAN[0], Constantes.getInstance().HAUTEUR_SOL);

			// Dessin des obstacles
			for (Obstacle o : listeObstacle) {

				if (o.isActif()) {
					g.setColor(Constantes.getInstance().COULEUR_OBSTACLE);
				} else {
					g.setColor(Constantes.getInstance().COULEUR_OBSTACLE_TOUCHE);
				}

				if (!o.isCarre()) {
					g.drawOval(o.getC().x, o.getC().y, o.getTaille(), o.getTaille());
				} else {
					g.drawRect(o.getC().x, o.getC().y, o.getTaille(), o.getTaille());
				}

			}

			// dessin de l'oiseau
			if (!collision) {
				g.setColor(Constantes.getInstance().COULEUR_OISEAU);
			} else {
				g.setColor(Constantes.getInstance().COULEUR_OISEAU_TOUCHE);
			}

			g.fillOval(a.getC().x, a.getC().y, a.getTaille(), a.getTaille());
			g.setColor(Color.WHITE);
			g.fillOval(a.getC().x + 23, a.getC().y + 8, 12, 12);
			g.setColor(Color.BLACK);
			g.fillOval(a.getC().x + 27, a.getC().y + 11, 6, 6);

			// Indications
			g.setColor(Color.white);
			g.drawString("Le rayon de battement est actuellement contraint a " + Constantes.getInstance().RAYON_DEPART + " pixels.",
					10, 20);
			/*
			if (distance(Constantes.getInstance().COORDONNEES_ORIGINE.x + Constantes.getInstance().TAILLE_OISEAU / 2,
					Constantes.getInstance().COORDONNEES_ORIGINE.y + Constantes.getInstance().TAILLE_OISEAU / 2,
					a.getC().x + Constantes.getInstance().TAILLE_OISEAU / 2,
					a.getC().y + Constantes.getInstance().TAILLE_OISEAU / 2) > Constantes.getInstance().RAYON_DEPART) {
				g.setColor(Color.red);
			}

			g.drawString("La valeur actuelle de celui-ci est de "
					+ (int) distance(Constantes.getInstance().COORDONNEES_ORIGINE.x + Constantes.getInstance().TAILLE_OISEAU / 2,
							Constantes.getInstance().COORDONNEES_ORIGINE.y + Constantes.getInstance().TAILLE_OISEAU / 2,
							a.getC().x + Constantes.getInstance().TAILLE_OISEAU / 2, a.getC().y + Constantes.getInstance().TAILLE_OISEAU / 2)
					+ " pixels.", 10, 40);
			 */

			// distances
			double aa = a.distance(a.getC().x + Constantes.getInstance().TAILLE_OISEAU/2, a.getC().y + Constantes.getInstance().TAILLE_OISEAU/2,
					Constantes.getInstance().COORDONNEES_ORIGINE.x-100 + Constantes.getInstance().TAILLE_OISEAU/2, Constantes.getInstance().COORDONNEES_ORIGINE.y + Constantes.getInstance().TAILLE_OISEAU/2);
			double cc = a.distance(Constantes.getInstance().COORDONNEES_ORIGINE.x + Constantes.getInstance().TAILLE_OISEAU / 2,
					Constantes.getInstance().COORDONNEES_ORIGINE.y + Constantes.getInstance().TAILLE_OISEAU / 2, 
					Constantes.getInstance().COORDONNEES_ORIGINE.x-100 + Constantes.getInstance().TAILLE_OISEAU/2, 
					Constantes.getInstance().COORDONNEES_ORIGINE.y + Constantes.getInstance().TAILLE_OISEAU/2);
			double bb = a.distance(Constantes.getInstance().COORDONNEES_ORIGINE.x + Constantes.getInstance().TAILLE_OISEAU / 2,
					Constantes.getInstance().COORDONNEES_ORIGINE.y + Constantes.getInstance().TAILLE_OISEAU / 2, 
					a.getC().x+ Constantes.getInstance().TAILLE_OISEAU / 2, a.getC().y+ Constantes.getInstance().TAILLE_OISEAU / 2);
			
					
			g.setColor(Color.GREEN);
			g.drawString(String.valueOf(bb), 350, 70);

			g.setColor(Color.RED);
			g.drawString(String.valueOf(cc), 400, 70);

			g.setColor(Color.YELLOW);
			g.drawString(String.valueOf(aa), 450, 70);


			// position de depart

			g.setColor(Color.red);
			g.drawLine(Constantes.getInstance().COORDONNEES_ORIGINE.x+Constantes.getInstance().TAILLE_OISEAU/2, Constantes.getInstance().COORDONNEES_ORIGINE.y + Constantes.getInstance().TAILLE_OISEAU/2, 
					Constantes.getInstance().COORDONNEES_ORIGINE.x-100 + Constantes.getInstance().TAILLE_OISEAU/2, Constantes.getInstance().COORDONNEES_ORIGINE.y + Constantes.getInstance().TAILLE_OISEAU/2);

			// orientation de la courbe de lancer
			if (Constantes.getInstance().DISTANCE && !a.getVole()) {
				g.setColor(Color.GREEN);
				g.drawLine(Constantes.getInstance().COORDONNEES_ORIGINE.x + Constantes.getInstance().TAILLE_OISEAU / 2,
						Constantes.getInstance().COORDONNEES_ORIGINE.y + Constantes.getInstance().TAILLE_OISEAU / 2,
						a.getC().x + Constantes.getInstance().TAILLE_OISEAU / 2, a.getC().y + Constantes.getInstance().TAILLE_OISEAU / 2);
			}


			g.setColor(Color.WHITE);
			// vitesse de l'oiseau
			g.drawString("La valeur de la vitesse de l'oiseau est de " + a.getVitesse() + " pixels.", 10, 50);
			g.drawString("L'angle de lancer est de " + 
					
					// (b² + c² - a²)/(2*b*c)
					//(bb*bb + cc*cc - aa*aa) / (2*bb*cc)
					a.getAngle()
					
					+ " radians.", 10, 70);



			g.setColor(Color.WHITE);
			g.drawString("Pour repositionner l'oiseau sur la position d'origine, appuyez sur la touche <r>.", 10, 100);
			g.drawString("Pour passer du mode debug au mode graphique et inversement, appuyez sur la touche <g>.", 10, 120);


			// Coordonnees de l'oiseau
			g.drawString("(" + a.getC().x + "," + a.getC().y + ")", a.getC().x + 10, a.getC().y - 10);

			// Coordonnees de l'origine
			g.drawString("(" + Constantes.getInstance().COORDONNEES_ORIGINE.x + "," + Constantes.getInstance().COORDONNEES_ORIGINE.y + ")",
					Constantes.getInstance().COORDONNEES_ORIGINE.x + 10, Constantes.getInstance().COORDONNEES_ORIGINE.y - 10);

			// Score
			g.drawString("Score : " + a.getScore() + " points", 20, 140);



		} else {

			// Police d'ecriture
			g.setFont(font);

			// Dessin du background
			g.drawImage(Images.getInstance().BACKGROUND, 0, 0, Constantes.getInstance().TAILLE_ECRAN[0], Constantes.getInstance().TAILLE_ECRAN[1], null);


			// fronde
			if (a.getC().x < Constantes.getInstance().COORDONNEES_ORIGINE.x) {
				g.setColor(Color.BLACK);
				g.drawLine(a.getC().x + Constantes.getInstance().TAILLE_OISEAU / 2, a.getC().y + Constantes.getInstance().TAILLE_OISEAU / 2, 200,
						350);
				g.drawLine(a.getC().x + Constantes.getInstance().TAILLE_OISEAU / 2, a.getC().y + Constantes.getInstance().TAILLE_OISEAU / 2, 150,
						350);
			}

			// lance-pierres (rapport d'echelle = 2.487)
			g.drawImage(Images.getInstance().SLINGSHOT, Constantes.getInstance().COORDONNEES_ORIGINE.x - 30, 327, 90, 200, null);

			// dessin de la trajectoire

			// position de depart
			g.setColor(Constantes.getInstance().COULEUR_TRAJECTOIRE1);
			g.fillOval(a.getDepart().x+Constantes.getInstance().TAILLE_OISEAU/2-5, a.getDepart().y+Constantes.getInstance().TAILLE_OISEAU/2, 8, 8);
			g.setColor(Constantes.getInstance().COULEUR_TRAJECTOIRE2);
			g.drawOval(a.getDepart().x+Constantes.getInstance().TAILLE_OISEAU/2-5, a.getDepart().y+Constantes.getInstance().TAILLE_OISEAU/2, 8, 8);

			// position d'arrivee
			g.setColor(Constantes.getInstance().COULEUR_TRAJECTOIRE1);
			g.fillOval(a.getArrivee().x+Constantes.getInstance().TAILLE_OISEAU/2, a.getArrivee().y+Constantes.getInstance().TAILLE_OISEAU/2, 8, 8);
			g.setColor(Constantes.getInstance().COULEUR_TRAJECTOIRE2);
			g.drawOval(a.getArrivee().x+Constantes.getInstance().TAILLE_OISEAU/2, a.getArrivee().y+Constantes.getInstance().TAILLE_OISEAU/2, 8, 8);

			for (int i = 0; i < a.getPassage().size(); i++) {
				g.setColor(Constantes.getInstance().COULEUR_TRAJECTOIRE1);
				g.fillOval(a.getPassage().get(i).x + a.getTaille() / 2, a.getPassage().get(i).y + a.getTaille() / 2,
						3, 3);

				g.setColor(Constantes.getInstance().COULEUR_TRAJECTOIRE2);
				g.drawOval(a.getPassage().get(i).x + a.getTaille() / 2, a.getPassage().get(i).y + a.getTaille() / 2,
						3, 3);
			}

			// Dessin des obstacles
			for (Obstacle o : listeObstacle) {

				g.setColor(Constantes.getInstance().COULEUR_OBSTACLE_TOUCHE);

				if (o.isActif()) {
					if (o.isCarre()) {
						g.drawImage(Images.getInstance().OBSTACLE, o.getC().x, o.getC().y, Constantes.getInstance().TAILLE_OBSTACLES,
								Constantes.getInstance().TAILLE_OBSTACLES, null);
					} else {
						g.drawImage(Images.getInstance().CAISSE_RONDE, o.getC().x, o.getC().y, Constantes.getInstance().TAILLE_OBSTACLES,
								Constantes.getInstance().TAILLE_OBSTACLES, null);
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
				g.setColor(Constantes.getInstance().COULEUR_OISEAU);
			} else {
				g.setColor(Constantes.getInstance().COULEUR_OISEAU_TOUCHE);
			}

			g.fillOval(a.getC().x, a.getC().y, a.getTaille(), a.getTaille());

			// dessin du bec
			CreerTriangle(a.getC().x + a.getTaille() / 2, a.getC().y + a.getTaille(), a.getC().x + a.getTaille() / 2,
					a.getC().y, (a.getC2().x - 50) + a.getTaille() + a.getTaille() / 2,
					a.getC2().y + a.getTaille() / 2);
			int[] px = { p[0].x, p[1].x, p[2].x };
			int[] py = { p[0].y, p[1].y, p[2].y };
			g.fillPolygon(px, py, 3);

			g.drawImage(Images.getInstance().BIRD, a.getC().x, a.getC().y, Constantes.getInstance().TAILLE_OISEAU, Constantes.getInstance().TAILLE_OISEAU, null);

			// lance-pierres
			g.drawImage(Images.getInstance().SLINGSHOT_UP, Constantes.getInstance().COORDONNEES_ORIGINE.x - 30, 327, 90, 200, null);

			// Score
			g.setColor(Color.BLACK);
			if(a.getScore() <= 1){
				g.drawString("Score : " + a.getScore() + " point", 25, 55);
				g.setColor(Color.WHITE);
				g.drawString("Score : " + a.getScore() + " point", 20, 50);
			}else{
				g.drawString("Score : " + a.getScore() + " points", 25, 55);
				g.setColor(Color.WHITE);
				g.drawString("Score : " + a.getScore() + " points", 20, 50);
			}

		}

		if(a.getScore() != 0 && a.getScore() == this.listeObstacle.size()){
			a.setVictory(true);
			Sound.getInstance().playVictory();
			g.drawImage(Images.getInstance().VICTORY, 0, 0, 1200, 610, null);
			a.addToScore(-a.getScore());

			// reset du modele

		}
	}


	public void setCollision(boolean b) {
		this.collision = b;
	}

	public void CreerTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		p[0] = new Point(x1, y1);
		p[1] = new Point(x2, y2);
		p[2] = new Point(x3, y3);
	}



}