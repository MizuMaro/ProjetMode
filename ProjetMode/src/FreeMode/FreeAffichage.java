
package FreeMode;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map.Entry;

import Element.Constantes;
import Element.Images;
import IHM.Affichage;
import ObstacleFactory.Obstacle;

@SuppressWarnings("serial")
public class FreeAffichage extends Affichage {
	
	private HashMap<Point,Point> trajectoires;
	private FreeModel m;
	
	public FreeAffichage(FreeModel m){
		super(m.getOiseau(),m.getListObstacles());
		this.m=m;
		super.setSize(Constantes.TAILLE_ECRAN[0], Constantes.TAILLE_ECRAN[1]);
		this.trajectoires = m.getTrajectoires();
	}

	public void paintComponent(Graphics g) {

		// Dessin du background
		g.drawImage(Images.FREE_BACKGROUND, 0, 0, Constantes.TAILLE_ECRAN[0], Constantes.TAILLE_ECRAN[1], null);
		
		// dessin des trajectoires des obstacles qui bougent
		g.setColor(Color.GREEN);
		for(Entry<Point, Point> entry : trajectoires.entrySet()){
			
			g.fillOval(entry.getKey().x, entry.getKey().y, 15, 15);
			g.fillOval(entry.getValue().x, entry.getValue().y, 15, 15);
			
			g.drawLine(entry.getKey().x+7, entry.getKey().y+7,entry.getValue().x+9, entry.getValue().y+9);
			g.drawLine(entry.getKey().x+8, entry.getKey().y+8,entry.getValue().x+8, entry.getValue().y+8);
			g.drawLine(entry.getKey().x+8, entry.getKey().y+8,entry.getValue().x+8, entry.getValue().y+8);
			g.drawLine(entry.getKey().x+9, entry.getKey().y+9,entry.getValue().x+7, entry.getValue().y+7);
		}

		// dessin de la trajectoire passee
		if (Constantes.TRAJECTOIRES) {
			g.setColor(Constantes.COULEUR_TRAJECTOIRE);

			// dessin de la trajectoire
			for (int i = 0; i < super.a.getPassage().size(); i++) {
				g.fillOval(a.getPassage().get(i).x + a.getTaille() / 2, a.getPassage().get(i).y + a.getTaille() / 2,
						3, 3);
			}

		}

		// fronde
		if (a.getC().x < Constantes.COORDONNEES_ORIGINE.x) {
			g.setColor(Color.BLACK);
			g.drawLine(a.getC().x + Constantes.TAILLE_OISEAU / 2, a.getC().y + Constantes.TAILLE_OISEAU / 2, 200,
					350);
			g.drawLine(a.getC().x + Constantes.TAILLE_OISEAU / 2, a.getC().y + Constantes.TAILLE_OISEAU / 2, 150,
					350);
		}

		// lance-pierres (rapport d'echelle = 2.487)
		g.drawImage(Images.SLINGSHOT, Constantes.COORDONNEES_ORIGINE.x - 30, 327, 90, 200, null);

		// Dessin des obstacles
		for (Obstacle o : super.listeObstacle) {

			g.setColor(Constantes.COULEUR_OBSTACLE_TOUCHE);

			if (o.isActif()) {
				if (o.isCarre()) {
					g.drawImage(Images.OBSTACLE, o.getC().x, o.getC().y, Constantes.TAILLE_OBSTACLES,
							Constantes.TAILLE_OBSTACLES, null);
				} else {
					g.drawImage(Images.CAISSE_RONDE, o.getC().x, o.getC().y, Constantes.TAILLE_OBSTACLES,
							Constantes.TAILLE_OBSTACLES, null);
				}
			} else {
				if (o.isCarre()) {
					g.drawRect(o.getC().x, o.getC().y, o.getTaille(), o.getTaille());
				} else {
					g.drawOval(o.getC().x, o.getC().y, o.getTaille(), o.getTaille());
				}
			}
		}

		// dessin de l'oiseau
		if (!collision) {
			g.setColor(Constantes.COULEUR_OISEAU);
		} else {
			g.setColor(Constantes.COULEUR_OISEAU_TOUCHE);
		}

		g.fillOval(a.getC().x, a.getC().y, a.getTaille(), a.getTaille());

		// dessin du bec
		CreerTriangle(a.getC().x + a.getTaille() / 2, a.getC().y + a.getTaille(), a.getC().x + a.getTaille() / 2,
				a.getC().y, (a.getC2().x - 50) + a.getTaille() + a.getTaille() / 2,
				a.getC2().y + a.getTaille() / 2);
		int[] px = { p[0].x, p[1].x, p[2].x };
		int[] py = { p[0].y, p[1].y, p[2].y };
		g.fillPolygon(px, py, 3);

		g.drawImage(Images.BIRD, a.getC().x, a.getC().y, Constantes.TAILLE_OISEAU, Constantes.TAILLE_OISEAU, null);

		// lance-pierres
		g.drawImage(Images.SLINGSHOT_UP, Constantes.COORDONNEES_ORIGINE.x - 30, 327, 90, 200, null);
		
		
		// fond du menu
		g.setColor(Color.BLUE);
		g.fill3DRect(10, 30, 70, 280, true);
		
		// selection actuelle
		g.setColor(Color.RED);
		if(FreeVue.carre){
			g.fill3DRect(20, 45, Constantes.TAILLE_OBSTACLES+10, Constantes.TAILLE_OBSTACLES+10, true);
		}else if(FreeVue.rond){
			g.fill3DRect(20, 105, Constantes.TAILLE_OBSTACLES+10, Constantes.TAILLE_OBSTACLES+10, true);
		}else if(FreeVue.carre_bouge){
			g.fill3DRect(20, 165, Constantes.TAILLE_OBSTACLES+10, Constantes.TAILLE_OBSTACLES+20, true);
		}else if(FreeVue.rond_bouge){
			g.fill3DRect(20, 235, Constantes.TAILLE_OBSTACLES+10, Constantes.TAILLE_OBSTACLES+20, true);
		}
		
		// menu de selection des items
		g.drawImage(Images.OBSTACLE, 25, 50, Constantes.TAILLE_OBSTACLES, Constantes.TAILLE_OBSTACLES, null);
		g.drawImage(Images.CAISSE_RONDE, 25, 110, Constantes.TAILLE_OBSTACLES, Constantes.TAILLE_OBSTACLES, null);
		g.drawImage(Images.CAISSE_BOUGE, 25, 170, Constantes.TAILLE_OBSTACLES, Constantes.TAILLE_OBSTACLES+10, null);
		g.drawImage(Images.ROND_BOUGE, 25, 240, Constantes.TAILLE_OBSTACLES, Constantes.TAILLE_OBSTACLES+10, null);
		
		// nombre d'obstacles
		if(this.m.getCptObstacles() == 0 || this.m.getCptObstacles() == 1){
			g.drawString(String.valueOf(this.m.getCptObstacles() + " obstacle"), 13, 330);
		}else{
			g.drawString(String.valueOf(this.m.getCptObstacles() + " obstacles"), 13, 330);
		}

	}

	public double getAngle(Point c, Point c2) {
		return super.getAngle(c, c2);

	}

	public void setCollision(boolean b) {
		super.setCollision(b);
	}

	public void CreerTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		super.CreerTriangle(x1, y1, x2, y2, x3, y3);
	}

	public double distance(double x1, double y1, double x2, double y2) {
		return super.distance(x1, y1, x2, y2);
	}

	public double coeffDirecteur() {
		return super.coeffDirecteur();
	}


}