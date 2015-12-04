package IHM;
import Element.Constantes;
import Element.Oiseau;
import Element.Obstacle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;



@SuppressWarnings("serial")
public class Affichage extends JPanel {
	
	private Oiseau a;
	private ArrayList<Obstacle> listeObstacle;
	private boolean collision = false;

	public Affichage(Oiseau a, ArrayList<Obstacle> listeObstacle, int i) {
		this.a = a;
		this.listeObstacle = listeObstacle;
		this.setSize(Constantes.LARGEUR_ECRAN, Constantes.HAUTEUR_ECRAN);

	}
	
	private Point[] p = new Point[3];

	public void paintComponent(Graphics g) {

		// Dessin du background
		g.setColor(Constantes.COULEUR_BACKGROUND);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// dessin de la trajectoire passee
		if (Constantes.TRAJECTOIRES) {
			g.setColor(Constantes.COULEUR_TRAJECTOIRE);

			// dessin de la trajectoire
			for (int i = 0; i < a.getPassage().size(); i++) {
				g.fillOval(a.getPassage().get(i).x + a.getTaille() / 2, a.getPassage().get(i).y + a.getTaille() / 2, 3, 3);
			}	
			
		}

		//position de depart
		g.setColor(Color.BLUE);
		g.fillOval(Constantes.COORDONNEES_ORIGINE.x, Constantes.COORDONNEES_ORIGINE.y, Constantes.TAILLE_OISEAU, Constantes.TAILLE_OISEAU);
		
		//orientation de la courbe de lancer
		g.setColor(Color.WHITE);
		g.drawLine(Constantes.COORDONNEES_ORIGINE.x + Constantes.TAILLE_OISEAU/2, Constantes.COORDONNEES_ORIGINE.y + Constantes.TAILLE_OISEAU/2, a.getC().x+Constantes.TAILLE_OISEAU/2, a.getC().y+Constantes.TAILLE_OISEAU/2);
		
		//valeur du vecteur de lancer
		g.drawString(String.valueOf(distance(Constantes.COORDONNEES_ORIGINE.x + Constantes.TAILLE_OISEAU/2, Constantes.COORDONNEES_ORIGINE.y + Constantes.TAILLE_OISEAU/2, a.getC().x+Constantes.TAILLE_OISEAU/2, a.getC().y+Constantes.TAILLE_OISEAU/2)), a.getC().x+Constantes.TAILLE_OISEAU, a.getC().y+Constantes.TAILLE_OISEAU/2-20);
		
		//dessin du bec
		CreerTriangle(a.getC().x+a.getTaille()/2, a.getC().y+a.getTaille(), a.getC().x+a.getTaille()/2, a.getC().y, (a.getC2().x-50)+a.getTaille()+a.getTaille()/2, a.getC2().y+a.getTaille()/2);
		int[] px = { p[0].x, p[1].x, p[2].x };
		int[] py = { p[0].y, p[1].y, p[2].y };
		g.setColor(Constantes.COULEUR_BEC);
		g.fillPolygon(px, py, 3);
		
		
		// Dessin du sol
		g.setColor(Constantes.COULEUR_SOL);
		g.drawLine(0, Constantes.HAUTEUR_SOL, Constantes.LARGEUR_ECRAN, Constantes.HAUTEUR_SOL);
		

		// Dessin des obstacles
		for (Obstacle o : listeObstacle) {

			if (o.isActif()) {
				
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
				
				g.fillOval(a.getC().x, a.getC().y, a.getTaille(), a.getTaille());
				g.setColor(Color.WHITE);
				g.fillOval(a.getC().x+23, a.getC().y+8, 12, 12);
				g.setColor(Color.BLACK);
				g.fillOval(a.getC().x+27, a.getC().y+11, 6, 6);

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
	
	public double distance(double x1, double y1, double x2, double y2) {
        return Math.round(Math.sqrt((y2 - y1)*(y2 - y1) + (x2 - x1)*(x2 - x1)));
    }

}