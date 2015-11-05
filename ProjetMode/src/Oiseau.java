import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Oiseau extends JPanel {
	int taille;
	Point c;
	Point c2 = new Point();
	ArrayList<Point> passage = new ArrayList<>();

	// ajouter angle pour l'oiseau
	public Oiseau(Point c) {
		this.taille = Constantes.TAILLE_OISEAU;
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
		// pour afficher en pointillé
		if(x%3==0)
		passage.add(new Point(x, y));
	}
	public void setC2(int x2 , int y2){
		this.c2.x = x2;
		this.c2.y = y2;
		
	}
	
	public void effacerTrajectoire(){
		passage.clear();
	}
}
