import java.awt.Color;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ecran {
	JFrame fenetre;
	Oiseau a ;
	JPanel bg;
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
		a = new Oiseau(new Point(50,fenetre.getHeight()-100));
		bg.add(a);
		a.setPreferredSize(fenetre.getSize());
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
		while(x<=800*7){
			x+=80;
			t++;
			g++;
			y= (int) (a * Math.pow(g, 2) + b * t + c);
			System.out.println(y);
			o.setC(x/8, y/8);
			Thread.sleep(50);
			bg.repaint();
		}
	}
}
