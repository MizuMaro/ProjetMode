package Courbe;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import Element.Constantes;
import Element.Oiseau;
import IHM.Affichage;
import MVC.Model;
import ObstacleFactory.Obstacle;


public class Physique {
	static double t;

	public Physique(){
		
	}
	
	public void trajectoireParabole(final Oiseau o, final double vitesse, final double angle, final Affichage a, final Model m){
		t=0;
		final Timer timer = new Timer();
		TimerTask timerTask = new TimerTask(){
			public void run() {
				t+=0.015;
				o.setCoord(coordParabole(t,vitesse,angle,150,350));
				double t2 = t+0.002;
				o.setProchaineCoord(coordParabole(t2,vitesse,angle,150,350));
				while(o.getC().distance(o.getProchaineCoord()) < o.getTaille()/2 + 10){
					t2+=0.002;
					o.setProchaineCoord(coordParabole(t2,vitesse,angle,150,350));
				}
				for(Obstacle c : m.getListObstacles()){
					if(c.getC().distance(o.getC()) < (c.getTaille()/2 + o.getTaille()/2)){
						c.setActif(false);
					}
				}
				a.repaint();
				if(o.getC().y<=0+o.getTaille()/2 || t>=8000){
					o.setVole(false);
					o.effacerTrajectoire();
					timer.cancel();
				}
			}
		};
		timer.scheduleAtFixedRate(timerTask,0,1);
	}
	
	static Point coordParabole(double t, double vitesse, double anglen, int posDepX, int posDepY) {
		double x = vitesse*Math.cos(anglen)*t + posDepX;
		
		double truc = x /(vitesse*Math.cos(anglen));
		final double G = 9.81;
		
		double y = (-(G/2))*truc*truc + vitesse*Math.sin(anglen)*truc + posDepY;
		return new Point((int) x, (int)y);
	}
	
	
}