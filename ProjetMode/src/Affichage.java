import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Affichage extends JPanel {
	int couleur =0;
	Oiseau a;
	ArrayList<Obstacle> listeObstacle;

	public Affichage(Oiseau a, ArrayList<Obstacle> listeObstacle, int i) {
		this.a = a;
		this.listeObstacle = listeObstacle;
		this.setSize(Constantes.LARGEUR_ECRAN, Constantes.HAUTEUR_ECRAN);
		if (i%1==0){
			couleur =0;
		} else { 
			couleur =1;
		}

	}
	
	Point[] p = new Point[3];
	public void CreerTriangle(int x1, int y1, int x2, int y2, int x3, int y3)
	{
	p[0] = new Point(x1, y1);
	p[1] = new Point(x2, y2);
	p[2] = new Point(x3, y3);
	}

	public void paintComponent(Graphics g) {

		// Dessin du background
		g.setColor(Constantes.COULEUR_BACKGROUND);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// dessin de la trajectoire pass�
		if (Constantes.TRAJECTOIRES) {
			g.setColor(Constantes.COULEUR_TRAJECTOIRE);

			// dessin de la trajectoire
			for (int i = 0; i < a.passage.size(); i++) {
				g.fillOval(a.passage.get(i).x + a.taille / 2, a.passage.get(i).y + a.taille / 2, 3, 3);
				// g.drawLine(c.x+(taille/2),c.y+(taille/2), c2.x+(taille/2), c2.y+(taille/2));

			}
			
			/*for (int i = 0; i < a.passageTang.size()-1; i++) {
				g.drawLine(a.passageTang.get(i).x+a.taille/2, a.passageTang.get(i).y+a.taille/2, a.passageTang.get(i+1).x+a.taille/2, a.passageTang.get(i+1).y+a.taille/2);
			}*/
			
		}

		// dessin du triangle
		
		
		/*g.setColor(Color.orange);
		int[] x = { a.c.x + a.getTaille() / 2, a.c.x + a.getTaille() / 2, a.c.x + a.getTaille() + a.getTaille() };
		int[] y = { a.c.y + a.getTaille(), a.c.y, a.c.y + a.getTaille() / 2 };
		Polygon triangle = new Polygon(x, y, 3);
		AffineTransform at = new AffineTransform();
		double angle = getAngle(a.c, a.c2);
		System.out.println(angle);
		at.rotate(angle, a.getC().getX(), a.getC().getY());

		Polygon triangle2 = new Polygon();
		for (int i = 0; i < triangle.npoints; i++) {
			Point p = new Point(triangle.xpoints[i], triangle.ypoints[i]);
			at.transform(p, p);
			triangle2.addPoint(p.x, p.y);
		}
		g.fillPolygon(triangle2);*/
		
		CreerTriangle(a.c.x+a.getTaille()/2, a.c.y+a.getTaille(), a.c.x+a.getTaille()/2, a.c.y, (a.c2.x-50)+a.getTaille()+a.getTaille()/2, a.c2.y+a.getTaille()/2);
		int[] px = { p[0].x, p[1].x, p[2].x };
		int[] py = { p[0].y, p[1].y, p[2].y };
		g.setColor(Constantes.COULEUR_BEC);
		g.fillPolygon(px, py, 3);
		

		// dessin de l'oiseau
		if (couleur == 0){
		g.setColor(Color.RED);
		} else {
			g.setColor(Color.CYAN);
		}
		g.fillOval(a.c.x, a.c.y, a.taille, a.taille);
		g.setColor(Color.WHITE);
		g.fillOval(a.c.x+23, a.c.y+8, 12, 12);
		g.setColor(Color.BLACK);
		g.fillOval(a.c.x+27, a.c.y+11, 6, 6);

		// Dessin des obstacles

		for (Obstacle o : listeObstacle) {

			if (o.getActif()) {
				g.setColor(Constantes.COULEUR_OBSTACLE);
				g.drawOval(o.getC().x, o.getC().y, o.getTaille(), o.getTaille());
			} else {
				g.setColor(Constantes.COULEUR_OBSTACLE_TOUCHE);
				g.fillOval(o.getC().x, o.getC().y, o.getTaille(), o.getTaille());
			}

			// g.drawPolygon(triangle); // affichage
			g.setColor(Color.black);
		}

	}

	public double getAngle(Point c, Point c2) {
		double xDiff = c2.x - c.x;
		double yDiff = c2.y - c.y;
		return Math.toDegrees(Math.atan2(yDiff, xDiff)); 

	}
	
	public void modifIntCouleur (int i){
		couleur=i;
	}

}