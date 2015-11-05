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
		passage.add(new Point(x, y));
	}
	public void setC2(int x2 , int y2){
		this.c2.x = x2;
		this.c2.y = y2;
		
	}

	public void paintComponent(Graphics g) {
		
		if(Constantes.TRAJECTOIRES){
			g.setColor(Constantes.COULEUR_TRAJECTOIRE);
			
			//dessin de la trajectoire
			for (int i = 0; i < passage.size(); i++) {
				g.fillOval(passage.get(i).x + taille / 2, passage.get(i).y + taille / 2, 3, 3);
				//g.drawLine(c.x+(taille/2),c.y+(taille/2),  c2.x+(taille/2), c2.y+(taille/2));
				
			}
		}
		
		//dessin de l'oiseau
		g.setColor(Color.RED);
		g.fillOval(c.x, c.y, taille, taille);
		
		//dessin de la tangente
		g.drawLine(c.x+(taille/2),c.y+(taille/2),  c2.x+(taille), c2.y+(taille));
		//System.out.println("c.x= " + c.x + " c.y = " + c.y + " c2.x = " + c2.x + " c2.y= " + c2.y);
		
	}
	
	public void effacerTrajectoire(){
		passage.clear();
	}
}
