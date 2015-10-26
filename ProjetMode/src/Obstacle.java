import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Obstacle extends JPanel {
	int taille;
	Point c;
	boolean actif;

	public Obstacle(Point c) {
		this.taille = 80;
		this.c = c;
		this.actif = true;

	}

	public void setActif(boolean t) {
		this.actif = t;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public Point getC() {
		return c;
	}

	public void setC(int x, int y) {
		this.c.x = x;
		this.c.y = y;
	}
	//dessin de l'obstacle
	public void paintComponent(Graphics g) {
		if (actif)
			g.setColor(Color.GREEN);
		else
			g.setColor(Color.yellow);
		g.fillOval(c.x, c.y, taille, taille);
	}
}
