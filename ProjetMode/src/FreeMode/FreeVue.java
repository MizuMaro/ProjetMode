package FreeMode;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

import Courbe.Courbe;
import Element.Constantes;

public class FreeVue implements Observer  {
	
	protected FreeModel m;
	protected FreeController c;
	

	public FreeVue(final FreeModel m, final FreeController c, final JFrame fenetre) {
		
		// MVC
		this.m = m;
		this.c = c;
		m.addObserver(this);

		// Listener qui gere le drag
		fenetre.addMouseMotionListener(new MouseInputAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {
				
				if (!m.getOiseau().getVole()) {
					if (e.getX() < Constantes.COORDONNEES_ORIGINE.x + 20 + Constantes.RAYON_DEPART
							&& e.getX() > Constantes.COORDONNEES_ORIGINE.x + 20 - Constantes.RAYON_DEPART
							&& e.getY() < Constantes.COORDONNEES_ORIGINE.y + 2 * 20 + Constantes.RAYON_DEPART
							&& e.getY() > Constantes.COORDONNEES_ORIGINE.y + 20 - Constantes.RAYON_DEPART

							&& m.getAffichage().distance(
									Constantes.COORDONNEES_ORIGINE.x + Constantes.TAILLE_OISEAU / 2,
									Constantes.COORDONNEES_ORIGINE.y + Constantes.TAILLE_OISEAU / 2,
									m.getPositionOiseau().getX() + Constantes.TAILLE_OISEAU / 2,
									m.getPositionOiseau().getY()
									+ Constantes.TAILLE_OISEAU / 2) < Constantes.RAYON_DEPART) {

						c.setPositionOiseauC2(m.getPositionOiseau().x + 50, m.getPositionOiseau().y);
						c.setPositionOiseau(e.getX() - Constantes.TAILLE_OISEAU / 2,
								e.getY() - Constantes.TAILLE_OISEAU);

						c.repaint();
						c.setDrag(true);

					}
				}
			}

		});

		fenetre.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e){
				
				if(e.getX() > 25 && e.getX() < 65 && e.getY() > 50+25 && e.getY() < 90+25){
					m.carre = true;
					m.rond = false;
					m.carre_bouge = false;
					m.rond_bouge = false;
					
				}else if(e.getX() > 25 && e.getX() < 65 && e.getY() > 110+25 && e.getY() < 150+25){
					m.carre = false;
					m.rond = true;
					m.carre_bouge = false;
					m.rond_bouge = false;
					
				}else if(e.getX() > 25 && e.getX() < 65 && e.getY() > 165+25 && e.getY() < 220+25){
					m.carre = false;
					m.rond = false;
					m.carre_bouge = true;
					m.rond_bouge = false;
					
				}else if(e.getX() > 25 && e.getX() < 65 && e.getY() > 235+25 && e.getY() < 290+25){
					m.carre = false;
					m.rond = false;
					m.carre_bouge = false;
					m.rond_bouge = true;
					
				}else if(!c.getDrag() && !m.getVol()){		
					m.modObstacle(e.getX(), e.getY());
					
				}

				c.repaint();
			}
			
			// fonction qui gere le drop
			public void mouseReleased(MouseEvent e) {
				
				if (c.getDrag()&& !m.getVol()) {
					
					c.setDrag(false);
					
					// courbes de Remi
					double posLanX = m.getPositionOiseau().getX();
					double posLanY = m.getPositionOiseau().getY();

					if (posLanY >= 350 && posLanX <= 150) {

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
				}
			}

		});

		// initialisation de la position du bec
		c.setPositionOiseauC2(m.getPositionOiseau().x + 50, m.getPositionOiseau().y);
		c.initObstacles();
		c.initAffichage();
		
		fenetre.setContentPane(m.getAffichage());
	}
	
	@Override
	public void update(Observable o, Object arg) {

	}

}