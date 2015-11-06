import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Affichage extends JPanel {
	Oiseau a;
	ArrayList<Obstacle> listeObstacle;

	public Affichage(Oiseau a, ArrayList<Obstacle> listeObstacle) {
		this.a = a;
		this.listeObstacle = listeObstacle;
		this.setSize(Constantes.LARGEUR_ECRAN, Constantes.HAUTEUR_ECRAN);

	}

	public void paintComponent(Graphics g) {

		// Dessin du background
		g.setColor(Constantes.COULEUR_BACKGROUND);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// dessin de la trajectoire passé
		if (Constantes.TRAJECTOIRES) {
			g.setColor(Constantes.COULEUR_TRAJECTOIRE);

			// dessin de la trajectoire
			for (int i = 0; i < a.passage.size(); i++) {
				g.fillOval(a.passage.get(i).x + a.taille / 2, a.passage.get(i).y + a.taille / 2, 3, 3);
				// g.drawLine(c.x+(taille/2),c.y+(taille/2), c2.x+(taille/2),
				// c2.y+(taille/2));

			}
		}

		// dessin du triangle
		g.setColor(Color.orange);
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
		g.fillPolygon(triangle2);

		// dessin de l'oiseau
		g.setColor(Color.RED);
		g.fillOval(a.c.x, a.c.y, a.taille, a.taille);

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

}