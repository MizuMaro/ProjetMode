package MVC;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.event.MouseInputAdapter;

import Courbe.Courbe;
import Element.Constantes;
import Element.Sound;
import ObstacleFactory.Obstacle;

public class Controller {
	Model m ;
	Vue v= null;
	public Controller(Model m) {
		this.m=m;
	}
	public void setDrag(boolean b){
		m.setDrag(b);
	}
	public void setPositionOiseau(int x , int y){
		m.setPositionOiseau(x,y);
	}
	public void setPositionOiseauC2(int x , int y){
		m.setPositionOiseauC2(x,y);
	}

	public void setObstacles(ArrayList<Obstacle> obstacles) {
		this.m.setObstacles(obstacles);
	}
	public void repaint(){
		this.m.getAffichage().repaint();
	}

	public void addVue(Vue v){
		this.v = v;

		// Listener qui gere les actions au clavier
		v.getFenetre().addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// reset a la position d'origine de l'oiseau
				if (e.getKeyChar() == 'r') {
					setPositionOiseau(Constantes.getInstance().COORDONNEES_ORIGINE.x, Constantes.getInstance().COORDONNEES_ORIGINE.y);
					setPositionOiseauC2((int) m.getPositionOiseau().getX(), (int) m.getPositionOiseau().getY());
					repaint();
				} else if (e.getKeyChar() == 'g') {
					Model.debug = !Model.debug;
					repaint();
				}

			}
		});

		// Listener qui gere le drag
		v.getFenetre().addMouseMotionListener(new MouseInputAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if (!m.getOiseau().getVole()) {
					
					// calcul de l'angle
					
					double aa = m.getOiseau().distance(m.getOiseau().getC().x + Constantes.getInstance().TAILLE_OISEAU/2, m.getOiseau().getC().y + Constantes.getInstance().TAILLE_OISEAU/2,
							Constantes.getInstance().COORDONNEES_ORIGINE.x-100 + Constantes.getInstance().TAILLE_OISEAU/2, Constantes.getInstance().COORDONNEES_ORIGINE.y + Constantes.getInstance().TAILLE_OISEAU/2);
					
					double cc = m.getOiseau().distance(Constantes.getInstance().COORDONNEES_ORIGINE.x + Constantes.getInstance().TAILLE_OISEAU / 2,
							Constantes.getInstance().COORDONNEES_ORIGINE.y + Constantes.getInstance().TAILLE_OISEAU / 2, 
							Constantes.getInstance().COORDONNEES_ORIGINE.x-100 + Constantes.getInstance().TAILLE_OISEAU/2, 
							Constantes.getInstance().COORDONNEES_ORIGINE.y + Constantes.getInstance().TAILLE_OISEAU/2);
					
					double bb = m.getOiseau().distance(Constantes.getInstance().COORDONNEES_ORIGINE.x + Constantes.getInstance().TAILLE_OISEAU / 2,
							Constantes.getInstance().COORDONNEES_ORIGINE.y + Constantes.getInstance().TAILLE_OISEAU / 2, 
							m.getOiseau().getC().x+ Constantes.getInstance().TAILLE_OISEAU / 2, m.getOiseau().getC().y+ Constantes.getInstance().TAILLE_OISEAU / 2);
					
					
					if (e.getX() < Constantes.getInstance().COORDONNEES_ORIGINE.x + 20 + Constantes.getInstance().RAYON_DEPART
							&& e.getX() > Constantes.getInstance().COORDONNEES_ORIGINE.x + 20 - Constantes.getInstance().RAYON_DEPART
							&& e.getY() < Constantes.getInstance().COORDONNEES_ORIGINE.y + 2 * 20 + Constantes.getInstance().RAYON_DEPART
							&& e.getY() > Constantes.getInstance().COORDONNEES_ORIGINE.y + 20 - Constantes.getInstance().RAYON_DEPART

							&& m.getOiseau().distance(
									Constantes.getInstance().COORDONNEES_ORIGINE.x + Constantes.getInstance().TAILLE_OISEAU / 2,
									Constantes.getInstance().COORDONNEES_ORIGINE.y + Constantes.getInstance().TAILLE_OISEAU / 2,
									m.getPositionOiseau().getX() + Constantes.getInstance().TAILLE_OISEAU / 2,
									m.getPositionOiseau().getY()
									+ Constantes.getInstance().TAILLE_OISEAU / 2) < Constantes.getInstance().RAYON_DEPART) {
						
						
						m.getOiseau().setVitesse((int)m.getOiseau().distance(Constantes.getInstance().COORDONNEES_ORIGINE.x + Constantes.getInstance().TAILLE_OISEAU / 2,
								Constantes.getInstance().COORDONNEES_ORIGINE.y + Constantes.getInstance().TAILLE_OISEAU / 2,
								m.getOiseau().getC().x + Constantes.getInstance().TAILLE_OISEAU / 2, m.getOiseau().getC().y + Constantes.getInstance().TAILLE_OISEAU / 2));
						
						m.getOiseau().setAngle((bb*bb + cc*cc - aa*aa) / (2*bb*cc));
						
						setPositionOiseauC2(m.getPositionOiseau().x + 50, m.getPositionOiseau().y);
						setPositionOiseau(e.getX() - Constantes.getInstance().TAILLE_OISEAU / 2,
								e.getY() - Constantes.getInstance().TAILLE_OISEAU);

						repaint();
						setDrag(true);

					}
				}
			}
		});

		v.getFenetre().addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {
				if (m.getDrag()&& !m.getVol()) {
					setDrag(false);
					// courbes de Remi
					double posLanX = m.getPositionOiseau().getX();
					double posLanY = m.getPositionOiseau().getY();

					if (posLanY >= 350 && posLanX <= 150) {

						if(new Random().nextInt(5) == 0)
							Sound.getInstance().playWeeee();

						double c = 100;
						double calcul = ((posLanX / 1000000) * 6 + 0.00030) * Math.pow(m.getPositionOiseau().getX(), 2)
								+ (((posLanY - 350) / 7) - 1.05 - (1.02 * ((posLanY - 350) / 7)))
								* m.getPositionOiseau().getX()
								+ c;
						while (Math.abs(calcul - posLanY) > 3 && calcul < 1000) {
							c = c + 2;
							calcul = ((posLanX / 1000000) * 6 + 0.00030) * Math.pow(m.getPositionOiseau().getX(), 2)
									+ (((posLanY - 350) / 7) - 1.05 - (1.02 * ((posLanY - 350) / 7)))
									* m.getPositionOiseau().getX()
									+ c;
						}

						new Courbe((posLanX / 1000000) * 6 + 0.00030,
								((posLanY - 350) / 7) - 1.05 - (1.02 * ((posLanY - 350) / 7)), c, m.getOiseau(),
								m.getAffichage(), m.getListObstacles(), 1);
					} else if (posLanY < 350 && posLanX <= 150) {
						double coeffDir = (posLanY - 350) / (posLanX - 150);
						double hauteur = 350 - (coeffDir * 150);

						new Courbe(coeffDir, hauteur, m.getOiseau(), m.getAffichage(), m.getListObstacles(), 1);
					} else if (posLanY < 350 && posLanX > 150) {
						double coeffDir = (350 - posLanY) / (150 - posLanX);
						double hauteur = 350 - (coeffDir * 150);

						new Courbe(coeffDir, hauteur, m.getOiseau(), m.getAffichage(), m.getListObstacles(), 0);
					} else {
						double c = 100;
						double calcul = ((posLanX / 1000000) * 6 + 0.00030) * Math.pow(m.getPositionOiseau().getX(), 2)
								+ (((posLanY - 350) / 7) + 0.50 - (1.02 * ((posLanY - 350) / 7)))
								* m.getPositionOiseau().getX()
								+ c;
						while (Math.abs(calcul - posLanY) > 3 && calcul < 1000) {
							c = c + 2;
							calcul = ((posLanX / 1000000) * 6 + 0.00030) * Math.pow(m.getPositionOiseau().getX(), 2)
									+ (((posLanY - 350) / 7) + 0.50 - (1.02 * ((posLanY - 350) / 7)))
									* m.getPositionOiseau().getX()
									+ c;
						}
						new Courbe((posLanX / 1000000) * 6 + 0.00030,
								((posLanY - 350) / 7) + 0.50 - (1.02 * ((posLanY - 350) / 7)), c, m.getOiseau(),
								m.getAffichage(), m.getListObstacles(), 0);

						new Courbe((posLanX / 1000000) * 6 + 0.00030,
								((posLanY - 350) / 7) - 1.05 - (1.02 * ((posLanY - 350) / 7)), c, m.getOiseau(),
								m.getAffichage(), m.getListObstacles(), 1);

					}

					if(m.getOiseau().getScore() != 0 && m.getOiseau().getScore() == m.getCptObstacles()){
						m.getOiseau().setVictory(true);		
						// reset du modele (a faire)
						m.getOiseau().addToScore(-m.getOiseau().getScore());
						m.reset();
						repaint();
					}
				}
			}
		});		
		
	}

	public void initObstacles(){
		this.m.initObstacles();
	}

	public void initAffichage(){
		this.m.initAffichage();
	}

	public void SetVol(boolean b){
		this.m.setVol(b);
	}

}
