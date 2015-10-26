import java.awt.Color;
import java.awt.List;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ecran {
	JFrame fenetre;
	Oiseau a;
	//panel principal 
	JPanel bg;
	//liste et obstacle
	ArrayList<Obstacle> obstacles = new ArrayList<>();
	Obstacle ob1;
	Obstacle ob2;

	public Ecran() throws InterruptedException {
		this.fenetre = new JFrame("Projet Angry Bird");
		fenetre.setSize(800, 600);
		fenetre.setResizable(false);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
		fenetre.setLocationRelativeTo(null);
		bg = new JPanel();
		bg.setPreferredSize(fenetre.getSize());
		//fond du panel 
		bg.setBackground(Color.BLACK);
		// initialisation de l'oiseau
		a = new Oiseau(new Point(50, fenetre.getHeight() - 100));
		// initialisation des obstacles;
		ob1 = new Obstacle(new Point(550, fenetre.getHeight() - 400));
		ob2 = new Obstacle(new Point(250, fenetre.getHeight() - 400));
		//ajout des obstacles dans la liste pour pouvoir les gerer 
		obstacles.add(ob1);
		obstacles.add(ob2);
		
		//setPreferredSize pour permetre l'affichage sans layout
		ob2.setPreferredSize(fenetre.getSize());
		ob1.setPreferredSize(fenetre.getSize());
		a.setPreferredSize(fenetre.getSize());
		// ajouts des obstacles dans l'obstacles puis dans l'oiseau puis dans le panel principal (poupée russe)
		ob1.add(ob2);
		a.add(ob1);
		bg.add(a);
		fenetre.setContentPane(bg);
		//differentes courbes
		courbe(3, 3, 30, a);
		courbe(3, 60, 3, a);
		courbe(3, 50, 3, a);
	}

	void courbe(double a, double b, double c, Oiseau o) throws InterruptedException {
		
		int y;
		// point de depart
		int g = -36;
		//
		int t = 0;
		//
		int x = 50;
		boolean touch = false;
		while (x <= 800 * 7 && !touch) {
			//plus ou moins loin 
			x += 80;
			t++;
			g++;
			//courbe ax2+bx+c
			y = (int) (a * Math.pow(g, 2) + b * t + c);
			System.out.println(y);
			//nouvelle position de l'oiseau
			o.setC(x / 8, y / 8);
			//utiliser timer
			Thread.sleep(50);
			
			// test si un obstacle est touché
			for (Obstacle ob : obstacles) {
				if (this.a.getC().getX() > ob.getC().getX() - 40 && this.a.getC().getX() < ob.getC().getX() + 40
						&& this.a.getC().getY() > ob.getC().getY() - 40
						&& this.a.getC().getY() < ob.getC().getY() + 40) {
					touch = true;
					ob.setActif(false);
					Thread.sleep(2000);
				}
			}
			bg.repaint();
		}
	}
}
