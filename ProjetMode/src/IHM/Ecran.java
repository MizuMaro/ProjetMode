package IHM;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;

import Element.Constantes;
import Element.Obstacle;
import Element.ObstacleMouvant;
import Element.Oiseau;

public class Ecran {
	private JFrame fenetre;
	private Oiseau a;
	
	// liste et obstacle
	private ArrayList<Obstacle> obstacles = new ArrayList<>();
	private Obstacle ob1;
	private Obstacle ob2;
	private Obstacle ob3;
	private Obstacle ob4;
	private Obstacle ob5;
	private Obstacle ob6;
	private Obstacle ob7;	
	private ObstacleMouvant ob8;
	private ObstacleMouvant ob9;
	
	private int compteurTouch=1;
	
	private Affichage affichage ;
	public Ecran() throws InterruptedException {

		this.fenetre = new JFrame(Constantes.TITRE);
		fenetre.setSize(Constantes.LARGEUR_ECRAN, Constantes.HAUTEUR_ECRAN);
		fenetre.setResizable(false);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
		fenetre.setLocationRelativeTo(null);
		
		// initialisation de l'oiseau
		a = new Oiseau(new Point(Constantes.COORDONNEES_ORIGINE));

		// initialisation des obstacles;
		ob1 = new Obstacle(new Point(fenetre.getWidth() - 100,
				fenetre.getHeight() - 130));
		ob2 = new Obstacle(new Point(fenetre.getWidth() - 120,
				fenetre.getHeight() - 270));
		ob3 = new Obstacle(new Point(fenetre.getWidth() - 140,
				fenetre.getHeight() - 370));
		ob4 = new Obstacle(new Point(fenetre.getWidth() - 160,
				fenetre.getHeight() - 470));
		ob5 = new Obstacle(new Point(fenetre.getWidth() - 180,
				fenetre.getHeight() - 600));
		ob6 = new Obstacle(new Point(fenetre.getWidth() - 600,
				fenetre.getHeight() - 180));
		ob7 = new Obstacle(new Point(fenetre.getWidth() - 200,
				fenetre.getHeight() - 150));
		
		ob8 = new ObstacleMouvant(new Point(fenetre.getWidth() - 400,
				fenetre.getHeight() - 550));
		ob8.setLimites_x(new int[]{fenetre.getWidth() - 400, fenetre.getWidth() - 400});
		ob8.setLimites_y(new int[]{fenetre.getHeight() - 550, fenetre.getHeight() - 400});

		ob9 = new ObstacleMouvant(new Point(fenetre.getWidth() - 1000,
				fenetre.getHeight()/3));
		ob9.setLimites_x(new int[]{fenetre.getWidth() - 1000, fenetre.getWidth() - 800});
		ob9.setLimites_y(new int[]{fenetre.getHeight()/3, fenetre.getHeight()/3});
		
		
		// ajout des obstacles dans la liste pour pouvoir les gerer
		obstacles.add(ob1);
		obstacles.add(ob2);
		obstacles.add(ob3);
		obstacles.add(ob4);
		obstacles.add(ob5);
		obstacles.add(ob6);
		obstacles.add(ob7);
		obstacles.add(ob8);
		obstacles.add(ob9);

		affichage = new Affichage(a, obstacles, compteurTouch);
		fenetre.setContentPane(affichage);		

		// differentes courbes
		courbe(0.0009, -1, 500, a);
		courbeCubique(-0.0000005, 0.1, 450, a);
		courbe(0.0009, -1, 500, a);
		courbe(0.0008, -1.01, 500, a);
		courbe(0.00077, -1.05, 500, a);
		courbe(0.0007, -1.05, 500, a);
		courbeCubique(0.000001, 0.001, 450, a);
		courbe(0.0005, -1, 500, a);

	}

	

