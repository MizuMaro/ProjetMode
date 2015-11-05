import java.awt.Color;
import java.awt.Graphics;
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

		// dessin de l'oiseau
		g.setColor(Color.RED);
		g.fillOval(a.c.x, a.c.y, a.taille, a.taille);

		// dessin de la tangente
		g.drawLine(a.c.x + (a.taille / 2), a.c.y + (a.taille / 2), a.c2.x + (a.taille), a.c2.y + (a.taille));
		// System.out.println("c.x= " + c.x + " c.y = " + c.y + " c2.x = " +
		// c2.x + " c2.y= " + c2.y);

		// Dessin des obstacles

		for (Obstacle o : listeObstacle) {

			if (o.getActif()) {
				g.setColor(Constantes.COULEUR_OBSTACLE);
				g.drawOval(o.getC().x, o.getC().y, o.getTaille(), o.getTaille());
			} else {
				g.setColor(Constantes.COULEUR_OBSTACLE_TOUCHE);
				g.fillOval(o.getC().x, o.getC().y, o.getTaille(), o.getTaille());
			}
		}

	}

}
