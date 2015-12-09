package Element;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

import Courbe.Courbe;
import Courbe.CourbesOld;
import IHM.Affichage;
import Obstacles.Obstacle;
import Obstacles.ObstacleMouvant;

public class Ecran {
	private JFrame fenetre;
	private Oiseau a;
	private ArrayList<Obstacle> obstacles = new ArrayList<>();
	private int compteurTouch=1;
	private Affichage affichage ;

	public Ecran() throws InterruptedException {

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
				//reset a la position d'origine de l'oiseau
				if(e.getKeyChar() == 'r'){
					getOiseau().setC(Constantes.COORDONNEES_ORIGINE.x, Constantes.COORDONNEES_ORIGINE.y);
					getOiseau().setC2(getOiseau().getC().x+50, getOiseau().getC().y);	
					affichage.repaint();
				}else if(e.getKeyChar() == 'q'){
					System.exit(0);
				}

			}
		});

		// Listener qui gere le drag
		fenetre.addMouseMotionListener(new MouseInputAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {	

				if(e.getX() < Constantes.COORDONNEES_ORIGINE.x+20+Constantes.RAYON_DEPART && e.getX() > Constantes.COORDONNEES_ORIGINE.x+20-Constantes.RAYON_DEPART
						&& e.getY() < Constantes.COORDONNEES_ORIGINE.y+2*20+Constantes.RAYON_DEPART && e.getY() > Constantes.COORDONNEES_ORIGINE.y+20-Constantes.RAYON_DEPART

						&& affichage.distance(Constantes.COORDONNEES_ORIGINE.x + Constantes.TAILLE_OISEAU/2, 
								Constantes.COORDONNEES_ORIGINE.y + Constantes.TAILLE_OISEAU/2, 
								a.getC().x+Constantes.TAILLE_OISEAU/2, 
								a.getC().y+Constantes.TAILLE_OISEAU/2)< Constantes.RAYON_DEPART){

					getOiseau().setC2(getOiseau().getC().x+50, getOiseau().getC().y);	
					getOiseau().setC(e.getX()-Constantes.TAILLE_OISEAU/2,e.getY()-Constantes.TAILLE_OISEAU);
					affichage.repaint();
				}
			}
		});

		// Listener qui gere le drop
		fenetre.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("unused")
			public void mouseReleased(MouseEvent e){
				//Courbe c = new Courbe(0.00077, -1.05, 500, a, affichage, obstacles);
				
				// courbes de Remi
				double bonjourtoto = (double) getOiseau().getC().x;
				double bonjourtiti = (double) getOiseau().getC().y;
				Courbe c = new Courbe((bonjourtoto/1000000)*6 + 0.00030 , ((bonjourtiti-350)/7)-1.05-(1.02*((bonjourtiti-350)/7)) , 500, a, affichage, obstacles);
			
			}
		});


		// initialisation de l'oiseau
		a = new Oiseau(new Point(Constantes.COORDONNEES_ORIGINE));

		// ajout des obstacles;
		addObstacles();

		//initialisation de la position du bec
		getOiseau().setC2(getOiseau().getC().x+50, getOiseau().getC().y);

		affichage = new Affichage(a, obstacles, compteurTouch);
		fenetre.setContentPane(affichage);	

		// fonctions du jalon 1
		//courbesTest();

	}	
	
	
	
	

	public void addObstacles(){

		Obstacle ob1 = new Obstacle(new Point(fenetre.getWidth() - 100,
				fenetre.getHeight() - 130));
		Obstacle ob2 = new Obstacle(new Point(fenetre.getWidth() - 120,
				fenetre.getHeight() - 270));
		Obstacle ob3 = new Obstacle(new Point(fenetre.getWidth() - 140,
				fenetre.getHeight() - 370));
		Obstacle ob4 = new Obstacle(new Point(fenetre.getWidth() - 160,
				fenetre.getHeight() - 470));
		Obstacle ob5 = new Obstacle(new Point(fenetre.getWidth() - 180,
				fenetre.getHeight() - 600));
		Obstacle ob6 = new Obstacle(new Point(fenetre.getWidth() - 600,
				fenetre.getHeight() - 180));
		Obstacle ob7 = new Obstacle(new Point(fenetre.getWidth() - 200,
				fenetre.getHeight() - 150));

		ObstacleMouvant ob8 = new ObstacleMouvant(new Point(fenetre.getWidth() - 400,
				fenetre.getHeight() - 550));
		ob8.setLimites_x(new int[]{fenetre.getWidth() - 400, fenetre.getWidth() - 400});
		ob8.setLimites_y(new int[]{fenetre.getHeight() - 550, fenetre.getHeight() - 400});

		ObstacleMouvant ob9 = new ObstacleMouvant(new Point(fenetre.getWidth() - 1000,
				fenetre.getHeight()/3));
		ob9.setLimites_x(new int[]{fenetre.getWidth() - 1000, fenetre.getWidth() - 800});
		ob9.setLimites_y(new int[]{fenetre.getHeight()/3, fenetre.getHeight()/3});
		ob9.setCarre(true);

		obstacles.add(ob1);
		obstacles.add(ob2);
		obstacles.add(ob3);
		obstacles.add(ob4);
		obstacles.add(ob5);
		obstacles.add(ob6);
		obstacles.add(ob7);
		obstacles.add(ob8);
		obstacles.add(ob9);	
	}

	
	public void courbesTest() {

		CourbesOld courbesTest = new CourbesOld();

		courbesTest.courbe(0.00077, -1.05, 500, a, affichage, obstacles);
		courbesTest.courbe(0.0009, -1, 500, a, affichage, obstacles);
		courbesTest.courbeCubique(-0.0000005, 0.1, 450, a, affichage, obstacles);
		courbesTest.courbe(0.0009, -1, 500, a, affichage, obstacles);
		courbesTest.courbe(0.0008, -1.01, 500, a, affichage, obstacles);
		courbesTest.courbe(0.00077, -1.05, 500, a, affichage, obstacles);
		courbesTest.courbe(0.0007, -1.05, 500, a, affichage, obstacles);
		courbesTest.courbeCubique(0.000001, 0.001, 450, a, affichage, obstacles);
		courbesTest.courbe(0.0005, -1, 500, a, affichage, obstacles);
	}

	
	public Oiseau getOiseau() {
		return a;
	}

}
