import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JFrame;

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
	
	private Affichage affichage ;
	public Ecran() throws InterruptedException {

		this.fenetre = new JFrame(Constantes.TITRE);
		fenetre.setSize(Constantes.LARGEUR_ECRAN, Constantes.HAUTEUR_ECRAN);
		fenetre.setResizable(false);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
		fenetre.setLocationRelativeTo(null);
		/*bg = new JPanel();
		bg.setSize(Constantes.LARGEUR_ECRAN, Constantes.HAUTEUR_ECRAN);

		// fond du panel
		bg.setBackground(Constantes.COULEUR_BACKGROUND);
		*/
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

		// ajout des obstacles dans la liste pour pouvoir les gerer
		obstacles.add(ob1);
		obstacles.add(ob2);
		obstacles.add(ob3);
		obstacles.add(ob4);
		obstacles.add(ob5);
		/*
		// setPreferredSize pour permetre l'affichage sans layout
		ob1.setPreferredSize(fenetre.getSize());
		ob2.setPreferredSize(fenetre.getSize());
		ob3.setPreferredSize(fenetre.getSize());
		ob4.setPreferredSize(fenetre.getSize());
		ob5.setPreferredSize(fenetre.getSize());
		a.setPreferredSize(fenetre.getSize());
	
		// ajout des obstacles dans l'obstacles puis dans l'oiseau puis dans le
		// panel principal (poupée russe)
		ob4.add(ob5);
		ob3.add(ob4);
		ob2.add(ob3);
		ob1.add(ob2);
		a.add(ob1);
		bg.add(a);
		*/
		affichage = new Affichage(a, obstacles);
		fenetre.setContentPane(affichage);
		

		// differentes courbes
		
		courbe(0.0009, -1, 500, a);
		courbe(0.0008, -1.01, 500, a);
		courbe(0.00077, -1.05, 500, a);
		courbe(0.0007, -1.05, 500, a);
		courbe(0.0005, -1, 500, a);

	}

	

	void courbe(double a, double b, double c, Oiseau o) throws InterruptedException {
		
		if(Constantes.TRAJECTOIRE_UNIQUE)
			o.effacerTrajectoire();
		
		o.setC(50, 450);
		affichage.repaint();
		
		long premier = System.currentTimeMillis();
		long deuxieme = premier+1000;
		
		while(premier<deuxieme){
			premier=System.currentTimeMillis();
		}
		
		int y;
		int x = 50;
		int x2;
		int y2;
		boolean touch = false;
		
		long timeLancement = System.currentTimeMillis();
		long timeFin = timeLancement + 15000;
		
		while (x <= 800 * 7 && !touch && timeLancement<timeFin) {
			
			timeLancement = System.currentTimeMillis();
			// plus ou moins loin
			x += 2;
			//pour tangente
			x2 = x+10;
			y2 = (int) (a * Math.pow(x2, 2) + b * x2 + c);
			// courbe ax2+bx+c
			y = (int) (a * Math.pow(x, 2) + b * x + c);
			
			// System.out.println(y);
			// nouvelle position de l'oiseau
			o.setC(x, y);
			o.setC2(x2, y2);
			o.setC(x,y);
			// utiliser timer
			long now = System.currentTimeMillis();
			long time = now + 10;
			
			while (now < time) {
				now = System.currentTimeMillis();
			}

			// test si un obstacle est touchï¿½
			for (Obstacle ob : obstacles) {
				if (this.a.getC().getX() > ob.getC().getX() - Constantes.TAILLE_OBSTACLES
						&& this.a.getC().getX() < ob.getC().getX() + Constantes.TAILLE_OBSTACLES
						&& this.a.getC().getY() > ob.getC().getY() - Constantes.TAILLE_OBSTACLES
						&& this.a.getC().getY() < ob.getC().getY() + Constantes.TAILLE_OBSTACLES) {
					touch = true;
					ob.setActif(false);
					affichage.repaint();
					now = System.currentTimeMillis();
					time = now + 2000;
					
					while (now < time) {
						now = System.currentTimeMillis();
					}
				}
			}
			
			affichage.repaint();
		}
	}

}
