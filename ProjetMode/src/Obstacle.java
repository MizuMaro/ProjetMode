import java.awt.Point;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Obstacle extends JPanel {
	int taille;
	Point c;
	boolean actif;

	public Obstacle(Point c) {
		this.taille = 50;
		this.c = c;
		this.actif = true;

	}

	public boolean getActif() {
		return this.actif;
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
}
