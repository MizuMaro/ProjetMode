package IHM;
import Element.Constantes;
import Element.Oiseau;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

import Element.Constantes;
import Element.Obstacle;
import Element.Oiseau;

@SuppressWarnings("serial")
public class Affichage extends JPanel {
	
	Oiseau a;
	ArrayList<Obstacle> listeObstacle;
	private boolean collision = false;

	public Affichage(Oiseau a, ArrayList<Obstacle> listeObstacle, int i) {
		this.a = a;
		this.listeObstacle = listeObstacle;
		this.setSize(Constantes.LARGEUR_ECRAN, Constantes.HAUTEUR_ECRAN);

	}
	
	Point[] p = new Point[3];

	public void paintComponent(Graphics g) {

		// Dessin du background
		g.setColor(Constantes.COULEUR_BACKGROUND);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// dessin de la trajectoire passee
		if (Constantes.TRAJECTOIRES) {
			g.setColor(Constantes.COULEUR_TRAJECTOIRE);

			// dessin de la trajectoire
			for (int i = 0; i < a.passage.size(); i++) {
				g.fillOval(a.passage.get(i).x + a.taille / 2, a.passage.get(i).y + a.taille / 2, 3, 3);
			}	
			
		}

		//dessin du bec
		CreerTriangle(a.c.x+a.getTaille()/2, a.c.y+a.getTaille(), a.c.x+a.getTaille()/2, a.c.y, (a.c2.x-50)+a.getTaille()+a.getTaille()/2, a.c2.y+a.getTaille()/2);
		int[] px = { p[0].x, p[1].x, p[2].x };
		int[] py = { p[0].y, p[1].y, p[2].y };
		g.setColor(Constantes.COULEUR_BEC);
		g.fillPolygon(px, py, 3);


		// Dessin des obstacles
		for (Obstacle o : listeObstacle) {

			if (o.getActif()) {
				g.setColor(Constantes.COULEUR_OBSTACLE);
				g.drawOval(o.getC().x, o.getC().y, o.getTaille(), o.getTaille());
			} else {
				g.setColor(Constantes.COULEUR_OBSTACLE_TOUCHE);
				g.drawOval(o.getC().x, o.getC().y, o.getTaille(), o.getTaille());
			}
		}
		
		// dessin de l'oiseau
				if (!collision){
					g.setColor(Constantes.COULEUR_OISEAU);
				} else {
					g.setColor(Constantes.COULEUR_OISEAU_TOUCHE);
				}
				
				g.fillOval(a.c.x, a.c.y, a.taille, a.taille);
				g.setColor(Color.WHITE);
				g.fillOval(a.c.x+23, a.c.y+8, 12, 12);
				g.setColor(Color.BLACK);
				g.fillOval(a.c.x+27, a.c.y+11, 6, 6);

	}

	public double getAngle(Point c, Point c2) {
		double xDiff = c2.x - c.x;
		double yDiff = c2.y - c.y;
		return Math.toDegrees(Math.atan2(yDiff, xDiff)); 

	}
	
	public void setCollision(boolean b){
		this.collision = b;
	}
	
	public void CreerTriangle(int x1, int y1, int x2, int y2, int x3, int y3){
		p[0] = new Point(x1, y1);
		p[1] = new Point(x2, y2);
		p[2] = new Point(x3, y3);
	}

}