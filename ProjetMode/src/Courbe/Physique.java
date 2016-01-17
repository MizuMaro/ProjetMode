package Courbe;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import Element.Constantes;
import Element.Oiseau;
import IHM.Affichage;
import MVC.Model;
import ObstacleFactory.Obstacle;

/**
 * La classe Physique implemente le moteur physique du projet.
 * Celle-ci permet de lancer un oiseau tout en gerant l'acceleration, la vitesse de celui-ci et des 
 * obstacles ainsi que plusieurs parametres tels que la gravite.
 * @author Rémy & Rémi
 *  
 */
public class Physique {
	static double t;

	public Physique(){}
	
	/**
	 * permet de lancer un oiseau selon une courbe parametrique, avec une gestion de l'affichage
	 * et des obstacles.
	 * @param o L'oiseau a envoyer.
	 * @param vitesse La vitesse de l'oiseau au lancement.
	 * 
	 * @param angle L'angle de l'oiseau au lancement.
	 * @param a L'affichage sur lequel doit s'effectuer le graphisme du programme.
	 * @param m Le model d'ou l'on tire les donnees necessaires a l'execution du programme.
	 */
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
	
	/**
	 * Permet de définir les différents points de coordonnées de l'oiseau en fonction du temps
	 * @param t La variable du temps
	 * @param vitesse La vitesse initiale au lancement de l'oiseau
	 * @param anglen L'angle initial au lancement de l'oiseau
	 * @param posDepX La position initiale de l'oiseau sur le lance-pierre en abscisse
	 * @param posDepY La position initiale de l'oiseau sur le lance-pierre en ordonnée
	 * 
	 * @return Le point correspondant a la coordonnée suivante de l'oiseau
	 */
	static Point coordParabole(double t, double vitesse, double anglen, int posDepX, int posDepY) {
		double x = vitesse*Math.cos(anglen)*t + posDepX;
		
		double truc = x /(vitesse*Math.cos(anglen));
		final double G = 3;
		
		double y = (-(G/2))*truc*truc + vitesse*Math.sin(anglen)*truc + posDepY;
		return new Point((int) x, (int)y);
	}
	
	
}