	void courbe(double a, double b, double c, Oiseau o) throws InterruptedException {
		
		affichage.setCollision(false);
		
		if(Constantes.TRAJECTOIRE_UNIQUE)
			o.effacerTrajectoire();
			o.effacerTrajectoireTangeante();
			
		affichage.repaint();
		o.setC(50, 450);
		o.setC2(100, 430);
		affichage.repaint();
		
		long premier = System.currentTimeMillis();
		long deuxieme = premier+1000;
		while(premier<deuxieme){
			premier=System.currentTimeMillis();
		}
		
		int y;
		int x = 50;
		int x2 = 50+50;
		int y2 =0;
		boolean touch = false;
		
		long timeLancement = System.currentTimeMillis();
		long timeFin = timeLancement + 15000;
		
		while (x <= 800 * 7 && !touch && timeLancement<timeFin) {
			
			timeLancement = System.currentTimeMillis();
			// plus ou moins loin
			x += 4;
			//pour tangente
			x2 = x+50;
			y2 = (int) ((int) (a*2*x2+b)*(x2-a)+(a * Math.pow(x2, 2) + b * x2 + c));
			// courbe ax2+bx+c
			y = (int) (a * Math.pow(x, 2) + b * x + c);
			
			// System.out.println(y);
			// nouvelle position de l'oiseau
			o.setC(x, y);
			//o.setC2(x2, y2);
			
			// utiliser timer
			long now = System.currentTimeMillis();
			long time = now + 10;
			
			while (now < time) {
				now = System.currentTimeMillis();
			}

			for (Obstacle ob : obstacles) {
				
				//on bouge les obstacles qui se déplacent
				if(ob.isActif() && ob instanceof ObstacleMouvant){
					((ObstacleMouvant)ob).moveX();
					((ObstacleMouvant)ob).moveY();
				}
				
				// test si un obstacle est touche
				if (this.a.getC().getX() > ob.getC().getX() - Constantes.TAILLE_OBSTACLES
						&& this.a.getC().getX() < ob.getC().getX() + Constantes.TAILLE_OBSTACLES
						&& this.a.getC().getY() > ob.getC().getY() - Constantes.TAILLE_OBSTACLES
						&& this.a.getC().getY() < ob.getC().getY() + Constantes.TAILLE_OBSTACLES
						&& ob.isActif()) {
					
					affichage.setCollision(true);
					touch = true;
					compteurTouch++;
					ob.setActif(false);
					affichage.repaint();
					now = System.currentTimeMillis();
					time = now + 2000;
					
					while (now < time) {
						now = System.currentTimeMillis();
					}
				}
				if (touch == false)
				o.setC2(x2, y2);
			}
			
			affichage.repaint();
		}
	}
	
void courbeCubique(double a, double b, double c, Oiseau o) throws InterruptedException {
		
		affichage.setCollision(false);
		
		if(Constantes.TRAJECTOIRE_UNIQUE)
			o.effacerTrajectoire();
			o.effacerTrajectoireTangeante();
			
		affichage.repaint();
		o.setC(50, 450);
		o.setC2(100, 430);
		affichage.repaint();
		
		long premier = System.currentTimeMillis();
		long deuxieme = premier+1000;
		
		while(premier<deuxieme){
			premier=System.currentTimeMillis();
		}
		
		int y;
		int x = 50;
		int x2 = 50+50;
		int y2 =0;
		boolean touch = false;
		
		long timeLancement = System.currentTimeMillis();
		long timeFin = timeLancement + 15000;
		
		while (x <= 800 * 7 && !touch && timeLancement<timeFin) {
			
			timeLancement = System.currentTimeMillis();
			// plus ou moins loin
			x += 4;
			//pour tangente
			x2 = x+50;
			y2 = (int) ((int) (a*3*Math.pow(x2,2)+b)*(x2-a)+(a * Math.pow(x2, 3) + b * x2 + c));
			// courbe ax2+bx+c
			y = (int) (a * Math.pow(x, 3) + b * x + c);
			
			// System.out.println(y);
			// nouvelle position de l'oiseau
			o.setC(x, y);
			//o.setC2(x2, y2);
			
			// utiliser timer
			long now = System.currentTimeMillis();
			long time = now + 10;
			
			while (now < time) {
				now = System.currentTimeMillis();
			}

			// test si un obstacle est touche
			for (Obstacle ob : obstacles) {
				
				//on bouge les obstacles qui se déplacent
				if(ob.isActif() && ob instanceof ObstacleMouvant){
					((ObstacleMouvant)ob).moveX();
					((ObstacleMouvant)ob).moveY();
				}
				
				// test si un obstacle est touche
				if (this.a.getC().getX() > ob.getC().getX() - Constantes.TAILLE_OBSTACLES
						&& this.a.getC().getX() < ob.getC().getX() + Constantes.TAILLE_OBSTACLES
						&& this.a.getC().getY() > ob.getC().getY() - Constantes.TAILLE_OBSTACLES
						&& this.a.getC().getY() < ob.getC().getY() + Constantes.TAILLE_OBSTACLES
						&& ob.isActif()) {
					
					affichage.setCollision(true);
					touch = true;
					compteurTouch++;
					ob.setActif(false);
					affichage.repaint();
					now = System.currentTimeMillis();
					time = now + 2000;
					
					while (now < time) {
						now = System.currentTimeMillis();
					}
				}
				if (touch == false)
				o.setC2(x2, y2);
			}
			
			affichage.repaint();
		}
	}

}
