import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Oiseau extends JPanel {
	int taille;
	Point c;
	ArrayList<Point> passage = new ArrayList<>();

	// ajouter angle pour l'oiseau
	public Oiseau(Point c) {
		this.taille = 40;
		this.c = c;

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
		passage.add(new Point(x, y));
	}
// dessin de l'oiseau
	public void paintComponent(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(c.x, c.y, taille, taille);
		g.setColor(Color.white);
		for (int i = 0; i < passage.size(); i++) {
			System.out.println(passage.get(i).x);
			g.fillOval(passage.get(i).x + taille / 2, passage.get(i).y + taille / 2, 3, 3);
		}
	}
}
