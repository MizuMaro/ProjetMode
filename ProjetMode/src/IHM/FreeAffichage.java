
package IHM;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

import Element.Constantes;
import Element.Images;
import Element.Sound;
import MVC_free.FreeModel;
import ObstacleFactory.Obstacle;

/**
 * La classe FreeAffichage permet l'affichage du mode creatif.
 * Voir la javadoc de la classe Affichage pour plus d'informations.
 * @author Rémy
 *
 */
@SuppressWarnings("serial")
public class FreeAffichage extends Affichage {

	private FreeModel m;
	private Font font;

	public FreeAffichage(FreeModel m){
		super(m.getOiseau(),m.getListObstacles());
		this.m=m;
		super.setSize(Constantes.getInstance().TAILLE_ECRAN[0], Constantes.getInstance().TAILLE_ECRAN[1]);

		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,new File("docs/font/font.ttf")));
			font = new Font("AngryBirds",Font.BOLD,40);
		} catch (IOException|FontFormatException e) {
			System.err.println(e.getMessage());
		}
	}

	public void paintComponent(Graphics g) {

		if(m.getOiseau().isVictory()){
			System.exit(0);
		}
		
		g.setFont(font);

		// Dessin du background
		g.drawImage(Images.getInstance().FREE_BACKGROUND, 0, 0, Constantes.getInstance().TAILLE_ECRAN[0], Constantes.getInstance().TAILLE_ECRAN[1], null);

		// dessin des trajectoires des obstacles qui bougent		
		g.setColor(Color.GREEN);

		for(Entry<Obstacle,Point[]> entry : m.getTrajecs().entrySet()){
			g.fillOval(entry.getValue()[0].x, entry.getValue()[0].y, 15, 15);
			g.fillOval(entry.getValue()[1].x, entry.getValue()[1].y, 15, 15);

			g.drawLine(entry.getValue()[0].x+7, entry.getValue()[0].y+7, entry.getValue()[1].x+9, entry.getValue()[1].y+9);
			g.drawLine(entry.getValue()[0].x+8, entry.getValue()[0].y+8, entry.getValue()[1].x+8, entry.getValue()[1].y+8);
			g.drawLine(entry.getValue()[0].x+8, entry.getValue()[0].y+8, entry.getValue()[1].x+8, entry.getValue()[1].y+8);
			g.drawLine(entry.getValue()[0].x+9, entry.getValue()[0].y+9, entry.getValue()[1].x+7, entry.getValue()[1].y+7);
		}

		// fronde
		if (a.getC().x < Constantes.getInstance().COORDONNEES_ORIGINE.x) {
			g.setColor(Color.BLACK);
			g.drawLine(a.getC().x + Constantes.getInstance().TAILLE_OISEAU / 2, a.getC().y + Constantes.getInstance().TAILLE_OISEAU / 2, 200,
					350);
			g.drawLine(a.getC().x + Constantes.getInstance().TAILLE_OISEAU / 2, a.getC().y + Constantes.getInstance().TAILLE_OISEAU / 2, 150,
					350);
		}

		// lance-pierres (rapport d'echelle = 2.487)
		g.drawImage(Images.getInstance().SLINGSHOT, Constantes.getInstance().COORDONNEES_ORIGINE.x - 30, 327, 90, 200, null);

		
		// dessin de la trajectoire 

		// position de depart
		g.setColor(Constantes.getInstance().COULEUR_TRAJECTOIRE1);
		g.fillOval(a.getDepart().x+Constantes.getInstance().TAILLE_OISEAU/2-5, a.getDepart().y+Constantes.getInstance().TAILLE_OISEAU/2, 8, 8);
		g.setColor(Constantes.getInstance().COULEUR_TRAJECTOIRE2);
		g.drawOval(a.getDepart().x+Constantes.getInstance().TAILLE_OISEAU/2-5, a.getDepart().y+Constantes.getInstance().TAILLE_OISEAU/2, 8, 8);

		// position d'arrivee
		g.setColor(Constantes.getInstance().COULEUR_TRAJECTOIRE1);
		g.fillOval(a.getArrivee().x+Constantes.getInstance().TAILLE_OISEAU/2, a.getArrivee().y+Constantes.getInstance().TAILLE_OISEAU/2, 8, 8);
		g.setColor(Constantes.getInstance().COULEUR_TRAJECTOIRE2);
		g.drawOval(a.getArrivee().x+Constantes.getInstance().TAILLE_OISEAU/2, a.getArrivee().y+Constantes.getInstance().TAILLE_OISEAU/2, 8, 8);

		for (int i = 0; i < a.getPassage().size(); i++) {
			g.setColor(Constantes.getInstance().COULEUR_TRAJECTOIRE1);
			g.fillOval(a.getPassage().get(i).x + a.getTaille() / 2, a.getPassage().get(i).y + a.getTaille() / 2,
					3, 3);

			g.setColor(Constantes.getInstance().COULEUR_TRAJECTOIRE2);
			g.drawOval(a.getPassage().get(i).x + a.getTaille() / 2, a.getPassage().get(i).y + a.getTaille() / 2,
					3, 3);
		}

		// Dessin des obstacles
		for (Obstacle o : super.listeObstacle) {

			g.setColor(Constantes.getInstance().COULEUR_OBSTACLE_TOUCHE);

			if (o.isActif()) {
				if (o.isCarre()) {
					g.drawImage(Images.getInstance().OBSTACLE, o.getC().x, o.getC().y, Constantes.getInstance().TAILLE_OBSTACLES,
							Constantes.getInstance().TAILLE_OBSTACLES, null);
				} else {
					g.drawImage(Images.getInstance().CAISSE_RONDE, o.getC().x, o.getC().y, Constantes.getInstance().TAILLE_OBSTACLES,
							Constantes.getInstance().TAILLE_OBSTACLES, null);
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
			g.setColor(Constantes.getInstance().COULEUR_OISEAU);
		} else {
			g.setColor(Constantes.getInstance().COULEUR_OISEAU_TOUCHE);
		}

		g.fillOval(a.getC().x, a.getC().y, a.getTaille(), a.getTaille());

		// dessin du bec
		CreerTriangle(a.getC().x + a.getTaille() / 2, a.getC().y + a.getTaille(), a.getC().x + a.getTaille() / 2,
				a.getC().y, (a.getC2().x - 50) + a.getTaille() + a.getTaille() / 2,
				a.getC2().y + a.getTaille() / 2);
		int[] px = { p[0].x, p[1].x, p[2].x };
		int[] py = { p[0].y, p[1].y, p[2].y };
		g.fillPolygon(px, py, 3);

		g.drawImage(Images.getInstance().BIRD, a.getC().x, a.getC().y, Constantes.getInstance().TAILLE_OISEAU, Constantes.getInstance().TAILLE_OISEAU, null);

		// lance-pierres
		g.drawImage(Images.getInstance().SLINGSHOT_UP, Constantes.getInstance().COORDONNEES_ORIGINE.x - 30, 327, 90, 200, null);


		// fond du menu
		g.setColor(Color.BLUE);
		g.fill3DRect(10, 30, 70, 280, true);

		// selection actuelle
		g.setColor(Color.RED);
		if(m.isCarre()){
			g.fill3DRect(20, 45, Constantes.getInstance().TAILLE_OBSTACLES+10, Constantes.getInstance().TAILLE_OBSTACLES+10, true);
		}else if(m.isRond()){
			g.fill3DRect(20, 105, Constantes.getInstance().TAILLE_OBSTACLES+10, Constantes.getInstance().TAILLE_OBSTACLES+10, true);
		}else if(m.isCarre_bouge()){
			g.fill3DRect(20, 165, Constantes.getInstance().TAILLE_OBSTACLES+10, Constantes.getInstance().TAILLE_OBSTACLES+20, true);
		}else if(m.isRond_bouge()){
			g.fill3DRect(20, 235, Constantes.getInstance().TAILLE_OBSTACLES+10, Constantes.getInstance().TAILLE_OBSTACLES+20, true);
		}

		// menu de selection des items
		g.drawImage(Images.getInstance().OBSTACLE, 25, 50, Constantes.getInstance().TAILLE_OBSTACLES, Constantes.getInstance().TAILLE_OBSTACLES, null);
		g.drawImage(Images.getInstance().CAISSE_RONDE, 25, 110, Constantes.getInstance().TAILLE_OBSTACLES, Constantes.getInstance().TAILLE_OBSTACLES, null);
		g.drawImage(Images.getInstance().CAISSE_BOUGE, 25, 170, Constantes.getInstance().TAILLE_OBSTACLES, Constantes.getInstance().TAILLE_OBSTACLES+10, null);
		g.drawImage(Images.getInstance().ROND_BOUGE, 25, 240, Constantes.getInstance().TAILLE_OBSTACLES, Constantes.getInstance().TAILLE_OBSTACLES+10, null);


		// nombre d'obstacles
		g.setColor(Color.BLACK);

		if(this.m.getCptObstacles() == 0){
			g.drawString(String.valueOf("Aucun obstacle"), 95, 70);
			g.setColor(Color.WHITE);
			g.drawString(String.valueOf("Aucun obstacle"), 90, 65);

		}else if(this.m.getCptObstacles() == 1){
			g.drawString(String.valueOf(this.m.getCptObstacles() + " obstacle"), 95, 70);
			g.setColor(Color.WHITE);
			g.drawString(String.valueOf(this.m.getCptObstacles() + " obstacle"), 90, 65);

		}else{
			g.drawString(String.valueOf(this.m.getCptObstacles() + " obstacles"), 95, 70);
			g.setColor(Color.WHITE);
			g.drawString(String.valueOf(this.m.getCptObstacles() + " obstacles"), 90, 65);

		}

		// Score
		g.setColor(Color.BLACK);
		if(a.getScore() <= 1){
			g.drawString("Score : " + a.getScore() + " point", 95, 120);
			g.setColor(Color.WHITE);
			g.drawString("Score : " + a.getScore() + " point", 90, 115);
		}else{
			g.drawString("Score : " + a.getScore() + " points", 95, 120);
			g.setColor(Color.WHITE);
			g.drawString("Score : " + a.getScore() + " points", 90, 115);
		}

		if(m.getOiseau().getScore() != 0 && m.getOiseau().getScore() == m.getCptObstacles()){
			m.getOiseau().setVictory(true);
			Sound.getInstance().playVictory();
			g.drawImage(Images.getInstance().VICTORY, 0, 0, 1200, 610, null);


		}

	}

}