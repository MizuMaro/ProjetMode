package Vue;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

import Controller.Controller;
import Courbe.Courbe;
import Courbe.CourbesOld;
import Element.Constantes;
import IHM.Affichage;
import Model.Model;
import Obstacles.Obstacle;
import Obstacles.ObstacleMouvant;

public class Vue implements Observer {
	private JFrame fenetre;
	protected Model m;
	protected Controller c;

	public Vue(Model m, Controller c) {
		// MVC
		this.m = m;
		this.c = c;
		m.addObserver(this);

		this.fenetre = new JFrame(Constantes.TITRE);
		fenetre.setSize(Constantes.TAILLE_ECRAN[0], Constantes.TAILLE_ECRAN[1]);
		fenetre.setResizable(false);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
		fenetre.setLocationRelativeTo(null);

		// Listener qui gere quelques actions au clavier
		fenetre.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// reset a la position d'origine de l'oiseau
				if (e.getKeyChar() == 'r') {
					c.volFini();
					c.setPositionOiseau(Constantes.COORDONNEES_ORIGINE.x, Constantes.COORDONNEES_ORIGINE.y);
					c.setPositionOiseauC2((int) m.getPositionOiseau().getX(), (int) m.getPositionOiseau().getY());
					c.repaint();
				} else if (e.getKeyChar() == 'q') {
					System.exit(0);
				}

			}
		});

		// Listener qui gere le drag
		fenetre.addMouseMotionListener(new MouseInputAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {

				if (e.getX() < Constantes.COORDONNEES_ORIGINE.x + 20 + Constantes.RAYON_DEPART
						&& e.getX() > Constantes.COORDONNEES_ORIGINE.x + 20 - Constantes.RAYON_DEPART
						&& e.getY() < Constantes.COORDONNEES_ORIGINE.y + 2 * 20 + Constantes.RAYON_DEPART
						&& e.getY() > Constantes.COORDONNEES_ORIGINE.y + 20 - Constantes.RAYON_DEPART

						&& m.getAffichage().distance(Constantes.COORDONNEES_ORIGINE.x + Constantes.TAILLE_OISEAU / 2,
								Constantes.COORDONNEES_ORIGINE.y + Constantes.TAILLE_OISEAU / 2,
								m.getPositionOiseau().getX() + Constantes.TAILLE_OISEAU / 2,
								m.getPositionOiseau().getY()
										+ Constantes.TAILLE_OISEAU / 2) < Constantes.RAYON_DEPART) {

					c.setPositionOiseauC2(m.getPositionOiseau().x + 50, m.getPositionOiseau().y);
					c.setPositionOiseau(e.getX() - Constantes.TAILLE_OISEAU / 2, e.getY() - Constantes.TAILLE_OISEAU);

					System.out.println(e.getX() + " " + e.getY());
					c.repaint();

				}
			}
		});

		// Listener qui gere le drop
		fenetre.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unused")
			public void mouseReleased(MouseEvent e) {
				// Courbe c = new Courbe(0.00077, -1.05, 500, a, affichage,
				// obstacles);

				// courbes de Remi
				double bonjourtoto = m.getPositionOiseau().getX();
				double bonjourtiti = m.getPositionOiseau().getY();
				Courbe courbe = new Courbe((bonjourtoto / 1000000) * 6 + 0.00030,
						((bonjourtiti - 350) / 7) - 1.05 - (1.02 * ((bonjourtiti - 350) / 7)), 500, m.getOiseau(),
						m.getAffichage(), m.getListObstacles());

			}
		});

		// initialisation de la position du bec
		c.setPositionOiseauC2(m.getPositionOiseau().x + 50, m.getPositionOiseau().y);
		// initialisation des obstacles
		c.initObstacles();
		// initialisation du jPanel Affichage
		c.initAffichage();
		fenetre.setContentPane(m.getAffichage());

		// fonctions du jalon 1
		// courbesTest();

	}

	public void courbesTest() {

		CourbesOld courbesTest = new CourbesOld();

		courbesTest.courbe(0.00077, -1.05, 500, m.getOiseau(), m.getAffichage(), m.getListObstacles());
		courbesTest.courbe(0.0009, -1, 500, m.getOiseau(), m.getAffichage(), m.getListObstacles());
		courbesTest.courbeCubique(-0.0000005, 0.1, 450, m.getOiseau(), m.getAffichage(), m.getListObstacles());
		courbesTest.courbe(0.0009, -1, 500, m.getOiseau(), m.getAffichage(), m.getListObstacles());
		courbesTest.courbe(0.0008, -1.01, 500, m.getOiseau(), m.getAffichage(), m.getListObstacles());
		courbesTest.courbe(0.00077, -1.05, 500, m.getOiseau(), m.getAffichage(), m.getListObstacles());
		courbesTest.courbe(0.0007, -1.05, 500, m.getOiseau(), m.getAffichage(), m.getListObstacles());
		courbesTest.courbeCubique(0.000001, 0.001, 450, m.getOiseau(), m.getAffichage(), m.getListObstacles());
		courbesTest.courbe(0.0005, -1, 500, m.getOiseau(), m.getAffichage(), m.getListObstacles());
	}

	@Override
	public void update(Observable o, Object arg) {

	}

}
