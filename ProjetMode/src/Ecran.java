import java.awt.Color;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ecran {
	JFrame fenetre;
	Oiseau a ;
	JPanel bg;
	Obstacle ob1;
	public Ecran() throws InterruptedException {
		this.fenetre = new JFrame("Projet Angry Bird");
		fenetre.setSize(800,600);
		fenetre.setResizable(false);	
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
		fenetre.setLocationRelativeTo(null);
		bg= new JPanel();
		bg.setPreferredSize(fenetre.getSize());
		bg.setBackground(Color.BLACK);
		//initialisation de l'oiseau
		a = new Oiseau(new Point(50,fenetre.getHeight()-100));
		//initialisation des obstacles;
		ob1= new Obstacle(new Point(550,fenetre.getHeight()-400));

		ob1.setPreferredSize(fenetre.getSize());
		a.setPreferredSize(fenetre.getSize());
		
		a.add(ob1);
		bg.add(a);
		fenetre.setContentPane(bg);
		
		courbe(3,3,30,a);
		courbe(3,100,3,a);
		courbe(3,50,3,a);
		//fenetre.pack();
	}
	void courbe(double a,double b , double c,Oiseau o) throws InterruptedException{
		int y  ;
		int g=-36;
		int t=0;
		int x=50;
		boolean touch=false;
		while(x<=800*7 && !touch){
			x+=80;
			t++;
			g++;
			y= (int) (a * Math.pow(g, 2) + b * t + c);
			System.out.println(y);
			o.setC(x/8, y/8);
			Thread.sleep(50);
			if(
					this.a.getC().getX() > ob1.getC().getX()-40 && 
					this.a.getC().getX() < ob1.getC().getX()+40 && 
					this.a.getC().getY() > ob1.getC().getY()-40 && 
					this.a.getC().getY() < ob1.getC().getY()+40){
				touch = true;
				this.ob1.setActif(false);
				Thread.sleep(2000);
			}
			bg.repaint();
		}
	}
}
